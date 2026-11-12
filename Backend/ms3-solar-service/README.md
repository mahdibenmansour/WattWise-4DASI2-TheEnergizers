# MS3 — Solar Panel Management

Solar installations, panels, daily production, self-consumption, exports and estimated savings.

| Setting | Value |
|---|---|
| Owner | Farouk |
| Service name | `ms3-solar-service` |
| Port | 8083 |
| Technology | Java 17 · Spring Boot 3.5 · Spring Cloud 2025.0 · Maven |
| Database | PostgreSQL `solar_db` (used only by MS3) |
| Package | `tn.esprit.wattwise.solar` |

## Status (session 4)

Project created: it starts, registers in Eureka, reads the Config Server when it runs, and exposes a check endpoint and Swagger. Business logic comes after the design is validated.

## Run it

```bash
cd Backend/ms3-solar-service
./mvnw spring-boot:run            # Windows: mvnw.cmd spring-boot:run
```

- Check endpoint: http://localhost:8083/api/v1/hello
- Swagger UI: http://localhost:8083/swagger-ui.html
- Health: http://localhost:8083/actuator/health
- Through the gateway: http://localhost:8080/ms3/api/v1/hello

Without Eureka or the Config Server running, the service still starts (it only logs a connection warning). With PostgreSQL running, start it with the `db` profile: `./mvnw spring-boot:run -Dspring-boot.run.profiles=db`.

## Design

Class diagram: [Documentation/Diagrams/class-diagrams/ms3-solar-service](../../Documentation/Diagrams/class-diagrams/ms3-solar-service/)

Entities: `SolarInstallation`, `SolarPanel`, `ProductionRecord`, `SolarEnergyBalance`, `SolarSavings`, plus a local `HouseholdRef` copy and the `OutboxEvent` / `ProcessedEvent` tables.

Rule: `selfConsumedKWh + exportedKWh` must not exceed `producedKWh`.

## API contract (planned) · base `/api/v1/solar`

| Method | URI | Codes |
|---|---|---|
| POST | `/installations` | 201, 400, 403 |
| GET | `/installations/{id}` | 200, 404 |
| POST | `/installations/{id}/production` | 201, 400, 404 |
| GET | `/installations/{id}/balance` | 200, 404 |
| GET | `/installations/{id}/savings` | 200, 404 |

## Interactions

| Direction | With | Mode | Data | If the other side is down |
|---|---|---|---|---|
| Out | MS2 `GET /tariffs/current` | Sync (OpenFeign + Resilience4j) | Current STEG rate, saved as a snapshot | Last known rate is reused |
| In | MS1 events `HouseholdCreated`, `HouseholdDeleted` | Async (RabbitMQ) | Local household copy, cleanup | Events wait in the queue |
| Out | `SolarProductionRecorded` → MS5 | Async (RabbitMQ, outbox) | householdId, installationId, producedKWh, selfConsumedKWh | Event stays in the outbox |
| In | MS7 `GET /installations/{id}` | Sync, display only | Installation summary | MS7 still shows the request |

Household ownership is checked with the `household_ids` claim of the user's JWT, so MS3 does not call MS1.

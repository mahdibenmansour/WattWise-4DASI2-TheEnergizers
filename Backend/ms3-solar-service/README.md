# MS3 — Solar Management

Solar installations, panels, production, self-consumption, exports and estimated savings.

| Setting | Agreed value |
|---|---|
| Owner | Farouk |
| Service name | `ms3-solar-service` |
| Port | 8083 |
| Technology | Java 17 / Spring Boot 3.3.x / Maven |
| Database | PostgreSQL `solar_db` (exclusive to this service) |
| Package | `tn.esprit.wattwise.solar` |

## Session 4 handoff

Documentation placeholder only; no application or database configuration is implemented here yet.

- Create the project and implement `GET /api/v1/hello`.
- Add Spring Web, Eureka Discovery Client, Config Client, Actuator, the database driver and Spring Data, plus springdoc 2.6.0. Expose Swagger at `/swagger-ui.html`.
- Register with Eureka (:8761), use Config Server (:8888) where applicable, and configure the assigned database.
- Document API contracts, events and dependency failure behavior before adding business logic.
- Verify startup, the hello response, OpenAPI and discovery registration; commit from your own account.

See the [project overview](../../README.md) and [project context](../../WATTWISE_CONTEXT.md) for the full agreed design.

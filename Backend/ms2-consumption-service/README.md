# MS2 — Consumption & Bills

Meter readings, consumption history, STEG bill estimates and forecasts.

| Setting | Agreed value |
|---|---|
| Owner | Ryma |
| Service name | `ms2-consumption-service` |
| Port | 8082 |
| Technology | Java 17 / Spring Boot 3.3.x / Maven |
| Database | MySQL `consumption_db` (exclusive to this service) |
| Package | `tn.esprit.wattwise.consumption` |

## Session 4 handoff

Documentation placeholder only; no application or database configuration is implemented here yet.

- Create the project and implement `GET /api/v1/hello`.
- Add Spring Web, Eureka Discovery Client, Config Client, Actuator, the database driver and Spring Data, plus springdoc 2.6.0. Expose Swagger at `/swagger-ui.html`.
- Register with Eureka (:8761), use Config Server (:8888) where applicable, and configure the assigned database.
- Document API contracts, events and dependency failure behavior before adding business logic.
- Verify startup, the hello response, OpenAPI and discovery registration; commit from your own account.

See the [project overview](../../README.md) and [project context](../../WATTWISE_CONTEXT.md) for the full agreed design.

# MS6 — Power Cut Reporting

GPS outage reports, incident clustering and area reliability metrics.

| Setting | Agreed value |
|---|---|
| Owner | Yasmine |
| Service name | `ms6-outage-service` |
| Port | 8086 |
| Technology | Java 17 / Spring Boot 3.3.x / Maven |
| Database | MongoDB `outage_db` (exclusive to this service) |
| Package | `tn.esprit.wattwise.outage` |

## Session 4 handoff

Documentation placeholder only; no application or database configuration is implemented here yet.

- Create the project and implement `GET /api/v1/hello`.
- Add Spring Web, Eureka Discovery Client, Config Client, Actuator, the database driver and Spring Data, plus springdoc 2.6.0. Expose Swagger at `/swagger-ui.html`.
- Register with Eureka (:8761), use Config Server (:8888) where applicable, and configure the assigned database.
- Document API contracts, events and dependency failure behavior before adding business logic.
- Verify startup, the hello response, OpenAPI and discovery registration; commit from your own account.

See the [project overview](../../README.md) for the agreed architecture, technologies and team responsibilities.

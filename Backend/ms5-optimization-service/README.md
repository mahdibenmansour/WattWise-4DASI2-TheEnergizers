# MS5 — Smart Optimization

Consumption analysis, anomalies, efficiency scores, personalized recommendations, simulations and saving plans.

| Setting | Agreed value |
|---|---|
| Owner | Selim |
| Service name | `ms5-optimization-service` |
| Port | 8085 |
| Technology | Java 17 / Spring Boot 3.3.x / Maven |
| Database | MongoDB `optimization_db` (exclusive to this service) |
| Package | `tn.esprit.wattwise.optimization` |

## Session 4 handoff

Documentation placeholder only; no application or database configuration is implemented here yet.

- Create the project and implement `GET /api/v1/hello`.
- Add Spring Web, Eureka Discovery Client, Config Client, Actuator, the database driver and Spring Data, plus springdoc 2.6.0. Expose Swagger at `/swagger-ui.html`.
- Register with Eureka (:8761), use Config Server (:8888) where applicable, and configure the assigned database.
- Document API contracts, events and dependency failure behavior before adding business logic.
- Verify startup, the hello response, OpenAPI and discovery registration; commit from your own account.

See the [project overview](../../README.md) for the agreed architecture, technologies and team responsibilities.

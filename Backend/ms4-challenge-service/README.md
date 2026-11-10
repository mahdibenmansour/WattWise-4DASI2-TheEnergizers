# MS4 — Challenges & Rewards

Energy-saving challenges, participation, progress, points, badges and leaderboards.

| Setting | Agreed value |
|---|---|
| Owner | Arfaoui |
| Service name | `ms4-challenge-service` |
| Port | 8084 |
| Technology | Java 17 / Spring Boot 3.3.x / Maven |
| Database | MongoDB `challenge_db` (exclusive to this service) |
| Package | `tn.esprit.wattwise.challenge` |

## Session 4 handoff

Documentation placeholder only; no application or database configuration is implemented here yet.

- Create the project and implement `GET /api/v1/hello`.
- Add Spring Web, Eureka Discovery Client, Config Client, Actuator, the database driver and Spring Data, plus springdoc 2.6.0. Expose Swagger at `/swagger-ui.html`.
- Register with Eureka (:8761), use Config Server (:8888) where applicable, and configure the assigned database.
- Document API contracts, events and dependency failure behavior before adding business logic.
- Verify startup, the hello response, OpenAPI and discovery registration; commit from your own account.

See the [project overview](../../README.md) and [project context](../../WATTWISE_CONTEXT.md) for the full agreed design.

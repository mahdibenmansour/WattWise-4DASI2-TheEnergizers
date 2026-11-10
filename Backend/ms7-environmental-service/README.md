# MS7 — Environmental Impact

Carbon footprint, avoided emissions without double counting, community statistics and sustainability reports.

| Setting | Agreed value |
|---|---|
| Owner | Whole team (presented by Selim) |
| Service name | `ms7-environmental-service` |
| Port | 8087 |
| Technology | Python / FastAPI |
| Database | PostgreSQL `environmental_db` (exclusive to this service) |

## Session 4 handoff

Documentation placeholder only; no application or database configuration is implemented here yet.

- Create the project and implement `GET /api/v1/hello`.
- Add `requirements.txt`, a Dockerfile, built-in OpenAPI at `/docs`, and Eureka registration using `py_eureka_client`.
- Register with Eureka (:8761), use Config Server (:8888) where applicable, and configure the assigned database.
- Document API contracts, events and dependency failure behavior before adding business logic.
- Verify startup, the hello response, OpenAPI and discovery registration; commit from your own account.
- Every member contributes at least one personal commit to MS7. Selim leads the skeleton and presents the service.

See the [project overview](../../README.md) and [project context](../../WATTWISE_CONTEXT.md) for the full agreed design.

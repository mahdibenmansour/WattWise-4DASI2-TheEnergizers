# MS7 — Energy Marketplace

Energy marketplace listings, offers and household exchanges.

| Setting | Agreed value |
|---|---|
| Owner | Mahdi |
| Service name | `ms7-marketplace-service` |
| Port | 8087 |
| Technology | Python / FastAPI |
| Database | PostgreSQL `marketplace_db` (exclusive to this service) |

## Session 4 handoff

Documentation placeholder only; no application or database configuration is implemented here yet.

- Create the project and implement `GET /api/v1/hello`.
- Add `requirements.txt`, a Dockerfile, built-in OpenAPI at `/docs`, and Eureka registration using `py_eureka_client`.
- Register with Eureka (:8761), use Config Server (:8888) where applicable, and configure the assigned database.
- Document API contracts, events and dependency failure behavior before adding business logic.
- Verify startup, the hello response, OpenAPI and discovery registration; commit from your own account.
- Mahdi owns the skeleton, implementation and presentation of MS7. Every member also contributes to shared MS1.

See the [project overview](../../README.md) for the agreed architecture, technologies and team responsibilities.

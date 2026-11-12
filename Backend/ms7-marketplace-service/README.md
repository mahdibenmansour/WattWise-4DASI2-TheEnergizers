# MS7 — Energy Marketplace

Standalone Node.js + NestJS + TypeScript starter, owned by Mahdi, on port **8087**.
This implementation supersedes the earlier FastAPI placeholder for MS7.
No database, Keycloak, Eureka or other services are required for this starter.

## Setup and run

Install Node.js 22 or newer with npm, then from the repository root:

```bash
cd Backend/ms7-marketplace-service
npm ci
npm run build
npm start
```

For development with automatic restart, use `npm run start:dev` instead.
Stop the server with Ctrl+C.

## Try the API

- Hello: http://localhost:8087/api/v1/hello
- Swagger UI: http://localhost:8087/docs
- OpenAPI JSON: http://localhost:8087/docs-json

In Swagger UI, expand **GET /api/v1/hello**, click **Try it out**, then **Execute**.
Expect HTTP 200 with:

```json
{"message":"Hello from Marketplace"}
```

You can also run `curl http://localhost:8087/api/v1/hello`.
Marketplace business logic, persistence and platform integrations are future work.

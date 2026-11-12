# MS4 — Challenges & Rewards

Energy-saving challenges, participation, progress, points, badges and leaderboards.

| Setting | Value |
|---|---|
| Owner | Yassine Arfaoui |
| Service name | `ms4-challenge-service` |
| Port | 8084 |
| Technology | Java 17 · Spring Boot 3.5 · Spring Cloud 2025.0 · Maven (same template as MS3) |
| Database | MongoDB `challenge_db` (used only by MS4) |
| Package | `tn.esprit.wattwise.challenge` |

## Status (session 4)

Project created: it starts, registers in Eureka, reads the Config Server when it runs, and exposes check endpoints and Swagger. Business logic comes after the design is validated.

## Run it

```bash
cd Backend/ms4-challenge-service
./mvnw spring-boot:run            # Windows: mvnw.cmd spring-boot:run
```

- Check endpoints: `GET` and `POST` http://localhost:8084/api/v1/hello
- Swagger UI: http://localhost:8084/swagger-ui.html
- Health: http://localhost:8084/actuator/health
- Through the gateway: http://localhost:8080/ms4-challenge-service/api/v1/hello (depends on the gateway routing)

`POST /api/v1/hello` with `{"message": "hello"}` returns `201` and the same message.

Without Eureka or the Config Server running, the service still starts (it only logs a connection warning). With MongoDB running, start it with the `db` profile: `./mvnw spring-boot:run -Dspring-boot.run.profiles=db`.

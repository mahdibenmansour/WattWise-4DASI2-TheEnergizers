# API Gateway

Spring Cloud Gateway: the single public entry point. It routes requests to the services found in Eureka and, when security is on, validates Keycloak JWTs.

- **Owner:** Farouk
- **Port:** 8080
- **Technology:** Java 17 · Spring Boot 3.5 · Spring Cloud Gateway (WebFlux) 2025.0

## Run it

Start `discovery-service` first, then:

```bash
cd Backend/api-gateway
./mvnw spring-boot:run
```

- Routes: http://localhost:8080/actuator/gateway/routes
- Example: http://localhost:8080/ms3/api/v1/hello

## Routes

| Path | Service |
|---|---|
| `/api/v1/households/**` | ms1-household-service |
| `/api/v1/consumption/**`, `/api/v1/bills/**`, `/api/v1/tariffs/**` | ms2-consumption-service |
| `/api/v1/solar/**` | ms3-solar-service |
| `/api/v1/challenges/**`, `/api/v1/rewards/**` | ms4-challenge-service |
| `/api/v1/assistant/**` | ms5-optimization-service |
| `/api/v1/outages/**` | ms6-outage-service |
| `/api/v1/marketplace/**` | ms7-marketplace-service |
| `/msN/**` | service N, prefix removed (session 4 checks) |

Routes use `lb://<spring.application.name>`, so **each service must keep the name above** in its configuration.

## Security

`SECURITY_ENABLED=false` by default for the session 4 demo. With `SECURITY_ENABLED=true`, every request except health needs a valid JWT from the Keycloak realm `wattwise` (`KEYCLOAK_ISSUER_URI`, default `http://localhost:8180/realms/wattwise`).

See the [root README](../../README.md).

# Config Server

Centralised configuration: services read their settings from here at startup (`spring.config.import=optional:configserver:...`).

- **Owner:** Farouk
- **Port:** 8888
- **Technology:** Java 17 · Spring Boot 3.5 · Spring Cloud Config Server 2025.0

## Run it

Start it after `discovery-service`:

```bash
cd Backend/config-server
./mvnw spring-boot:run
```

Check: http://localhost:8888/ms3-solar-service/default

## Where the settings live

`src/main/resources/config-repo/` (the `native` profile):

- `application.yml`: settings shared by every service (Eureka, actuator, Swagger paths)
- `<service-name>.yml`: settings of one service, for example `ms3-solar-service.yml`

**Service owners:** add your own `<spring.application.name>.yml` here. Never put passwords in these files; use environment variables. Later this folder can move to its own Git repository.

See the [root README](../../README.md).

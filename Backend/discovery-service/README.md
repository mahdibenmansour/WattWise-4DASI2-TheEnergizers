# Discovery Service (Eureka)

Service registry: every service registers here, and the gateway and OpenFeign clients find services by name (`lb://...`).

- **Owner:** Farouk
- **Port:** 8761
- **Technology:** Java 17 · Spring Boot 3.5 · Spring Cloud Netflix Eureka Server 2025.0

## Run it

Start this one first:

```bash
cd Backend/discovery-service
./mvnw spring-boot:run
```

Dashboard: http://localhost:8761 — registered services appear under "Instances currently registered".

See the [root README](../../README.md).

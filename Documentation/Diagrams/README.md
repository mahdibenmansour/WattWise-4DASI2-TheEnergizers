# Diagrams

Store architecture, use-case, per-service class diagrams and sequence diagrams in the matching subfolders. Add PNG exports and editable sources when available. Label the global architecture with the project name and team members.

Architecture should show Angular, the public gateway, private services with separate databases, Keycloak, Eureka, Config Server and RabbitMQ. Diagram images are pending.

Use the [service ownership table](../Technical-Document/README.md#service-decomposition-and-ownership) for all labels. Show MS1 User, Household & Meter Management as shared by the team and MS6 Power Cut Reporting as owned by Yasmine. Label MS7 as Energy Marketplace, owned by Mahdi, using Python / FastAPI on port 8087 with PostgreSQL `marketplace_db`. MS2–MS5 assignments remain unchanged.

Use `ms7-marketplace-service` for MS7 source/export names. Its planned domain is listings, offers and household exchanges; its synchronous dependency is MS1 for household ownership validation. Align new class and sequence diagrams with the [MS7 handoff](../../Backend/ms7-marketplace-service/README.md).

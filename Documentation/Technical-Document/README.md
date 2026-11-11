# Technical document

Yassine Arfaoui coordinates assembly. Add the final Word or LaTeX technical document here.

Cover the topic, actors and features, seven services, entities and database choices, API contracts, sync/async interactions and failure behavior, architecture, technologies, ownership, Git organization, schedule and risks. Use the [project overview](../../README.md) as the architecture and ownership reference.

## Service decomposition and ownership

Use these assignments consistently in the technical document, diagrams and presentation:

| Service | Owner | Backend folder |
|---|---|---|
| MS1 User, Household & Meter Management | Shared by the team | `Backend/ms1-household-service/` |
| MS2 Consumption & Bills | Ryma | `Backend/ms2-consumption-service/` |
| MS3 Solar Management | Farouk | `Backend/ms3-solar-service/` |
| MS4 Challenges & Rewards | Yassine Arfaoui | `Backend/ms4-challenge-service/` |
| MS5 Smart Optimization | Selim | `Backend/ms5-optimization-service/` |
| MS6 Power Cut Reporting | Yasmine | `Backend/ms6-outage-service/` |
| MS7 Energy Marketplace | Mahdi | `Backend/ms7-marketplace-service/` |

MS7 uses Python / FastAPI, port **8087**, and its own PostgreSQL database, `marketplace_db`. Its domain covers marketplace listings, offers and household exchanges. Document its planned ownership validation through MS1 and its API/event contracts before implementation. The previous environmental-impact service is superseded by Energy Marketplace; use the marketplace folder and terminology in new deliverables.

MS1 remains a shared team service: every member contributes a personal commit. MS2–MS5 retain their existing assignments, technologies, ports and databases.

This repository is a documentation scaffold. This handoff does not implement backend logic or claim that the planned services are running.

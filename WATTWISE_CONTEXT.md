# WattWise TN — Project Context for AI Assistants

> **How to use this file:** put it at the root of the Git repository (you can also save it as `CLAUDE.md` or `AGENTS.md`), or paste it at the start of a conversation with your AI assistant. It describes the project, the professor's requirements, the decisions the team already made, and the conventions everyone must follow. **The AI must respect the decisions below and not invent different ports, names, databases or endpoints.**

---

## 1. Course & milestone

- **School / module:** ESPRIT, *Distributed Web Applications (AWD / MT-41)*, class **4DAS**, academic year 2026–2027. Teacher: Dr. Badia Bouhdid.
- **Mandatory theme:** *Environmental protection in Tunisia.*
- **Current milestone:** **Intermediate Validation 1 — Session 4** (week of 9 November 2026).
- **Team:** 6 students. Each owns one microservice. A 7th microservice is shared by the whole team.

### What the professor expects for Session 4 (from her email)
- All six members develop their microservice in **Java / Spring Boot**.
- The team must **also integrate one microservice in a different technology** (e.g. FastAPI/Python) → **our choice: MS7 in FastAPI**.
- **No business logic yet.** Each microservice only needs a **basic working example**: a simple REST endpoint such as `GET /hello`, documented in Swagger, proving the service runs and is reachable.
- The goal of Session 4 is to **validate the design** (topic, decomposition, data, communication, architecture), the **Git organization**, and a **fair task distribution**.

---

## 2. Required deliverables (from "AWD Required Deliverables" + evaluation grid)

### Git repository (public, teacher added as collaborator)
```
wattwise-tn-4das-gX/            # X = group number
├── Backend/                    # one project per microservice
│   ├── discovery-service/      # Eureka (:8761)
│   ├── api-gateway/            # Spring Cloud Gateway (:8080)
│   ├── config-server/          # Spring Cloud Config (:8888)
│   ├── ms1-household-service/      # Yesmina  :8081
│   ├── ms2-consumption-service/    # Ryma     :8082
│   ├── ms3-solar-service/          # Farouk   :8083
│   ├── ms4-challenge-service/      # Arfaoui  :8084
│   ├── ms5-optimization-service/   # Selim    :8085
│   ├── ms6-outage-service/         # Mahdi    :8086
│   └── ms7-environmental-service/  # Whole team, FastAPI :8087
├── Frontend/                   # Angular project, one module folder per microservice
├── Documentation/
│   ├── Presentation/           # Session 4 slides (.pptx / .pdf)
│   └── Diagrams/               # architecture, use case, class diagrams (PNG)
├── docker-compose.yml          # PostgreSQL, MySQL, MongoDB, RabbitMQ, Keycloak
└── README.md                   # topic, members, architecture, how to run
```

### Technical document (Word or LaTeX)
Must contain everything in the presentation, in more detail: topic description, actors & features, microservice breakdown, data of each service, interactions between services, global architecture diagram, technologies, task distribution, Git organization, schedule, risks.

### Presentation (10–15 min, then ~10 min of questions)
- **Group part:** idea & problem, actors & use cases, main features, global architecture (gateway, security, data, communication), microservices + database choices, technologies, task distribution, Git & project management tool, provisional schedule & risks.
- **Individual part (max ~1 min per student, presented ONLY by the owner):** class diagram, interactions with other services (sync + async examples), API contract (main endpoints, response codes, events).
- The shared microservice (MS7) is presented by **one** member (Selim).

### Blackboard (submitted by one member)
Git repo link, technical document, presentation, architecture diagram (project name + team members).

### Grading (20 pts)
| Code | Team criterion (12 pts) | Pts |
|---|---|---|
| E1 | Topic & functional analysis (real problem, actors, use cases, realistic scope) | 2 |
| E2 | Breakdown into microservices (bounded contexts, no table-based split, + 1 advanced-technology MS) | 2 |
| E3 | Data & communication (one DB per service, justified; sync OpenFeign / async, justified) | 2 |
| E4 | Technical architecture (front, Gateway, Eureka, Config, Keycloak, MS, DBs, Docker) | 2 |
| E5 | Git repo & documentation (structure, projects created, README, commits from everyone, PM tool) | 2 |
| E6 | Oral presentation (clear, timed, speaking shared) | 2 |

| Code | Individual criterion (8 pts) | Pts |
|---|---|---|
| I1 | Design of their MS: responsibilities, entities, data model, justified DB, REST API (URIs, methods, status codes) | 3 |
| I2 | Interactions: called/calling services, data exchanged, sync/async justified, **behavior if a called service is down** | 2 |
| I3 | Project created in `Backend/`, compiles and starts, **personal commits** | 1 |
| I4 | Answers to questions (bounded context, DB per service, Gateway & Eureka, sync vs async) | 2 |

⚠️ A microservice not presented by its owner = 0 for I1–I3. Unjustified absence = 0. Inaccessible repo = 0 for E5.

---

## 3. The project: WattWise TN

**Tagline:** Smart & sustainable energy platform for Tunisian households.

### Problem (figures from our project file — verify sources before presenting)
- ~95% of Tunisia's electricity comes from natural gas (STEG thermal plants) → ~**0.45 kg CO₂e per kWh**.
- Summer heatwaves (>40 °C) → air-conditioning peaks → grid overloads and load shedding (*délestage*).
- Households only get a bimonthly paper bill: no visibility on spikes, wasteful appliances, or carbon cost.
- Tunisia has >3,000 hours of sunshine/year, yet rooftop solar is underused and hard to monitor.

### Solution
A distributed web platform that helps households **see** their consumption, **reduce** it, **adopt solar**, cope with **outages**, and **prove** their environmental impact (kWh → kg CO₂e avoided).

### Actors
- **Administrator:** manages platform configuration and emission factors (kg CO₂e/kWh), oversees data integrity.
- **Household Owner:** main account; manages household, members, meters, appliances, readings, solar installation, saving plans, sustainability reports. Can do everything a member can.
- **Household Member:** views consumption history, joins challenges, reports outages, views community sustainability.

### "Ahmed's story" (pitch for MS7)
Ahmed has used WattWise TN for three months: he consumes less, installed solar panels and completed challenges. He asks: *"I'm saving electricity and money, but what is my actual contribution to the environment?"* MS7 answers: 60 kWh less from the grid × 0.45 = **27 kg CO₂e avoided**, plus solar self-consumption (never counted twice).

---

## 4. Architecture (fixed decisions)

- **Client:** Angular → only talks to the **API Gateway** (:8080), the single public entry point.
- **Security:** Keycloak (OAuth2 / OIDC, JWT checked at the Gateway).
- **Discovery:** Netflix Eureka (:8761) — every service registers, including MS7.
- **Configuration:** Spring Cloud Config Server (:8888), Git-backed.
- **Sync communication:** OpenFeign + **Resilience4j** circuit breaker + fallback on every Feign client.
- **Async communication:** **RabbitMQ** domain events.
- **Persistence:** polyglot, **one database per service, never shared**.
- **Deployment:** everything in Docker containers via `docker-compose.yml`.
- **Docs:** Swagger/OpenAPI on every service (springdoc for Spring, built-in `/docs` for FastAPI).
- **Project management:** Jira (or Trello — the team must pick one and keep it consistent).

### Ports & databases
| Component | Port | Database | Owner |
|---|---|---|---|
| API Gateway | 8080 | — | Farouk |
| Eureka | 8761 | — | Farouk |
| Config Server | 8888 | — | Farouk |
| MS1 Household | 8081 | PostgreSQL `household_db` | Yesmina |
| MS2 Consumption & Bills | 8082 | MySQL `consumption_db` | Ryma |
| MS3 Solar | 8083 | PostgreSQL `solar_db` | Farouk |
| MS4 Challenges & Rewards | 8084 | MongoDB `challenge_db` | Arfaoui |
| MS5 Smart Optimization | 8085 | MongoDB `optimization_db` | Selim |
| MS6 Outage Analysis | 8086 | MongoDB `outage_db` | Mahdi |
| MS7 Environmental Impact | 8087 | PostgreSQL `environmental_db` | Whole team (FastAPI) |

### Database justifications
| Service | DB | Why |
|---|---|---|
| MS1 | PostgreSQL | Strict relations household → meters, appliances, members; FK + ACID |
| MS2 | MySQL | Structured, transactional, sequential readings and tiered billing |
| MS3 | PostgreSQL | Exact NUMERIC for kWh and money; relational installations/panels |
| MS4 | MongoDB | Flexible documents for varied challenges and badge rules |
| MS5 | MongoDB | Polymorphic recommendations, anomalies, simulations |
| MS6 | MongoDB | GeoJSON + 2dsphere index → radius queries to cluster outage reports |
| MS7 | PostgreSQL | Time-based monthly aggregation for reports and KPIs |

### Synchronous calls (OpenFeign + Resilience4j)
| Caller → Called | Purpose |
|---|---|
| MS2 → MS1 | Check the meter belongs to the household |
| MS3 → MS1 | Check the household exists |
| MS5 → MS2, MS3 | Consumption history and solar data |
| MS7 → MS1, MS2, MS3 | Data for reports |

### Asynchronous events (RabbitMQ)
| Event | Publisher | Consumers |
|---|---|---|
| `ApplianceAdded` | MS1 | MS5 (profile refresh) |
| `ConsumptionCalculated` | MS2 | MS4 (challenge progress), MS7 (emissions) |
| `SolarProductionRecorded` | MS3 | MS7 (avoided emissions) |
| `OutageIncidentUpdated` | MS6 | Community alerts (later sessions) |

**Rule of thumb:** sync when the user is waiting for the answer; async for state changes others react to (must not block, queue keeps events if a consumer is down).

---

## 5. Microservice specifications

All URIs are prefixed with `/api/v1`. Status codes are the team's design; each owner must be able to justify them.

### MS1 — Household Management (Yesmina) · PostgreSQL · :8081
- **Role:** master data of every household; other services refer to a household only by its id.
- **Entities:** `Household` (id, name, address, city, surfaceArea, numberOfOccupants, housingType, ownerUserId) composed of `ElectricityMeter` (meterNumber, meterType, installationDate, status), `Appliance` (name, category, powerWatts, quantity, estimatedHoursPerDay), `HouseholdMember` (userId, role, status, joinedAt), `HouseholdEnergyProfile` (estimatedDailyKWh, estimatedMonthlyKWh, applianceCount, lastCalculatedAt).
- **Endpoints:** `POST /households` (201, 400) · `GET /households/{id}` (200, 404) · `POST /households/{id}/meters` (201, 404, 409 duplicate meter) · `POST /households/{id}/appliances` (201, 404) · `GET /households/{id}/energy-profile` (200, 404).
- **Interactions:** called sync by MS2, MS3, MS7; publishes `ApplianceAdded`.
- **If down:** MS1 calls nobody so it is never blocked; callers protect themselves with circuit breakers.

### MS2 — Consumption & Bills (Ryma) · MySQL · :8082
- **Role:** meter readings → consumed kWh per period, STEG tiered bill estimates, forecasts.
- **Entities:** `MeterReading`, `ConsumptionRecord` (derived from 2 readings), `TariffPlan` composed of `TariffTier`, `BillEstimate`, `ConsumptionForecast`.
- **Endpoints:** `POST /consumption/readings` (201, 400 if lower than previous reading, 404) · `GET /consumption/history/{householdId}` (200, 404) · `POST /bills/estimate/{recordId}` (201, 404) · `GET /consumption/forecast/{householdId}` (200, 404).
- **Interactions:** sync → MS1 (meter ownership); called by MS5, MS7; publishes `ConsumptionCalculated`.
- **If MS1 down:** circuit opens, reading refused with 503 (or stored as "pending validation").

### MS3 — Solar Management (Farouk) · PostgreSQL · :8083
- **Role:** rooftop PV installations, production logs, self-consumed vs exported balance, financial savings.
- **Entities:** `SolarInstallation` composed of `SolarPanel`, `ProductionRecord`, `SolarEnergyBalance`; `SolarSavings`.
- **Endpoints (base `/api/v1/solar`):** `POST /installations` (201, 400, 404) · `POST /installations/{id}/production` (201, 400, 404) · `GET /installations/{id}/balance` (200, 404) · `GET /installations/{id}/savings` (200, 404).
- **Interactions:** sync → MS1 (household check); called by MS5, MS7; publishes `SolarProductionRecorded`.
- **If MS1 down:** new installations refused (503), reading existing data still works.

### MS4 — Challenges & Rewards (Arfaoui) · MongoDB · :8084
- **Role:** gamified energy-saving challenges, progress, points, badges, leaderboard.
- **Entities:** `Challenge` composed of `Participation`; `Reward`, `Badge`, `EarnedBadge`, `LeaderboardEntry`.
- **Endpoints:** `GET /challenges/active` (200) · `POST /challenges/{id}/join` (201, 404, 409 already joined) · `GET /challenges/leaderboard` (200) · `GET /rewards/household/{householdId}` (200, 404).
- **Interactions:** fully async — consumes `ConsumptionCalculated`; no sync calls.
- **If MS2 down:** events wait in the RabbitMQ queue, processed later; nothing lost.

### MS5 — Smart Optimization (Selim) · MongoDB · :8085
- **Role:** consumption analysis, anomaly detection, efficiency score, personalized recommendations, what-if simulations, saving plans.
- **Entities:** `ConsumptionAnalysis` (composes `EnergyAnomaly`, generates `Recommendation`), `EfficiencyScore`, `SavingSimulation`, `EnergySavingPlan` composed of `SavingAction`.
- **Endpoints (base `/api/v1/optimization`):** `GET /analysis/{householdId}` · `GET /anomalies/{householdId}` · `GET /recommendations/{householdId}` (200, 404) · `POST /simulations` · `POST /plans` (201, 400).
- **Interactions:** sync → MS2 (history), MS3 (solar); consumes `ApplianceAdded`.
- **If MS2 down:** Resilience4j fallback returns generic recommendations based on MS1's appliance profile (no 500).

### MS6 — Outage Analysis (Mahdi) · MongoDB · :8086
- **Role:** crowdsourced outage reports with GPS, clustering into incidents, reliability metrics per area.
- **Entities:** `AffectedArea`, `OutageReport`, `OutageIncident` (groups reports), `IncidentCorrelation` (time window + distance threshold), `ReliabilityMetric`.
- **Endpoints (base `/api/v1/outages`):** `POST /reports` (201, 400) · `GET /active` (200) · `GET /areas/{id}/reliability` (200, 404).
- **Interactions:** no sync calls; publishes `OutageIncidentUpdated`.
- **If a consumer is down:** RabbitMQ keeps the events.

### MS7 — Environmental Impact (whole team, presented by Selim) · **FastAPI (Python)** · PostgreSQL · :8087
- **Role:** converts kWh into kg CO₂e: household footprint, avoided emissions (savings + solar, no double counting), community stats (aggregated only, privacy), monthly sustainability reports.
- **Entities:** `EmissionFactor` (valueKgCO2ePerKWh = 0.45 by default, source, validFrom, validTo, active; admin-managed), `EnvironmentalBaseline`, `EnvironmentalImpactRecord`, `RenewableImpactRecord`, `EnergySavingImpact`, `SustainabilityReport`.
- **Endpoints (base `/api/v1/environmental`):** `GET /footprint/{householdId}` (200, 404) · `GET /savings/{householdId}` (200, 404) · `GET /community-sustainability` (200) · `POST /reports/{householdId}` (201, 404).
- **Interactions:** consumes `ConsumptionCalculated`, `SolarProductionRecorded`; sync HTTP → MS1, MS2, MS3 for report data; registers in Eureka with `py_eureka_client`.
- **If MS2 down:** report falls back to the stored `EnvironmentalBaseline`.
- **Team rule:** every member makes **at least one commit** to MS7. Suggested split: Selim = skeleton + `/hello` + Eureka; Farouk = Dockerfile + compose entry; Yesmina = `EmissionFactor` model; Ryma = stub `GET /footprint/{householdId}`; Arfaoui = stub `GET /community-sustainability`; Mahdi = README section + `requirements.txt`.

---

## 6. Task distribution

| Member | Microservice | Shared responsibility | Presents (slides) |
|---|---|---|---|
| Yesmina | MS1 Household | Problem statement, actors, use cases | Cover, problem, actors, MS1 |
| Ryma | MS2 Consumption | Decomposition, Angular frontend setup | Solution, decomposition, MS2 |
| Farouk | MS3 Solar | Architecture: Gateway, Eureka, Config, docker-compose, draw.io diagram | Architecture, technologies, MS3 |
| Arfaoui | MS4 Challenges | Consolidated class diagrams, technical document assembly | Data & databases, MS4, closing |
| Selim | MS5 Optimization | Sync/async communication table, MS7 lead | Communication, MS5, Ahmed's story, MS7 |
| Mahdi | MS6 Outage | Git repo, README, Jira board, Blackboard submission | MS6, tasks, Git, schedule, risks |

### Order of work
1. **Mahdi** creates the public repo, adds the teacher as collaborator, pushes the folder structure.
2. **Farouk** adds Eureka, Gateway, Config Server and `docker-compose.yml`.
3. **Everyone in parallel** creates their own microservice (setup below) with `/hello`.
4. **Selim** creates the MS7 FastAPI skeleton; then everyone commits to it.
5. Owners send their slide + class diagram; Arfaoui assembles the document; rehearse once (10–15 min).

---

## 7. Session 4 implementation template (what the AI should generate)

### Spring Boot microservice (MS1–MS6)
- Maven, **Java 17**, **Spring Boot 3.3.x** (same version for all), group `tn.esprit.wattwise`, artifact = folder name, package `tn.esprit.wattwise.<domain>` (household, consumption, solar, challenge, optimization, outage).
- Dependencies: Spring Web, Eureka Discovery Client, Config Client, Actuator, the DB driver (+ Spring Data JPA or Spring Data MongoDB), and:
```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.6.0</version>
</dependency>
```
- `application.yml`:
```yaml
spring:
  application:
    name: ms1-household-service        # your service name
  config:
    import: optional:configserver:http://localhost:8888
server:
  port: 8081                           # your port
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
# + your datasource (URL/user/password from docker-compose)
```
- `HelloController.java`:
```java
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Validation 1")
public class HelloController {
  @Value("${spring.application.name}")
  private String app;

  @GetMapping("/hello")
  @Operation(summary = "Test endpoint")
  public Map<String, Object> hello() {
    return Map.of("service", app, "status", "UP", "message", "Hello from " + app);
  }
}
```
- **Check:** `localhost:<port>/swagger-ui.html`, `localhost:<port>/api/v1/hello`, service visible on `localhost:8761`.
- A JPA/Mongo service won't start without its DB → run `docker compose up -d` first.

### FastAPI microservice (MS7)
```bash
pip install fastapi uvicorn py_eureka_client
uvicorn main:app --port 8087 --reload     # Swagger at localhost:8087/docs
```
```python
from contextlib import asynccontextmanager
from fastapi import FastAPI
import py_eureka_client.eureka_client as eureka

@asynccontextmanager
async def lifespan(app):
    await eureka.init_async(eureka_server="http://localhost:8761/eureka",
                            app_name="ms7-environmental-service", instance_port=8087)
    yield
    await eureka.stop_async()

app = FastAPI(title="MS7 Environmental Impact", lifespan=lifespan)

@app.get("/api/v1/hello")
def hello():
    return {"service": "ms7-environmental-service", "status": "UP"}
```
Also add `requirements.txt` and a `Dockerfile`.

### Git rules
- Commit from **your own** GitHub account (graded: personal commits).
- Clear messages, e.g. `feat(ms2): add hello endpoint`.
- Only touch your own folder (except MS7). Pull before you push.

---

## 8. Provisional schedule & risks

**Schedule (adjust to the official calendar):** S4 design + `/hello` → S5–S6 entities, databases, REST → S7 Gateway routes, OpenFeign, Resilience4j → S8 RabbitMQ events → S9 Keycloak security → S10 Docker deployment, frontend, final demo.

**Risks:** FastAPI ↔ Eureka/RabbitMQ integration (High → prototype early with `py_eureka_client` + `pika`) · cascading failures (High → circuit breakers + async) · many containers (Medium → one compose file, shared ports table) · merge conflicts / uneven commits (Medium → one folder per owner, weekly board review) · inaccurate emission factor (Low → admin-managed factor with source and validity dates).

---

## 9. Material already produced
- **PDF guide:** `WattWise_TN_Session4_Microservices_Guide.pdf` — one page per microservice (setup, DB, entities, endpoints, interactions, failure behavior, checklist).
- **Session 4 slide deck** (22 slides, with speaker notes for each presenter), made in Claude. Export to PPTX/PDF into `Documentation/Presentation/`.
- **Class diagrams** for MS1–MS7, the use-case diagram and a global architecture diagram exist as PNGs → put them in `Documentation/Diagrams/`. The architecture diagram should be redrawn in draw.io in the professor's style: client side, public network with the API Gateway, private network with services + DBs (Docker badges), RabbitMQ between async services, Keycloak/Eureka/Config at the bottom.
- The professor's documentation examples use a small HTML site (`indexDoc.html` + pages: class diagram, microservices architecture, class diagram by domain, Keycloak principle, async communication) in a plum/lilac Beamer-like style.

### Still to do / to fill in
- Group number (`gX`), GitHub link, Jira/Trello board link.
- Confirm sources for the problem figures (95%, 0.45 kg CO₂e/kWh, 3,000 h of sunshine).
- Technical document (Word/LaTeX), README, docker-compose, Angular init, draw.io diagram.

---

## 10. Instructions for the AI assistant
1. Stay within this design: same service names, ports, databases, endpoints, events and owners.
2. For Session 4, generate **only** project skeletons + `/hello` + Swagger + Eureka registration. No business logic unless explicitly asked.
3. Spring services: Java 17, Spring Boot 3.3.x, package `tn.esprit.wattwise.<domain>`. MS7: FastAPI.
4. Never make two services share a database. Cross-service data goes through REST (OpenFeign + Resilience4j) or RabbitMQ events.
5. When helping a student prepare their part, focus on what is graded: their class diagram, endpoints with status codes, interactions (sync/async with justification), and behavior when a called service is down.
6. Help the student **understand and explain** their choices: they will be questioned individually on bounded contexts, database per service, Gateway & Eureka, and sync vs async communication.

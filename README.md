# Person Service

Tenant-aware Spring Boot service for managing persons, teams, memberships and communication references. The service exposes the versioned REST API under `/api/v1`, persists data via Spring Data JPA/Flyway, and documents every controller, DTO and enum through Springdoc OpenAPI. It is designed to run locally without any Docker requirement as soon as a compatible database is reachable.

## Local Development

### Overview
- Java 21 via the provided Maven wrapper (`mvnw.cmd`).
- A MariaDB/MySQL-compatible database (local install, VM, container, etc.). The service does **not** depend on Docker; starting a database container is optional.
- All integration points are stubbed or mocked for local builds: JWTs are simulated, contact docking is exposed through simple IDs, and downstream services are not required.

### Required environment variables
| Variable | Purpose | Default / Notes |
| --- | --- | --- |
| `PERSON_SERVER_PORT` | HTTP port for the REST API | `8113` |
| `PERSON_MANAGEMENT_SERVER_PORT` | Management/Actuator port | `8213` |
| `PERSON_DB_HOST` | Database host | `localhost` |
| `PERSON_DB_PORT` | Database port | `3306` |
| `PERSON_DB_NAME` | Database schema | `person` |
| `PERSON_DB_USER` | Database user | `person` |
| `PERSON_DB_PASSWORD` | Database password | `person` |
| `PERSON_CONTEXT_PATH` | Base path for all endpoints | `/api/v1` |
| `PERSON_SWAGGER_UI_PATH` | Swagger UI endpoint | `/swagger-ui.html` |
| `PERSON_API_DOCS_PATH` | OpenAPI JSON path | `/v3/api-docs` |
| `PERSON_ACTUATOR_BASE_PATH` | Actuator base path | `/actuator` |
| `PERSON_JWT_JWKS_URI` | JWKS URL for OAuth2 token validation | required in prod (mocked locally) |
| `PERSON_JWT_ISSUER` | Expected JWT issuer | required in prod |
| `PERSON_JWT_AUDIENCE` | Expected JWT audience | `person-service` |
| `PERSON_JWT_CLOCK_SKEW_SECONDS` | Allowed clock skew for JWT checks | `60` |
| `PERSON_TENANT_CLAIM` | JWT claim carrying the tenant ID | `tenant_id` |
| `PERSON_COMPANY_SERVICE_BASE_URL` | Optional company validation | optional |
| `PERSON_USER_SERVICE_BASE_URL` | Optional user consistency check | optional |
| `PERSON_CONTACT_SERVICE_BASE_URL` | Contact docking endpoint | configure in prod |
| `PERSON_COMMUNICATION_SERVICE_BASE_URL` | Optional follow-up notifications | optional |
| `PERSON_IAM_SERVICE_BASE_URL` | Optional remote policy provider | optional |
| `PERSON_OUTBOX_ENABLED` | Controls the optional outbox | `false` |
| `PERSON_SWAGGER_ENABLED` | Enables Swagger UI | `true` |

Copy these into your shell or IDE run configuration before starting the service. Example PowerShell snippet:

```powershell
$env:PERSON_DB_HOST = "localhost"
$env:PERSON_DB_PORT = "3306"
$env:PERSON_DB_NAME = "person"
$env:PERSON_DB_USER = "person"
$env:PERSON_DB_PASSWORD = "person"
$env:PERSON_SERVER_PORT = "8113"
$env:PERSON_MANAGEMENT_SERVER_PORT = "8213"
```

### Starting without Docker
1. Make sure your MariaDB/MySQL instance is up and the environment variables above point to it. You can use a Docker container for the database if you want, but the service itself does not require Docker.
2. Start the Spring Boot application via the Maven wrapper:
   ```powershell
   .\mvnw.cmd -Dspring-boot.run.profiles=local spring-boot:run
   ```
   The `local` profile uses the defaults above (user `root` / blank password) and is helpful during development, but you can skip the profile and set the database credentials manually if you prefer.
3. Swagger/OpenAPI becomes available at `${PERSON_SWAGGER_UI_PATH}` and `${PERSON_API_DOCS_PATH}` once the service is running.

### Optional Docker helper
`docker/docker-compose.yml` can start a MariaDB instance that matches the defaults above. Use it only as a convenient database provider; the service does not need Docker at runtime, and tests against H2 run without any containers.

### Dependencies and documentation
Dive into [`doc/dependencies.md`](doc/dependencies.md) for the curated list of runtime/test dependencies and their purpose. Swagger/OpenAPI documentation reflects every controller, DTO and enum referenced by the REST API.

### Tests
- Run `.\mvnw.cmd -q clean test` to execute all units, controller slices, integration tests and security verifications. The suite uses embedded H2/Testcontainers for persistence and mocks JWT validation, so it never relies on external services.
- Positive and negative tests cover every exposed endpoint, and the security configuration is validated via dedicated WebMvc slices (`PersonEndpointsSecurityTest`, `TeamEndpointsSecurityTest`, etc.).

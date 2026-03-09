# Dependency overview

This project relies on Spring Boot 4.0.2 with the curated stack defined in `pom.xml`. The table below summarizes the main runtime and test dependencies that must stay aligned with the Pflichtenheft (Java 21, Spring Boot, REST, Spring Data JPA, Flyway, OpenAPI, OAuth2, MariaDB).

## Runtime dependencies
| Artifact | Purpose | Notes |
| --- | --- | --- |
| `spring-boot-starter-web` | Web MVC stack, Jackson, embedded Tomcat | Exposes REST controllers under `/api/v1`. |
| `spring-boot-starter-validation` | Bean validation support | Used for request DTO constraints. |
| `spring-boot-starter-security` | Core Spring Security | Enables OAuth2 resource-server configuration. |
| `spring-boot-starter-oauth2-resource-server` | OAuth2/JWT validation | Validates `tenant_id`, scopes and issuer per Pflichten. |
| `spring-boot-starter-data-jpa` | JPA/Hibernate persistence | Works with Flyway-managed schema; no raw JDBC. |
| `spring-boot-starter-flyway` + `flyway-mysql` | Database migrations for MariaDB/MySQL | Migration scripts (outside this scope) run before the app. |
| `org.mariadb.jdbc:mariadb-java-client` | Database driver | Runtime dependency for MariaDB/MySQL. |
| `spring-boot-starter-actuator` | Management endpoints | Scoped via `PERSON_MANAGEMENT_SERVER_PORT`. |
| `springdoc-openapi-starter-webmvc-ui` | OpenAPI/Swagger UI | Documents controllers, DTOs and enums per Pflichten. |
| `lombok` + `mapstruct` + `lombok-mapstruct-binding` | Compile-time convenience | Enables MapStruct mappers and reduces boilerplate. |
| `spring-boot-devtools` | Optional developer tooling | Runtime scope, optional auto-restart assistance. |

## Testing dependencies
| Artifact | Purpose | Scope |
| --- | --- | --- |
| `spring-boot-starter-test` | Unit and integration testing essentials | `test` |
| `spring-boot-starter-webmvc-test` | Lightweight MVC testing slices | `test` |
| `spring-security-test` | Spring Security test helpers (`@WithMockUser`, etc.) | `test` |
| `com.h2database:h2` | Embedded H2 for isolated DB tests | `test` (used via profile `test`) |
| `org.testcontainers:junit-jupiter`, `testcontainers`, `mariadb` | Optional Testcontainers support | `test` (BOM drives versions) |

## BOM and versions
- `org.testcontainers:testcontainers-bom` (imported in `dependencyManagement`) keeps `testcontainers` artifacts synchronized.
- Property-driven versions (`springdoc.version`, `mapstruct.version`, `testcontainers.version`) live inside `pom.xml`.

Refer to `pom.xml` for the exact versions; this document highlights the dependencies that need to match the Pflichtenheft (JPA-only persistence, JWT validation, structured logging/outbox readiness, no JDBC templates).

## Optional service dependencies

| Service | Environment variable | Notes |
| --- | --- | --- |
| `company-service` | `PERSON_COMPANY_SERVICE_BASE_URL` | Soft dependency used for optional company-status validations; the service remains healthy even if the endpoint cannot be reached. |
| `user-service` | `PERSON_USER_SERVICE_BASE_URL` | Optional consistency check to ensure `personId` references in the user database remain valid; timeouts should fall back to warning logs, not startup failures. |
| `contact-service` | `PERSON_CONTACT_SERVICE_BASE_URL` | Contact docking relies on this service owning email/phone data; the person service only supplies `contactOwnerType=PERSON` + `contactOwnerId`. |
| `communication-service` | `PERSON_COMMUNICATION_SERVICE_BASE_URL` | Optional downstream for pushing notifications once classifications or memberships change; unreachability is logged and reported via `DOWNSTREAM_UNAVAILABLE` only when used. |
| `iam-service` | `PERSON_IAM_SERVICE_BASE_URL` | Optional remote policy provider; local scopes (`person:read`, `team:write`, `person:classification:write`, etc.) are sufficient when this variable is unset. |
| Outbox | `PERSON_OUTBOX_ENABLED` | Toggles the optional outbox/eventing pipeline described in the Pflichtenheft; default `false` keeps the service lightweight while allowing future activation. |

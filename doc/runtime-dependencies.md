# Runtime Dependencies

## Required
- MariaDB

## Optional Runtime Integrations
- Auth JWKS (JWT key discovery/validation endpoint)
- Company service
- IAM service
- Communication service

## Testability Note
- Unit- und Integration-Tests laufen ohne externe Services.
- Grund: Tests verwenden MockMvc mit `spring-security-test` (`jwt()`) sowie Mocks/Stubs statt echter HTTP-Integrationen.

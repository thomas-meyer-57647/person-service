# Person Service

Spring Boot Service fuer Personen- und Team-Verwaltung.

## ID-Modell (extern vs. intern)

- `personId` in der API ist die externe ID und entspricht `publicId` (UUID-String, z. B. `550e8400-e29b-41d4-a716-446655440000`).
- `companyId` wird in allen Endpunkten und Payloads als `String` gefuehrt (typisch UUID-Format).
- Die interne Datenbank-PK `id` bei `person` bleibt `Long` und ist ein rein internes Persistenzdetail; sie wird extern nicht zur Identifikation in Pfaden verwendet.

## Local Development

### Benoetigte ENV Vars

Der Service nutzt folgende Umgebungsvariablen fuer die Datenbankverbindung:

- `PERSON_DB_HOST` (Default: `localhost`)
- `PERSON_DB_PORT` (Default: `3306`)
- `PERSON_DB_NAME` (Default: `person`)
- `PERSON_DB_USER` (Default: `person`)
- `PERSON_DB_PASSWORD` (Default: `person`)
- `PERSON_SERVER_PORT` (Default: `8113`)
- `PERSON_MANAGEMENT_SERVER_PORT` (Default: `8213`)

Beispiel (PowerShell):

```powershell
$env:PERSON_DB_HOST="localhost"
$env:PERSON_DB_PORT="3306"
$env:PERSON_DB_NAME="person"
$env:PERSON_DB_USER="person"
$env:PERSON_DB_PASSWORD="person"
$env:PERSON_SERVER_PORT="8113"
$env:PERSON_MANAGEMENT_SERVER_PORT="8213"
```

Optional koennen folgende Umgebungsvariablen aus dem Pflichtenheft gesetzt werden:

- `PERSON_API_DOCS_PATH` (Default: `/v3/api-docs`)
- `PERSON_SWAGGER_UI_PATH` (Default: `/swagger-ui.html`)
- `PERSON_ACTUATOR_BASE_PATH` (Default: `/actuator`)

### IntelliJ Run Configuration

- Beispiel-ENV:
  - `PERSON_DB_HOST=localhost;PERSON_DB_PORT=3306;PERSON_DB_NAME=person;PERSON_DB_USER=person;PERSON_DB_PASSWORD=person;PERSON_SERVER_PORT=8113;PERSON_MANAGEMENT_SERVER_PORT=8213`
- Hinweis: `Include system environment variables` kann aktiviert bleiben, da die Variablen service-spezifisch sind.

### Start mit Docker (ohne Profile)

1. Datenbank starten:
   - `docker compose -f docker/docker-compose.yml up -d`
2. Service starten:
   - `mvn spring-boot:run`

### Start lokal ohne Docker (mit local-Profil)

Das Profil `local` setzt DB-Zugang auf `root` mit leerem Passwort.

- Service starten:
  - `mvn spring-boot:run -Dspring-boot.run.profiles=local`

### Tests

- `mvn test`

### Hinweis zu Port-Konflikten

- Falls `3306` bereits belegt ist, in `docker/docker-compose.yml` Port-Mapping auf `3307:3306` umstellen.
- In diesem Fall beim Service `PERSON_DB_PORT=3307` setzen.

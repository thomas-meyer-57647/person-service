# Person Service

Spring Boot Service fuer Personen- und Team-Verwaltung.

## Local Development

### Benoetigte ENV Vars

Der Service nutzt folgende Umgebungsvariablen fuer die Datenbankverbindung:

- `PERSON_DB_HOST` (Default: `localhost`)
- `PERSON_DB_PORT` (Default: `3306`)
- `PERSON_DB_NAME` (Default: `person`)
- `PERSON_DB_USER` (Default: `person`)
- `PERSON_DB_PASSWORD` (Default: `person`)
- `PERSONPORT` (Default: `8080`)

Beispiel (PowerShell):

```powershell
$env:PERSON_DB_HOST="localhost"
$env:PERSON_DB_PORT="3306"
$env:PERSON_DB_NAME="person"
$env:PERSON_DB_USER="person"
$env:PERSON_DB_PASSWORD="person"
$env:PERSONPORT="8080"
```

### IntelliJ Run Configuration

- Beispiel-ENV:
  - `PERSON_DB_HOST=localhost;PERSON_DB_PORT=3306;PERSON_DB_NAME=person;PERSON_DB_USER=person;PERSON_DB_PASSWORD=person;PERSONPORT=8080`
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

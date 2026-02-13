# Person Service

Spring Boot Service fuer Personen- und Team-Verwaltung.

## Local Development

### Benoetigte ENV Vars

Der Service nutzt folgende Umgebungsvariablen fuer die Datenbankverbindung:

- `DB_HOST` (Default: `localhost`)
- `DB_PORT` (Default: `3306`)
- `DB_NAME` (Default: `person`)
- `DB_USER` (Default: `person`)
- `DB_PASSWORD` (Default: `person`)

Beispiel (PowerShell):

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="person"
$env:DB_USER="person"
$env:DB_PASSWORD="person"
```

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
- In diesem Fall beim Service `DB_PORT=3307` setzen.

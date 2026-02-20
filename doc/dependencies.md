# Service Dependencies

Dieses Dokument beschreibt die externen Abhaengigkeiten des `person-service` und deren Betriebsverhalten.

## Hard Dependencies

| Dependency | Zweck | Protokoll | Konfig-Keys / ENV | Failure-Verhalten | Default |
|---|---|---|---|---|---|
| MariaDB | Persistenz fuer Personen, Teams und Relations (inkl. Flyway-Migrationen) | DB (MariaDB/JDBC) | `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`, `spring.datasource.driver-class-name`; ENV in URL/Properties: `PERSON_DB_HOST`, `PERSON_DB_PORT`, `PERSON_DB_NAME`, `PERSON_DB_USER`, `PERSON_DB_PASSWORD` | Service startet nicht bzw. wird nicht betriebsbereit, wenn DB nicht erreichbar/ungueltig konfiguriert ist (JPA/Flyway schlagen fehl) | Enabled |

## Soft Dependencies

| Dependency | Zweck | Protokoll | Konfig-Keys / ENV | Failure-Verhalten | Default |
|---|---|---|---|---|---|
| Company-Service | Validierung/Anreicherung von Company-Bezuegen (zukuenftig) | HTTP | Noch nicht implementiert (vorgesehen z. B. `services.company.base-url`) | Bei Ausfall sollte Kernfunktionalitaet degradiert laufen oder mit klarer 5xx/4xx-Fehlermeldung antworten, je nach Use-Case | Disabled |
| User-Service | Aufloesung/Validierung von User-Informationen (zukuenftig) | HTTP | Noch nicht implementiert (vorgesehen z. B. `services.user.base-url`) | Bei Ausfall sollte Kernfunktionalitaet degradiert laufen oder mit klarer 5xx/4xx-Fehlermeldung antworten, je nach Use-Case | Disabled |
| Communication-Service | Validierung von referenzierten `communicationId` (zukuenftig) | HTTP | Noch nicht implementiert (vorgesehen z. B. `services.communication.base-url`) | Bei Ausfall sollte Kernfunktionalitaet degradiert laufen oder mit klarer 5xx/4xx-Fehlermeldung antworten, je nach Use-Case | Disabled |
| Auth-Service (spaeter) | Token-Validierung/Identity-Claims (spaeter) | HTTP | Noch nicht implementiert (vorgesehen z. B. `services.auth.*`) | Bei Ausfall i. d. R. AuthN/AuthZ-Fehler oder Fail-Closed Verhalten fuer geschuetzte Endpunkte | Disabled |

## Hinweise

- Aktuell ist nur MariaDB technisch im Service verdrahtet.
- Soft Dependencies sind als Zielbild dokumentiert und werden bei Implementierung um konkrete Keys/Timeouts/Retry-Regeln erweitert.

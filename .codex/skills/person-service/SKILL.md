# person-service-impl

## Zweck
Repo-spezifische Implementierungsrichtlinie fuer den Person Service.

## Repo-Konventionen
- Architektur: CQRS mit getrennten Query- und Command-Controllern/Services.
- Datenbank-Schema: ausschliesslich ueber Flyway-Migrationen aendern (Flyway is source of truth).
- JPA/Hibernate: `ddl-auto=validate` beibehalten, kein Schema-Write durch Hibernate.
- Audit und Soft-Delete:
  - Audit-Felder: `createdAt`, `createdBy`, `modifiedAt`, `modifiedBy`.
  - Soft-Delete-Felder: `trashedAt`, `trashedBy`.
  - Kein Hard Delete ohne expliziten Auftrag.
- Actor-Kontext: `X-Actor-Id` Header aus Requests lesen und fuer Audit/Trash-Felder verwenden.
- Mandantentrennung: `companyId` in allen Zugriffen und Mutationen erzwingen.

## Standard-Workflow
1. Plan:
   - Vor jeder Aenderung kurze Checklist mit den geplanten Schritten erstellen.
2. Implement:
   - Aenderungen entlang der Repo-Konventionen umsetzen.
3. Verifizieren:
   - Immer `mvn -U clean test` ausfuehren.
   - Bei Fehlern direkt beheben und erneut testen.
4. Abschluss:
   - Kurze Zusammenfassung der geaenderten Dateien und des Ergebnisses liefern.

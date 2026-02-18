# Person Service Testmatrix V1.1 (Security)

## 1) Vollstaendige REST-Endpunktliste (Controller + RequestMappings)

| Controller | Methode | Pfad | Request DTO | Response DTO |
|---|---|---|---|---|
| `OpsController` | GET | `/person/ping` | - | `OpsController.PingResponse` |
| `OpsController` | GET | `/person/version` | - | `OpsController.VersionResponse` |
| `PersonQueryController` | GET | `/api/v1/companies/{companyId}/persons` | - (Query: `q`, `includeTrashed`, Paging) | `Page<PersonResponse>` |
| `PersonQueryController` | GET | `/api/v1/companies/{companyId}/persons/{personId}` | - | `PersonResponse` |
| `PersonCommandController` | POST | `/api/v1/companies/{companyId}/persons` | `PersonCreateRequest` | `PersonResponse` |
| `PersonCommandController` | PATCH | `/api/v1/companies/{companyId}/persons/{personId}` | `PersonUpdateRequest` | `PersonResponse` |
| `PersonCommandController` | POST | `/api/v1/companies/{companyId}/persons/{personId}/trash` | - | `PersonResponse` |
| `PersonCommandController` | POST | `/api/v1/companies/{companyId}/persons/{personId}/restore` | - | `PersonResponse` |
| `PersonCommunicationRefController` | GET | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | - | `PersonCommunicationRefsResponse` |
| `PersonCommunicationRefController` | PUT | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | `PersonCommunicationRefsRequest` | `PersonCommunicationRefsResponse` |
| `TeamQueryController` | GET | `/api/v1/companies/{companyId}/teams` | - (Query: `q`, `includeTrashed`, Paging) | `Page<TeamResponse>` |
| `TeamQueryController` | GET | `/api/v1/companies/{companyId}/teams/{teamId}` | - | `TeamResponse` |
| `TeamQueryController` | GET | `/api/v1/companies/{companyId}/teams/{teamId}/members` | - | `List<TeamMemberResponse>` |
| `TeamCommandController` | POST | `/api/v1/companies/{companyId}/teams` | `TeamCreateRequest` | `TeamResponse` |
| `TeamCommandController` | PATCH | `/api/v1/companies/{companyId}/teams/{teamId}` | `TeamUpdateRequest` | `TeamResponse` |
| `TeamCommandController` | POST | `/api/v1/companies/{companyId}/teams/{teamId}/trash` | - | `TeamResponse` |
| `TeamCommandController` | POST | `/api/v1/companies/{companyId}/teams/{teamId}/restore` | - | `TeamResponse` |
| `TeamCommandController` | POST | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `TeamMemberAddRequest` | `TeamMemberResponse` |
| `TeamCommandController` | DELETE | `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | - | - (`204 No Content`) |

## 2) Testmatrix V1.1 (Positiv/Negativ inkl. Security)

Hinweis: `401`, `403 missing scope` und `403 tenant mismatch` sind als Security-Pflichtfaelle gemaess V1.1 je API-Endpoint (`/api/v1/**`) aufgefuehrt.

| Endpoint | Methode | Positiv | 401 | 403 missing scope | 403 tenant mismatch | 400 | 404 | 409 |
|---|---|---|---|---|---|---|---|---|
| `/person/ping` | GET | Service antwortet mit `service`,`version` | n/a | n/a | n/a | - | - | - |
| `/person/version` | GET | Service antwortet mit `service`,`version`,`buildTime` | n/a | n/a | n/a | - | - | - |
| `/api/v1/companies/{companyId}/persons` | GET | Liste/Paging/Filter erfolgreich | ohne Authentifizierung -> `401` | ohne erforderlichen Read-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | ungueltiges `includeTrashed`/Paging -> `400` | - | - |
| `/api/v1/companies/{companyId}/persons/{personId}` | GET | Person erfolgreich geladen | ohne Authentifizierung -> `401` | ohne erforderlichen Read-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | - | Person nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/persons` | POST | Person erfolgreich erstellt (`201`) | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | Validation/Company-Mismatch Body vs Path -> `400` | - | - |
| `/api/v1/companies/{companyId}/persons/{personId}` | PATCH | Person erfolgreich aktualisiert | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | Validationfehler -> `400` | Person nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/persons/{personId}/trash` | POST | Person soft-delete erfolgreich | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | - | Person nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/persons/{personId}/restore` | POST | Person restore erfolgreich | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | - | Person nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | GET | Refs erfolgreich geladen | ohne Authentifizierung -> `401` | ohne erforderlichen Read-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | - | Person nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | PUT | Refs erfolgreich ersetzt | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | Validationfehler (`communicationIds`) -> `400` | Person nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/teams` | GET | Liste/Paging/Filter erfolgreich | ohne Authentifizierung -> `401` | ohne erforderlichen Read-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | ungueltiges `includeTrashed`/Paging -> `400` | - | - |
| `/api/v1/companies/{companyId}/teams/{teamId}` | GET | Team erfolgreich geladen | ohne Authentifizierung -> `401` | ohne erforderlichen Read-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | - | Team nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/teams/{teamId}/members` | GET | Team-Members erfolgreich geladen | ohne Authentifizierung -> `401` | ohne erforderlichen Read-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | - | Team nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/teams` | POST | Team erfolgreich erstellt (`201`) | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | Validation/Company-Mismatch Body vs Path -> `400` | - | doppelter Teamname -> `409` |
| `/api/v1/companies/{companyId}/teams/{teamId}` | PATCH | Team erfolgreich aktualisiert | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | Validationfehler -> `400` | Team nicht vorhanden -> `404` | Konflikt (z. B. Name nicht unique) -> `409` |
| `/api/v1/companies/{companyId}/teams/{teamId}/trash` | POST | Team soft-delete erfolgreich | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | - | Team nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/teams/{teamId}/restore` | POST | Team restore erfolgreich | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | - | Team nicht vorhanden -> `404` | - |
| `/api/v1/companies/{companyId}/teams/{teamId}/members` | POST | Member erfolgreich hinzugefuegt (`201`) | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | Validationfehler (`personId`,`role`) -> `400` | Team/Person nicht vorhanden -> `404` | bereits aktive Mitgliedschaft -> `409` |
| `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | DELETE | Member erfolgreich entfernt (`204`) | ohne Authentifizierung -> `401` | ohne erforderlichen Write-Scope -> `403` | Token-Company != Pfad-`companyId` -> `403` | - | keine aktive Mitgliedschaft -> `404` | - |

## 3) Abgleich zum aktuellen Ist-Stand

| Punkt | Ist im aktuellen Projekt |
|---|---|
| Security-Layer (Auth/Scopes/Tenant aus Token) | aktuell nicht implementiert (kein Spring Security Starter/Config in `src/main`) |
| Fachliche HTTP-Fehler 400/404/409 | implementiert und in Web-/Integrationstests abgedeckt |
| Soft-Delete-Verhalten | fuer Person/Team/Membership vorhanden (`trash`/`restore` bzw. `DELETE` als Soft-Ende) |

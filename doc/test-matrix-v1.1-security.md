# Person Service Testmatrix V1.1 (Security)

## 1) Vollständige REST-Endpunktliste (Controller + RequestMappings)

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
| `PersonClassificationQueryController` | GET | `/api/v1/companies/{companyId}/person-classifications` | - | `List<PersonClassificationResponse>` |
| `PersonClassificationCommandController` | POST | `/api/v1/companies/{companyId}/person-classifications` | `PersonClassificationCreateRequest` | `PersonClassificationResponse` |
| `PersonClassificationCommandController` | PATCH | `/api/v1/companies/{companyId}/person-classifications/{classificationId}` | `PersonClassificationUpdateRequest` | `PersonClassificationResponse` |
| `PersonClassificationCommandController` | POST | `/api/v1/companies/{companyId}/person-classifications/{classificationId}/deactivate` | - | `PersonClassificationResponse` |
| `PersonClassificationCommandController` | POST | `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications` | `PersonClassificationAssignmentRequest` | `PersonClassificationAssignmentResponse` |
| `PersonClassificationCommandController` | DELETE | `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications/{assignmentId}` | - | - (`204 No Content`) |
| `TeamQueryController` | GET | `/api/v1/companies/{companyId}/teams` | - (Query) | `Page<TeamResponse>` |
| `TeamQueryController` | GET | `/api/v1/companies/{companyId}/teams/{teamId}` | - | `TeamResponse` |
| `TeamQueryController` | GET | `/api/v1/companies/{companyId}/teams/{teamId}/members` | - | `List<TeamMemberResponse>` |
| `TeamCommandController` | POST | `/api/v1/companies/{companyId}/teams` | `TeamCreateRequest` | `TeamResponse` |
| `TeamCommandController` | PATCH | `/api/v1/companies/{companyId}/teams/{teamId}` | `TeamUpdateRequest` | `TeamResponse` |
| `TeamCommandController` | POST | `/api/v1/companies/{companyId}/teams/{teamId}/trash` | - | `TeamResponse` |
| `TeamCommandController` | POST | `/api/v1/companies/{companyId}/teams/{teamId}/restore` | - | `TeamResponse` |
| `TeamCommandController` | POST | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `TeamMemberAddRequest` | `TeamMemberResponse` |
| `TeamCommandController` | DELETE | `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | - | - (`204 No Content`) |

## 2) Testmatrix V1.1 (Positiv/Negativ inkl. Security)

Hinweis: `401`, `403 missing scope` und `403 tenant mismatch` sind als Security-Pflichtfälle je API-Endpoint (`/api/v1/**`) aufgeführt.

| Endpoint | Methode | Positiv | 401 | 403 missing scope | 403 tenant mismatch | 400 | 404 | 409 |
|---|---|---|---|---|---|---|---|---|
| `/person/ping` | GET | `200` | n/a | n/a | n/a | - | - | - |
| `/person/version` | GET | `200` | n/a | n/a | n/a | - | - | - |
| `/api/v1/companies/{companyId}/persons` | GET | `200` | `401` | `403` | `403` | `400` | - | - |
| `/api/v1/companies/{companyId}/persons/{personId}` | GET | `200` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/persons` | POST | `201` | `401` | `403` | `403` | `400` | - | - |
| `/api/v1/companies/{companyId}/persons/{personId}` | PATCH | `200` | `401` | `403` | `403` | `400` | `404` | - |
| `/api/v1/companies/{companyId}/persons/{personId}/trash` | POST | `200` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/persons/{personId}/restore` | POST | `200` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | GET | `200` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | PUT | `200` | `401` | `403` | `403` | `400` | `404` | - |
| `/api/v1/companies/{companyId}/person-classifications` | GET | `200` | `401` | `403` | `403` | `400` | - | - |
| `/api/v1/companies/{companyId}/person-classifications` | POST | `201` | `401` | `403` | `403` | `400` | - | `409` |
| `/api/v1/companies/{companyId}/person-classifications/{classificationId}` | PATCH | `200` | `401` | `403` | `403` | `400` | `404` | `409` |
| `/api/v1/companies/{companyId}/person-classifications/{classificationId}/deactivate` | POST | `200` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications` | POST | `201` | `401` | `403` | `403` | `400` | `404` | - |
| `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications/{assignmentId}` | DELETE | `204` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/teams` | GET | `200` | `401` | `403` | `403` | `400` | - | - |
| `/api/v1/companies/{companyId}/teams/{teamId}` | GET | `200` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/teams/{teamId}/members` | GET | `200` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/teams` | POST | `201` | `401` | `403` | `403` | `400` | - | `409` |
| `/api/v1/companies/{companyId}/teams/{teamId}` | PATCH | `200` | `401` | `403` | `403` | `400` | `404` | `409` |
| `/api/v1/companies/{companyId}/teams/{teamId}/trash` | POST | `200` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/teams/{teamId}/restore` | POST | `200` | `401` | `403` | `403` | - | `404` | - |
| `/api/v1/companies/{companyId}/teams/{teamId}/members` | POST | `201` | `401` | `403` | `403` | `400` | `404` | `409` |
| `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | DELETE | `204` | `401` | `403` | `403` | - | `404` | - |

## 3) Abgleich zum aktuellen Ist-Stand

| Punkt | Ist im aktuellen Projekt |
|---|---|
| Security-Layer (Auth/Scopes/Tenant aus Token) | implementiert; SecurityConfig prüft `tenant_id`, `scope` und `audience`. |
| Fachliche HTTP-Fehler 400/404/409 | implementiert und in Web-/Integrationstests abgedeckt (`Person`, `Team`, `PersonClassification`). |
| Soft-Delete-Verhalten | für Person/Team/Membership + Klassifikationszuweisungen vorhanden (`trash`/`deactivate`/`delete`). |

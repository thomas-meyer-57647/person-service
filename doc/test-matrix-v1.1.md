# Person Service Testmatrix V1.1

## 1) Endpunkt-Bestandsaufnahme (Controller)

| HTTP | Pfad | Controller#Methode | Request DTO | Response DTO |
|---|---|---|---|---|
| GET | `/person/ping` | `OpsController#ping` | - | `OpsController.PingResponse` |
| GET | `/person/version` | `OpsController#version` | - | `OpsController.VersionResponse` |
| POST | `/api/v1/companies/{companyId}/persons` | `PersonCommandController#createPerson` | `PersonCreateRequest` | `PersonResponse` |
| PATCH | `/api/v1/companies/{companyId}/persons/{personId}` | `PersonCommandController#updatePerson` | `PersonUpdateRequest` | `PersonResponse` |
| POST | `/api/v1/companies/{companyId}/persons/{personId}/trash` | `PersonCommandController#trashPerson` | - | `PersonResponse` |
| POST | `/api/v1/companies/{companyId}/persons/{personId}/restore` | `PersonCommandController#restorePerson` | - | `PersonResponse` |
| GET | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | `PersonCommunicationRefController#getCommunicationRefs` | - | `PersonCommunicationRefsResponse` |
| PUT | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | `PersonCommunicationRefController#replaceCommunicationRefs` | `PersonCommunicationRefsRequest` | `PersonCommunicationRefsResponse` |
| GET | `/api/v1/companies/{companyId}/persons` | `PersonQueryController#listPersons` | - (Query: `q`, `includeTrashed`, Paging) | `Page<PersonResponse>` |
| GET | `/api/v1/companies/{companyId}/persons/{personId}` | `PersonQueryController#getPerson` | - | `PersonResponse` |
| POST | `/api/v1/companies/{companyId}/teams` | `TeamCommandController#createTeam` | `TeamCreateRequest` | `TeamResponse` |
| PATCH | `/api/v1/companies/{companyId}/teams/{teamId}` | `TeamCommandController#updateTeam` | `TeamUpdateRequest` | `TeamResponse` |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/trash` | `TeamCommandController#trashTeam` | - | `TeamResponse` |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/restore` | `TeamCommandController#restoreTeam` | - | `TeamResponse` |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `TeamCommandController#addMember` | `TeamMemberAddRequest` | `TeamMemberResponse` |
| DELETE | `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | `TeamCommandController#removeMember` | - | - (`204 No Content`) |
| GET | `/api/v1/companies/{companyId}/teams` | `TeamQueryController#listTeams` | - (Query: `q`, `includeTrashed`, Paging) | `Page<TeamResponse>` |
| GET | `/api/v1/companies/{companyId}/teams/{teamId}` | `TeamQueryController#getTeam` | - | `TeamResponse` |
| GET | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `TeamQueryController#getTeamMembers` | - | `List<TeamMemberResponse>` |

## 2) Testmatrix pro Endpunkt

| HTTP | Pfad | Positiv (2xx) | Negativ: 401 ohne JWT | Negativ: 403 fehlender Scope | Negativ: 403 tenant mismatch | Fachlicher Negativfall (mind. ein 400/404/409) |
|---|---|---|---|---|---|---|
| GET | `/person/ping` | `200` mit `service`,`version` | ohne JWT -> `401` (falls abgesichert) | ohne erforderlichen Scope -> `403` (falls Scope-Pruefung aktiv) | n/a (kein `companyId` im Pfad) | n/a (kein fachlicher 400/404/409-Fall definiert) |
| GET | `/person/version` | `200` mit `service`,`version`,`buildTime` | ohne JWT -> `401` (falls abgesichert) | ohne erforderlichen Scope -> `403` (falls Scope-Pruefung aktiv) | n/a (kein `companyId` im Pfad) | n/a (kein fachlicher 400/404/409-Fall definiert) |
| POST | `/api/v1/companies/{companyId}/persons` | `201` Person erstellt | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `400` bei Validation (z. B. `companyId` im Body fehlt) |
| PATCH | `/api/v1/companies/{companyId}/persons/{personId}` | `200` Person aktualisiert | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Person nicht existiert |
| POST | `/api/v1/companies/{companyId}/persons/{personId}/trash` | `200` Person getrasht | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Person nicht existiert |
| POST | `/api/v1/companies/{companyId}/persons/{personId}/restore` | `200` Person restored | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Person nicht existiert |
| GET | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | `200` Refs geladen | ohne JWT -> `401` | Read-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Person nicht existiert |
| PUT | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | `200` Refs ersetzt | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `400` bei Validation (`communicationIds` fehlt) |
| GET | `/api/v1/companies/{companyId}/persons` | `200` Liste/Paging erfolgreich | ohne JWT -> `401` | Read-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `400` bei ungueltigem `includeTrashed` |
| GET | `/api/v1/companies/{companyId}/persons/{personId}` | `200` Person geladen | ohne JWT -> `401` | Read-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Person nicht existiert |
| POST | `/api/v1/companies/{companyId}/teams` | `201` Team erstellt | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `409` bei doppeltem Teamnamen |
| PATCH | `/api/v1/companies/{companyId}/teams/{teamId}` | `200` Team aktualisiert | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Team nicht existiert |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/trash` | `200` Team getrasht | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Team nicht existiert |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/restore` | `200` Team restored | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Team nicht existiert |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `201` Member hinzugefuegt | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `409` bei bereits aktiver Mitgliedschaft |
| DELETE | `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | `204` Member entfernt | ohne JWT -> `401` | Write-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn aktive Mitgliedschaft nicht existiert |
| GET | `/api/v1/companies/{companyId}/teams` | `200` Liste/Paging erfolgreich | ohne JWT -> `401` | Read-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `400` bei ungueltigem `includeTrashed` |
| GET | `/api/v1/companies/{companyId}/teams/{teamId}` | `200` Team geladen | ohne JWT -> `401` | Read-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Team nicht existiert |
| GET | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `200` Team-Mitglieder geladen | ohne JWT -> `401` | Read-Scope fehlt -> `403` | Token-Tenant != `{companyId}` -> `403` | `404` wenn Team nicht existiert |

## 3) Test-Abbildung auf vorhandene Testklassen

| HTTP | Pfad | WebMvc Positivtest(s) | WebMvc Fachlicher Negativtest(s) | Integration Positivtest(s) | Integration Fachlicher Negativtest(s) |
|---|---|---|---|---|---|
| GET | `/person/ping` | `OpsControllerTest#shouldReturnPingWithFallbackVersion_whenBuildPropertiesMissingAndBasePathUnset` | - | - | - |
| GET | `/person/version` | `OpsControllerTest#shouldReturnVersionWithFallbackVersion_whenBuildPropertiesMissing`, `OpsControllerBuildPropertiesTest#shouldReturnVersionWithBuildProperties_whenAvailable` | - | - | - |
| POST | `/api/v1/companies/{companyId}/persons` | `PersonCommandControllerTest#shouldCreatePerson_whenValidRequest` | `PersonCommandControllerTest#shouldReturn400_whenCreatePersonMissingCompanyId` | `PersonTeamIntegrationTests#shouldCreatePerson_whenValidRequest` | `PersonTeamIntegrationTests#shouldReturn400_whenCreatePersonMissingCompanyId` |
| PATCH | `/api/v1/companies/{companyId}/persons/{personId}` | `PersonCommandControllerTest#shouldUpdatePerson_whenValidPatch` | `PersonCommandControllerTest#shouldReturn404_whenUpdatePersonNotFound` | `PersonTeamIntegrationTests#shouldPatchPerson_whenPersonExists` | `PersonTeamIntegrationTests#shouldReturn404_whenPatchPersonNotFound` |
| POST | `/api/v1/companies/{companyId}/persons/{personId}/trash` | `PersonCommandControllerTest#shouldTrashPerson_whenPersonExists` | `PersonCommandControllerTest#shouldReturn404_whenTrashPersonNotFound` | `PersonTeamIntegrationTests#shouldTrashPerson_andFilterByIncludeTrashed` | `PersonTeamIntegrationTests#shouldReturn404_whenTrashPersonNotFound` |
| POST | `/api/v1/companies/{companyId}/persons/{personId}/restore` | `PersonCommandControllerTest#shouldRestorePerson_whenPersonExists` | `PersonCommandControllerTest#shouldReturn404_whenRestorePersonNotFound` | `PersonTeamIntegrationTests#shouldRestorePerson_whenPersonIsTrashed` | `PersonTeamIntegrationTests#shouldReturn404_whenRestorePersonNotFound` |
| GET | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | `PersonCommunicationRefControllerTest#shouldGetCommunicationRefs_whenPersonExists` | `PersonCommunicationRefControllerTest#shouldReturn404_whenGetCommunicationRefsPersonNotFound` | `PersonTeamIntegrationTests#shouldGetCommunicationRefs_whenPersonExists` | `PersonTeamIntegrationTests#shouldReturn404_whenGetCommunicationRefsPersonNotFound` |
| PUT | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | `PersonCommunicationRefControllerTest#shouldPutCommunicationRefs_whenValidRequest` | `PersonCommunicationRefControllerTest#shouldReturn400_whenPutCommunicationRefsMissingList` | `PersonTeamIntegrationTests#shouldPutCommunicationRefs_whenValidRequest` | `PersonTeamIntegrationTests#shouldReturn400_whenPutCommunicationRefsValidationFails` |
| GET | `/api/v1/companies/{companyId}/persons` | `PersonQueryControllerTest#shouldListPersons_whenValidRequest` | `PersonQueryControllerTest#shouldReturn400_whenInvalidIncludeTrashedParameter` | `PersonTeamIntegrationTests#shouldListPersons_whenDataExists` | `PersonTeamIntegrationTests#shouldReturn400_whenListPersonsHasInvalidIncludeTrashedParam` |
| GET | `/api/v1/companies/{companyId}/persons/{personId}` | `PersonQueryControllerTest#shouldGetPerson_whenPersonExists` | `PersonQueryControllerTest#shouldReturn404_whenPersonDoesNotExist` | `PersonTeamIntegrationTests#shouldGetPersonById_whenPersonExists` | `PersonTeamIntegrationTests#shouldReturn404_whenGetPersonByIdNotFound` |
| POST | `/api/v1/companies/{companyId}/teams` | `TeamCommandControllerTest#shouldCreateTeam_whenValidRequest` | `TeamCommandControllerTest#shouldReturn409_whenCreateTeamHasDuplicateName` | `PersonTeamIntegrationTests#shouldCreateTeam_whenValidRequest` | `PersonTeamIntegrationTests#shouldReturn409_whenCreateTeamDuplicateNameInCompany` |
| PATCH | `/api/v1/companies/{companyId}/teams/{teamId}` | `TeamCommandControllerTest#shouldUpdateTeam_whenValidPatch` | `TeamCommandControllerTest#shouldReturn404_whenUpdateTeamNotFound` | `PersonTeamIntegrationTests#shouldPatchTeam_whenTeamExists` | `PersonTeamIntegrationTests#shouldReturn404_whenPatchTeamNotFound` |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/trash` | `TeamCommandControllerTest#shouldTrashTeam_whenTeamExists` | `TeamCommandControllerTest#shouldReturn404_whenTrashTeamNotFound` | `PersonTeamIntegrationTests#shouldTrashTeam_whenTeamExists` | `PersonTeamIntegrationTests#shouldReturn404_whenTrashTeamNotFound` |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/restore` | `TeamCommandControllerTest#shouldRestoreTeam_whenTeamExists` | `TeamCommandControllerTest#shouldReturn404_whenRestoreTeamNotFound` | `PersonTeamIntegrationTests#shouldRestoreTeam_whenTeamWasTrashed` | `PersonTeamIntegrationTests#shouldReturn404_whenRestoreTeamNotFound` |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `TeamCommandControllerTest#shouldAddMember_whenValidRequest` | `TeamCommandControllerTest#shouldReturn409_whenAddMemberDuplicate` | `PersonTeamIntegrationTests#shouldAddTeamMember_whenValidRequest` | `PersonTeamIntegrationTests#shouldReturn409_whenAddTeamMemberDuplicate` |
| DELETE | `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | `TeamCommandControllerTest#shouldRemoveMember_whenExistingMembership` | `TeamCommandControllerTest#shouldReturn404_whenRemoveMemberNotFound` | `PersonTeamIntegrationTests#shouldRemoveTeamMember_whenMembershipExists` | `PersonTeamIntegrationTests#shouldReturn404_whenRemoveTeamMemberNotFound` |
| GET | `/api/v1/companies/{companyId}/teams` | `TeamQueryControllerTest#shouldListTeams_whenValidRequest` | `TeamQueryControllerTest#shouldReturn400_whenInvalidIncludeTrashedForTeamList` | `PersonTeamIntegrationTests#shouldListTeams_whenDataExists` | `PersonTeamIntegrationTests#shouldReturn400_whenListTeamsHasInvalidIncludeTrashedParam` |
| GET | `/api/v1/companies/{companyId}/teams/{teamId}` | `TeamQueryControllerTest#shouldGetTeam_whenExists` | `TeamQueryControllerTest#shouldReturn404_whenTeamNotFound` | `PersonTeamIntegrationTests#shouldGetTeamById_whenTeamExists` | `PersonTeamIntegrationTests#shouldReturn404_whenGetTeamByIdNotFound` |
| GET | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `TeamQueryControllerTest#shouldGetTeamMembers_whenTeamExists` | `TeamQueryControllerTest#shouldReturn404_whenGetTeamMembersTeamNotFound` | `PersonTeamIntegrationTests#shouldListTeamMembers_whenMembersExist` | `PersonTeamIntegrationTests#shouldReturn404_whenListTeamMembersTeamNotFound` |

## 4) Grundlage der Bestandsaufnahme

- Controller-Definitionen in `src/main/java/de/innologic/personservice/web/*Controller.java`.
- Fachliche Negativfaelle aus bestehenden Web-/Integrationstests unter `src/test/java/de/innologic/personservice/web/*.java` und `src/test/java/de/innologic/personservice/PersonTeamIntegrationTests.java`.

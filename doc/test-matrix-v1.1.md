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
| POST | `/api/v1/companies/{companyId}/person-classifications` | `PersonClassificationCommandController#createClassification` | `PersonClassificationCreateRequest` | `PersonClassificationResponse` |
| PATCH | `/api/v1/companies/{companyId}/person-classifications/{classificationId}` | `PersonClassificationCommandController#updateClassification` | `PersonClassificationUpdateRequest` | `PersonClassificationResponse` |
| POST | `/api/v1/companies/{companyId}/person-classifications/{classificationId}/deactivate` | `PersonClassificationCommandController#deactivateClassification` | - | `PersonClassificationResponse` |
| GET | `/api/v1/companies/{companyId}/person-classifications` | `PersonClassificationQueryController#listClassifications` | - (`includeInactive`) | `List<PersonClassificationResponse>` |
| POST | `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications` | `PersonClassificationCommandController#assignClassification` | `PersonClassificationAssignmentRequest` | `PersonClassificationAssignmentResponse` |
| DELETE | `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications/{assignmentId}` | `PersonClassificationCommandController#removeAssignment` | - | - (`204`) |
| POST | `/api/v1/companies/{companyId}/teams` | `TeamCommandController#createTeam` | `TeamCreateRequest` | `TeamResponse` |
| PATCH | `/api/v1/companies/{companyId}/teams/{teamId}` | `TeamCommandController#updateTeam` | `TeamUpdateRequest` | `TeamResponse` |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/trash` | `TeamCommandController#trashTeam` | - | `TeamResponse` |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/restore` | `TeamCommandController#restoreTeam` | - | `TeamResponse` |
| POST | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `TeamCommandController#addMember` | `TeamMemberAddRequest` | `TeamMemberResponse` |
| DELETE | `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | `TeamCommandController#removeMember` | - | - (`204 No Content`) |
| GET | `/api/v1/companies/{companyId}/teams` | `TeamQueryController#listTeams` | - (Query) | `Page<TeamResponse>` |
| GET | `/api/v1/companies/{companyId}/teams/{teamId}` | `TeamQueryController#getTeam` | - | `TeamResponse` |
| GET | `/api/v1/companies/{companyId}/teams/{teamId}/members` | `TeamQueryController#getTeamMembers` | - | `List<TeamMemberResponse>` |

## 2) Testmatrix pro Endpunkt

| HTTP | Pfad | Positiv (2xx) | Negativ: 401 ohne JWT | Negativ: 403 fehlender Scope | Negativ: 403 tenant mismatch | Fachlicher Negativfall (mind. ein 400/404/409) |
|---|---|---|---|---|---|---|
| `/person/ping` | GET | `200` mit Service/Version | n/a | n/a | n/a | n/a |
| `/person/version` | GET | `200` mit Versioninfo | n/a | n/a | n/a | n/a |
| `/api/v1/companies/{companyId}/persons` | POST | `201` | `401` | `403` | `403` | `400` Body vs Path |
| `/api/v1/companies/{companyId}/persons/{personId}` | PATCH | `200` | `401` | `403` | `403` | `404` nicht existent |
| `/api/v1/companies/{companyId}/persons/{personId}/trash` | POST | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/persons/{personId}/restore` | POST | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | GET | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | PUT | `200` | `401` | `403` | `403` | `400` |
| `/api/v1/companies/{companyId}/persons` | GET | `200` | `401` | `403` | `403` | `400` |
| `/api/v1/companies/{companyId}/persons/{personId}` | GET | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/person-classifications` | POST | `201` | `401` | `403` | `403` | `409` |
| `/api/v1/companies/{companyId}/person-classifications/{classificationId}` | PATCH | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/person-classifications/{classificationId}/deactivate` | POST | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/person-classifications` | GET | `200` | `401` | `403` | `403` | `400` |
| `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications` | POST | `201` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications/{assignmentId}` | DELETE | `204` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/teams` | POST | `201` | `401` | `403` | `403` | `409` |
| `/api/v1/companies/{companyId}/teams/{teamId}` | PATCH | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/teams/{teamId}/trash` | POST | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/teams/{teamId}/restore` | POST | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/teams/{teamId}/members` | POST | `201` | `401` | `403` | `403` | `409` |
| `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | DELETE | `204` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/teams` | GET | `200` | `401` | `403` | `403` | `400` |
| `/api/v1/companies/{companyId}/teams/{teamId}` | GET | `200` | `401` | `403` | `403` | `404` |
| `/api/v1/companies/{companyId}/teams/{teamId}/members` | GET | `200` | `401` | `403` | `403` | `404` |

## 3) Test-Abbildung auf vorhandene Testklassen

| HTTP | Pfad | WebMvc Positivtest(s) | WebMvc Fachlicher Negativtest(s) | Integration Positivtest(s) | Integration Fachlicher Negativtest(s) |
|---|---|---|---|---|---|
| POST | `/api/v1/companies/{companyId}/persons` | `PersonCommandControllerTest#shouldCreatePerson_whenValidRequest` | `PersonCommandControllerTest#shouldReturn400_whenCreatePersonMissingCompanyId` | `PersonTeamIntegrationTests#shouldCreatePerson_whenValidRequest` | `PersonTeamIntegrationTests#shouldReturn400_whenCreatePersonMissingCompanyId` |
| PATCH | `/api/v1/companies/{companyId}/persons/{personId}` | `PersonCommandControllerTest#shouldUpdatePerson_whenValidPatch` | `PersonCommandControllerTest#shouldReturn404_whenUpdatePersonNotFound` | `PersonTeamIntegrationTests#shouldPatchPerson_whenPersonExists` | `PersonTeamIntegrationTests#shouldReturn404_whenPatchPersonNotFound` |
| POST | `/api/v1/companies/{companyId}/persons/{personId}/trash` | `PersonCommandControllerTest#shouldTrashPerson_whenPersonExists` | `PersonCommandControllerTest#shouldReturn404_whenTrashPersonNotFound` | `PersonTeamIntegrationTests#shouldTrashPerson_andFilterByIncludeTrashed` | `PersonTeamIntegrationTests#shouldReturn404_whenTrashPersonNotFound` |
| POST | `/api/v1/companies/{companyId}/persons/{personId}/restore` | `PersonCommandControllerTest#shouldRestorePerson_whenPersonExists` | `PersonCommandControllerTest#shouldReturn404_whenRestorePersonNotFound` | `PersonTeamIntegrationTests#shouldRestorePerson_whenPersonIsTrashed` | `PersonTeamIntegrationTests#shouldReturn404_whenRestorePersonNotFound` |
| GET | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | `PersonCommunicationRefControllerTest#shouldGetCommunicationRefs_whenPersonExists` | `PersonCommunicationRefControllerTest#shouldReturn404_whenGetCommunicationRefsPersonNotFound` | `PersonTeamIntegrationTests#shouldGetCommunicationRefs_whenPersonExists` | `PersonTeamIntegrationTests#shouldReturn404_whenGetCommunicationRefsPersonNotFound` |
| PUT | `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | `PersonCommunicationRefControllerTest#shouldPutCommunicationRefs_whenValidRequest` | `PersonCommunicationRefControllerTest#shouldReturn400_whenPutCommunicationRefsMissingList` | `PersonTeamIntegrationTests#shouldPutCommunicationRefs_whenValidRequest` | `PersonTeamIntegrationTests#shouldReturn400_whenPutCommunicationRefsValidationFails` |
| GET | `/api/v1/companies/{companyId}/persons` | `PersonQueryControllerTest#shouldListPersons_whenValidRequest` | `PersonQueryControllerTest#shouldReturn400_whenInvalidIncludeTrashedParameter` | `PersonTeamIntegrationTests#shouldListPersons_whenDataExists` | `PersonTeamIntegrationTests#shouldReturn400_whenListPersonsHasInvalidIncludeTrashedParam` |
| GET | `/api/v1/companies/{companyId}/persons/{personId}` | `PersonQueryControllerTest#shouldGetPerson_whenPersonExists` | `PersonQueryControllerTest#shouldReturn404_whenPersonDoesNotExist` | `PersonTeamIntegrationTests#shouldGetPersonById_whenPersonExists` | `PersonTeamIntegrationTests#shouldReturn404_whenGetPersonByIdNotFound` |
| POST | `/api/v1/companies/{companyId}/person-classifications` | `PersonClassificationCommandControllerTest#shouldCreateClassification_whenValidRequest` | `PersonClassificationCommandControllerTest#shouldReturn409_whenCreateClassificationConflict` | - | - |
| PATCH | `/api/v1/companies/{companyId}/person-classifications/{classificationId}` | `PersonClassificationCommandControllerTest#shouldUpdateClassification_whenValidPatch` | `PersonClassificationCommandControllerTest#shouldReturn404_whenUpdateClassificationNotFound` | - | - |
| POST | `/api/v1/companies/{companyId}/person-classifications/{classificationId}/deactivate` | `PersonClassificationCommandControllerTest#shouldDeactivateClassification_whenFound` | `PersonClassificationCommandControllerTest#shouldReturn404_whenDeactivateMissing` | - | - |
| GET | `/api/v1/companies/{companyId}/person-classifications` | `PersonClassificationQueryControllerTest#shouldListClassifications_whenValidRequest` | `PersonClassificationQueryControllerTest#shouldReturn400_whenIncludeInactiveNotBoolean` | - | - |
| POST | `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications` | `PersonClassificationCommandControllerTest#shouldAssignClassification_whenValidRequest` | `PersonClassificationCommandControllerTest#shouldReturn404_whenAssignClassificationMissingEntity` | - | - |
| DELETE | `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications/{assignmentId}` | `PersonClassificationCommandControllerTest#shouldRemoveAssignment_whenExists` | `PersonClassificationCommandControllerTest#shouldReturn404_whenRemoveMissingAssignment` | - | - |
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
- Fachliche Negativfälle aus Web-/Integrationstests unter `src/test/java/de/innologic/personservice/web/*.java` und `src/test/java/de/innologic/personservice/PersonTeamIntegrationTests.java`.

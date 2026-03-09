# Test Matrix Person Service (v1)

## Persons

| Endpoint | Method | Positiv-Testfall | Negativ-Testfall | Statuscodes | Wichtige Assertions | Notizen/Edgecases |
|---|---|---|---|---|---|---|
| `/api/v1/companies/{companyId}/persons` | GET | Paging-list mit `PersonQueryControllerTest#shouldListPersons_whenValidRequest` | `PersonQueryControllerTest#shouldReturn400_whenInvalidIncludeTrashedParameter` | `200`, `400` | Paging-Metadaten, `includeTrashed=false` filtert | |
| `/api/v1/companies/{companyId}/persons/{personId}` | GET | `PersonQueryControllerTest#shouldGetPerson_whenPersonExists` | `PersonQueryControllerTest#shouldReturn404_whenPersonDoesNotExist` | `200`, `404` | `personId`, Audit-Felder, `contactOwnerType` | |
| `/api/v1/companies/{companyId}/persons` | POST | `PersonCommandControllerTest#shouldCreatePerson_whenValidRequest` | `PersonCommandControllerTest#shouldReturn400_whenCreatePersonMissingCompanyId` | `201`, `400` | `createdBy`, `contactOwnerId` gesetzt | |
| `/api/v1/companies/{companyId}/persons/{personId}` | PATCH | `PersonCommandControllerTest#shouldUpdatePerson_whenValidPatch` | `PersonCommandControllerTest#shouldReturn404_whenUpdatePersonNotFound` | `200`, `404` | `modifiedAt` aktualisiert | |
| `/api/v1/companies/{companyId}/persons/{personId}/trash` | POST | `PersonCommandControllerTest#shouldTrashPerson_whenPersonExists` | `PersonCommandControllerTest#shouldReturn404_whenTrashPersonNotFound` | `200`, `404` | `trashedAt` gesetzt | |
| `/api/v1/companies/{companyId}/persons/{personId}/restore` | POST | `PersonCommandControllerTest#shouldRestorePerson_whenPersonExists` | `PersonCommandControllerTest#shouldReturn404_whenRestorePersonNotFound` | `200`, `404` | `trashedAt` `null` | |

## Teams

| Endpoint | Method | Positiv-Testfall | Negativ-Testfall | Statuscodes | Wichtige Assertions | Notizen/Edgecases |
|---|---|---|---|---|---|---|
| `/api/v1/companies/{companyId}/teams` | POST | `TeamCommandControllerTest#shouldCreateTeam_whenValidRequest` | `TeamCommandControllerTest#shouldReturn409_whenCreateTeamHasDuplicateName` | `201`, `409` | Eintrag mit `teamId`, `audit` | |
| `/api/v1/companies/{companyId}/teams/{teamId}` | PATCH | `TeamCommandControllerTest#shouldUpdateTeam_whenValidPatch` | `TeamCommandControllerTest#shouldReturn404_whenUpdateTeamNotFound` | `200`, `404` | `modifiedAt` aktualisiert | |
| `/api/v1/companies/{companyId}/teams/{teamId}/trash` | POST | `TeamCommandControllerTest#shouldTrashTeam_whenTeamExists` | `TeamCommandControllerTest#shouldReturn404_whenTrashTeamNotFound` | `200`, `404` | `trashedAt` gesetzt | |
| `/api/v1/companies/{companyId}/teams/{teamId}/restore` | POST | `TeamCommandControllerTest#shouldRestoreTeam_whenTeamExists` | `TeamCommandControllerTest#shouldReturn404_whenRestoreTeamNotFound` | `200`, `404` | `trashedAt` `null` | |

## Memberships

| Endpoint | Method | Positiv-Testfall | Negativ-Testfall | Statuscodes | Wichtige Assertions | Notizen/Edgecases |
|---|---|---|---|---|---|---|
| `/api/v1/companies/{companyId}/teams/{teamId}/members` | POST | `TeamCommandControllerTest#shouldAddMember_whenValidRequest` | `TeamCommandControllerTest#shouldReturn409_whenAddMemberDuplicate` | `201`, `409` | Membership-Audit & `isPrimary` | |
| `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | DELETE | `TeamCommandControllerTest#shouldRemoveMember_whenExistingMembership` | `TeamCommandControllerTest#shouldReturn404_whenRemoveMemberNotFound` | `204`, `404` | `trashedAt` gesetzt | |

## Person Classifications

| Endpoint | Method | Positiv-Testfall | Negativ-Testfall | Statuscodes | Wichtige Assertions | Notizen/Edgecases |
|---|---|---|---|---|---|---|
| `/api/v1/companies/{companyId}/person-classifications` | POST | `PersonClassificationCommandControllerTest#shouldCreateClassification_whenValidRequest` | `PersonClassificationCommandControllerTest#shouldReturn409_whenCreateClassificationConflict` | `201`, `409` | `classificationId`, `active`, Audit-Felder | Unique `companyId+key+code` enforced |
| `/api/v1/companies/{companyId}/person-classifications/{classificationId}` | PATCH | `PersonClassificationCommandControllerTest#shouldUpdateClassification_whenValidPatch` | `PersonClassificationCommandControllerTest#shouldReturn404_whenUpdateClassificationNotFound` | `200`, `404` | `label` update + audit | Key/code duplicates guarded |
| `/api/v1/companies/{companyId}/person-classifications/{classificationId}/deactivate` | POST | `PersonClassificationCommandControllerTest#shouldDeactivateClassification_whenFound` | `PersonClassificationCommandControllerTest#shouldReturn404_whenDeactivateMissing` | `200`, `404` | `active=false` | |
| `/api/v1/companies/{companyId}/person-classifications` | GET | `PersonClassificationQueryControllerTest#shouldListClassifications_whenValidRequest` | `PersonClassificationQueryControllerTest#shouldReturn400_whenIncludeInactiveNotBoolean` | `200`, `400` | `includeInactive` parameter honored | |

## Person Classification Assignments

| Endpoint | Method | Positiv-Testfall | Negativ-Testfall | Statuscodes | Wichtige Assertions | Notizen/Edgecases |
|---|---|---|---|---|---|---|
| `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications` | POST | `PersonClassificationCommandControllerTest#shouldAssignClassification_whenValidRequest` | `PersonClassificationCommandControllerTest#shouldReturn404_whenAssignClassificationMissingEntity` | `201`, `404` | `assignmentId`, `classificationId`, `personId` | inactive classification rejected |
| `/api/v1/companies/{companyId}/person-classifications/persons/{personId}/classifications/{assignmentId}` | DELETE | `PersonClassificationCommandControllerTest#shouldRemoveAssignment_whenExists` | `PersonClassificationCommandControllerTest#shouldReturn404_whenRemoveMissingAssignment` | `204`, `404` | `trashedAt` + `validTo` set | |

# Test Matrix Person Service (v1)

## Persons

| Endpoint | Method | Positiv-Testfall | Negativ-Testfall | Statuscodes | Wichtige Assertions | Notizen/Edgecases |
|---|---|---|---|---|---|---|
| `/api/v1/companies/{companyId}/persons` | GET | Personenliste mit Paging; `q` filtert Treffer; `includeTrashed=false` liefert nur aktive | Ungueltige Query-Parameter (`size<0`, ungueltiges Sort-Feld), Parsing-Fehler bei Parametern | `200`, `400` | `200`: Page-Felder (`content`,`totalElements`,`size`,`number`), jedes Element mit `id`,`companyId`,`createdAt`,`modifiedAt`; bei aktivem Datensatz `trashedAt=null`.<br>`400`: ProblemDetails mit `title`,`detail`,`status`,`type` | Bei `includeTrashed=true` muessen auch getrashte Elemente erscheinen |
| `/api/v1/companies/{companyId}/persons/{personId}` | GET | Aktive Person derselben Company abrufen | Person existiert nicht; Person gehoert anderer Company; Person ist getrasht | `200`, `404` | `200`: `id==personId`, `companyId==path`, fachliche Felder + Audit-Felder.<br>`404`: ProblemDetails `title=Not Found`, `status=404`, aussagekraeftiges `detail` | Mandantentrennung wird hier indirekt als NotFound sichtbar |
| `/api/v1/companies/{companyId}/persons` | POST | Person mit gueltigem Body anlegen (mit/ohne `X-Actor-Id`) | Validation-Fehler (`companyId` fehlt, `email` invalid, Feldlaengen); `body.companyId != path.companyId` | `201`, `400` | `201`: neue `id`, `companyId==path`, `createdAt` gesetzt, `createdBy/modifiedBy` = Header oder `system`.<br>`400`: ProblemDetails mit `title` (`Validation Failed`/`Bad Request`), `status=400`, `detail` enthaelt Feld-/Regelverletzung | Kein `409` fuer Person aktuell implementiert |
| `/api/v1/companies/{companyId}/persons/{personId}` | PATCH | Teilupdate aktiver Person, `modifiedAt/modifiedBy` wird aktualisiert | Validation (`email` invalid, max length), Person nicht gefunden/anderer Mandant/getrasht | `200`, `400`, `404` | `200`: geaenderte Felder sichtbar, ungeaenderte Felder bleiben, `modifiedBy` gesetzt.<br>`404`: ProblemDetails NotFound.<br>`400`: ProblemDetails Validation | Patch ist partiell (null-Werte werden ignoriert) |
| `/api/v1/companies/{companyId}/persons/{personId}/trash` | POST | Aktive Person soft-deleten | Person nicht gefunden; bereits getrasht; anderer Mandant | `200`, `404` | `200`: `trashedAt` gesetzt, `trashedBy` gesetzt, `modifiedBy` gesetzt.<br>`404`: ProblemDetails NotFound | Danach darf Person bei Listenstandard nicht erscheinen |
| `/api/v1/companies/{companyId}/persons/{personId}/restore` | POST | Getrashte Person wiederherstellen | Person nicht gefunden/anderer Mandant | `200`, `404` | `200`: `trashedAt=null`, `trashedBy=null`, `modifiedBy` aktualisiert.<br>`404`: ProblemDetails NotFound | Restore ist effektiv idempotent fuer bestehende, nicht-getrashte Person |

## Teams

| Endpoint | Method | Positiv-Testfall | Negativ-Testfall | Statuscodes | Wichtige Assertions | Notizen/Edgecases |
|---|---|---|---|---|---|---|
| `/api/v1/companies/{companyId}/teams` | GET | Teamliste mit Paging; `q` filtert auf Name/Beschreibung; `includeTrashed=false` nur aktive | Ungueltige Query/Page-Parameter | `200`, `400` | `200`: Page-Struktur + Teamfelder (`id`,`companyId`,`name`,`description`,`createdAt`,`modifiedAt`).<br>`400`: ProblemDetails-Felder vorhanden | Bei `includeTrashed=true` getrashte Teams enthalten |
| `/api/v1/companies/{companyId}/teams/{teamId}` | GET | Aktives Team abrufen | Team nicht gefunden; anderer Mandant; Team getrasht | `200`, `404` | `200`: `id`,`companyId`,`name`, Audit-Felder.<br>`404`: ProblemDetails NotFound | Mandantentrennung ueber companyId im Zugriff |
| `/api/v1/companies/{companyId}/teams` | POST | Team mit neuem Namen in Company anlegen | Validation (`companyId` fehlt, `name` blank/zu lang), `body.companyId != path.companyId`, Name bereits in Company vorhanden | `201`, `400`, `409` | `201`: `id`, `companyId`, `name`, `createdAt` gesetzt.<br>`409`: ProblemDetails `title=Conflict`,`status=409`.<br>`400`: ProblemDetails Validation/BadRequest | Unique-Regel ist pro Company (`company_id`,`name`) |
| `/api/v1/companies/{companyId}/teams/{teamId}` | PATCH | Teamdaten/Name auf freien Namen aktualisieren | Team nicht gefunden/anderer Mandant/getrasht; Validation; Zielname bereits vergeben | `200`, `400`, `404`, `409` | `200`: geaenderte Felder + `modifiedBy` gesetzt.<br>`409`: ProblemDetails Conflict.<br>`404`/`400`: ProblemDetails passend | Bei Namensaenderung explizit Duplicate-Test erforderlich |
| `/api/v1/companies/{companyId}/teams/{teamId}/trash` | POST | Team soft-deleten | Team nicht gefunden/anderer Mandant/bereits getrasht | `200`, `404` | `200`: Team `trashedAt/trashedBy` gesetzt.<br>`404`: ProblemDetails NotFound | Regel: aktive Team-Members werden ebenfalls getrasht, `leftAt` ggf. gesetzt |
| `/api/v1/companies/{companyId}/teams/{teamId}/restore` | POST | Team wiederherstellen | Team nicht gefunden/anderer Mandant | `200`, `404` | `200`: Team `trashedAt/trashedBy` wieder `null`.<br>`404`: ProblemDetails NotFound | Team-Members werden aktuell nicht automatisch restored |

## Memberships

| Endpoint | Method | Positiv-Testfall | Negativ-Testfall | Statuscodes | Wichtige Assertions | Notizen/Edgecases |
|---|---|---|---|---|---|---|
| `/api/v1/companies/{companyId}/teams/{teamId}/members` | GET | Aktive Mitglieder eines aktiven Teams abrufen | Team nicht gefunden/anderer Mandant/getrasht | `200`, `404` | `200`: Liste von `TeamMemberResponse` mit `id`,`companyId`,`teamId`,`personId`,`role`,`joinedAt`,`leftAt`, Audit/Trash-Feldern.<br>`404`: ProblemDetails NotFound | Nur nicht-getrashte Mitgliedschaften werden geliefert |
| `/api/v1/companies/{companyId}/teams/{teamId}/members` | POST | Mitglied (aktive Person) zu aktivem Team hinzufuegen | Validation (`personId` fehlt, `role` zu lang); Team/Person nicht gefunden oder anderer Mandant; aktive Mitgliedschaft bereits vorhanden | `201`, `400`, `404`, `409` | `201`: `teamId`,`personId`,`role` korrekt, Audit-Felder gesetzt.<br>`409`: ProblemDetails Conflict.<br>`404`/`400`: ProblemDetails passend | Wenn getrashte Beziehung existiert, wird aktuell neue aktive Beziehung verhindert nur bei aktiver Duplikation |
| `/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}` | DELETE | Mitgliedschaft soft-entfernen | Keine aktive Mitgliedschaft gefunden; anderer Mandant | `204`, `404` | `204`: leerer Body.<br>`404`: ProblemDetails NotFound | Soft-Delete: intern `trashedAt/trashedBy` und ggf. `leftAt` gesetzt |

## Communication Refs

| Endpoint | Method | Positiv-Testfall | Negativ-Testfall | Statuscodes | Wichtige Assertions | Notizen/Edgecases |
|---|---|---|---|---|---|---|
| `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | GET | Referenzliste einer aktiven Person laden | Person nicht gefunden/anderer Mandant/getrasht | `200`, `404` | `200`: `companyId`,`personId`,`communicationIds[]` vorhanden.<br>`404`: ProblemDetails NotFound | Keine Fremdvalidierung gegen Communication-Service |
| `/api/v1/companies/{companyId}/persons/{personId}/communication-refs` | PUT | Vollstaendiges Ersetzen der Refs (add/remove/restore) mit gueltigen IDs | Validation (`communicationIds` fehlt; blank ID; ID zu lang); Person nicht gefunden/anderer Mandant/getrasht | `200`, `400`, `404` | `200`: Rueckgabe enthaelt finalen Satz `communicationIds[]`, `companyId`,`personId` konsistent.<br>`400`/`404`: ProblemDetails-Felder | Edgecase: Duplikate im Request werden serverseitig auf eindeutige IDs normalisiert |

## Nicht vorhanden in v1

- Keine separaten REST-Ressourcen fuer **Classifications** oder **Engagements** gefunden.


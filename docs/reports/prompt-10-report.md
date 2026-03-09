# Prompt 10 Report

## Changed files
- docs/reports/prompt-10-report.md

## git status --short
## master...origin/master [ahead 4]
 M doc/dependencies.md
 D doc/test-matrix-person-service.md
 D doc/test-matrix-v1.1-security.md
 D doc/test-matrix-v1.1.md
A  docs/reports/prompt-10-report.md
?? diff-output.patch

## git diff --name-only
docs/reports/prompt-10-report.md

## Unified diff
`diff
diff --git a/docs/reports/prompt-10-report.md b/docs/reports/prompt-10-report.md
new file mode 100644
index 0000000..2799fb8
--- /dev/null
+++ b/docs/reports/prompt-10-report.md
@@ -0,0 +1,521 @@
+# Prompt 10 Report
+
+## Changed files
+- docs/reports/prompt-10-report.md
+
+## git status --short
+## master...origin/master [ahead 4]
+D  doc/dependencies.md
+D  doc/test-matrix-person-service.md
+D  doc/test-matrix-v1.1-security.md
+D  doc/test-matrix-v1.1.md
+?? diff-output.patch
+?? doc/dependencies.md
+
+## git diff --name-only
+docs/reports/prompt-10-report.md
+
+## Unified diff
+`diff
+diff --git a/docs/reports/prompt-10-report.md b/docs/reports/prompt-10-report.md
new file mode 100644
index 0000000..3d18e08
--- /dev/null
+++ b/docs/reports/prompt-10-report.md
@@ -0,0 +1,1048 @@
+# Prompt 10 Report
+
+## Changed files
+- docs/reports/prompt-10-report.md
+
+## git status --short
+## master...origin/master [ahead 4]
+ M doc/dependencies.md
+ D doc/test-matrix-person-service.md
+ D doc/test-matrix-v1.1-security.md
+ D doc/test-matrix-v1.1.md
+A  docs/reports/prompt-10-report.md
+?? diff-output.patch
+
+## git diff --name-only
+docs/reports/prompt-10-report.md
+
+## Unified diff
+`diff
+diff --git a/docs/reports/prompt-10-report.md b/docs/reports/prompt-10-report.md
+new file mode 100644
+index 0000000..2799fb8
+--- /dev/null
++++ b/docs/reports/prompt-10-report.md
+@@ -0,0 +1,521 @@
++# Prompt 10 Report
++
++## Changed files
++- docs/reports/prompt-10-report.md
++
++## git status --short
++## master...origin/master [ahead 4]
++D  doc/dependencies.md
++D  doc/test-matrix-person-service.md
++D  doc/test-matrix-v1.1-security.md
++D  doc/test-matrix-v1.1.md
++?? diff-output.patch
++?? doc/dependencies.md
++
++## git diff --name-only
++docs/reports/prompt-10-report.md
++
++## Unified diff
++`diff
++<<diff-here>>
++`
++
++## Raw build/test output
++`	ext
++15:08:16.100 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [de.innologic.personservice.PersonServiceApplicationTests]: PersonServiceApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++15:08:16.359 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.PersonServiceApplicationTests
++15:08:16.593 [main] INFO org.springframework.boot.devtools.restart.RestartApplicationListener -- Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:17.047+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : Starting PersonServiceApplicationTests using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:17.051+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : The following 1 profile is active: "test"
++2026-03-09T15:08:19.076+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
++2026-03-09T15:08:19.210+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 117 ms. Found 4 JPA repository interfaces.
++2026-03-09T15:08:20.560+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
++2026-03-09T15:08:20.814+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:person_service_test user=SA
++2026-03-09T15:08:20.817+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
++2026-03-09T15:08:20.887+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:person_service_test (H2 2.4)
++2026-03-09T15:08:20.907+01:00  WARN 9048 --- [person-service] [           main] o.f.c.internal.database.base.Database    : Using H2 2.4.240 which is newer than the version Flyway has been verified with. The latest verified version of H2 is 2.3.232.
++2026-03-09T15:08:20.950+01:00  INFO 9048 --- [person-service] [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Schema history table "PUBLIC"."flyway_schema_history_person" does not exist yet
++2026-03-09T15:08:20.952+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.019s)
++2026-03-09T15:08:20.959+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.Flyway                 : All configured schemas are empty; baseline operation skipped. A baseline or migration script with a lower version than the baseline version may execute if available. Check the Schemas parameter if this is not intended.
++2026-03-09T15:08:20.960+01:00  INFO 9048 --- [person-service] [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Creating Schema History table "PUBLIC"."flyway_schema_history_person" ...
++2026-03-09T15:08:21.015+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": << Empty Schema >>
++2026-03-09T15:08:21.029+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "1 - init"
++2026-03-09T15:08:21.068+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "2 - person communication refs"
++2026-03-09T15:08:21.079+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "3 - company id to varchar36"
++2026-03-09T15:08:21.124+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "4 - person public id"
++2026-03-09T15:08:21.136+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Successfully applied 4 migrations to schema "PUBLIC", now at version v4 (execution time 00:00.075s)
++2026-03-09T15:08:21.301+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
++2026-03-09T15:08:21.430+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.2.1.Final
++2026-03-09T15:08:22.053+01:00  INFO 9048 --- [person-service] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
++2026-03-09T15:08:22.132+01:00  WARN 9048 --- [person-service] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
++2026-03-09T15:08:22.152+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
++	Database JDBC URL [jdbc:h2:mem:person_service_test]
++	Database driver: H2 JDBC Driver
++	Database dialect: H2Dialect
++	Database version: 2.4.240
++	Default catalog/schema: person_service_test/PUBLIC
++	Autocommit mode: undefined/unknown
++	Isolation level: READ_COMMITTED [default READ_COMMITTED]
++	JDBC fetch size: 100
++	Pool: DataSourceConnectionProvider
++	Minimum pool size: undefined/unknown
++	Maximum pool size: undefined/unknown
++2026-03-09T15:08:23.588+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
++2026-03-09T15:08:23.596+01:00  INFO 9048 --- [person-service] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
++2026-03-09T15:08:23.795+01:00  INFO 9048 --- [person-service] [           main] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
++2026-03-09T15:08:27.687+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : Started PersonServiceApplicationTests in 11.109 seconds (process running for 13.612)
++2026-03-09T15:08:28.359+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.PersonTeamIntegrationTests]: PersonTeamIntegrationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:28.380+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.PersonTeamIntegrationTests
++2026-03-09T15:08:28.407+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:28.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : Starting PersonTeamIntegrationTests using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:28.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : The following 1 profile is active: "test"
++2026-03-09T15:08:28.929+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
++2026-03-09T15:08:28.954+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 22 ms. Found 4 JPA repository interfaces.
++2026-03-09T15:08:29.168+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Starting...
++2026-03-09T15:08:29.170+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-2 - Added connection conn10: url=jdbc:h2:mem:person_service_test user=SA
++2026-03-09T15:08:29.171+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Start completed.
++2026-03-09T15:08:29.174+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:person_service_test (H2 2.4)
++2026-03-09T15:08:29.179+01:00  WARN 9048 --- [person-service] [           main] o.f.c.internal.database.base.Database    : Using H2 2.4.240 which is newer than the version Flyway has been verified with. The latest verified version of H2 is 2.3.232.
++2026-03-09T15:08:29.188+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.006s)
++2026-03-09T15:08:29.196+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": 4
++2026-03-09T15:08:29.197+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Schema "PUBLIC" is up to date. No migration necessary.
++2026-03-09T15:08:29.229+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
++2026-03-09T15:08:29.287+01:00  INFO 9048 --- [person-service] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
++2026-03-09T15:08:29.289+01:00  WARN 9048 --- [person-service] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
++2026-03-09T15:08:29.290+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
++	Database JDBC URL [jdbc:h2:mem:person_service_test]
++	Database driver: H2 JDBC Driver
++	Database dialect: H2Dialect
++	Database version: 2.4.240
++	Default catalog/schema: person_service_test/PUBLIC
++	Autocommit mode: undefined/unknown
++	Isolation level: READ_COMMITTED [default READ_COMMITTED]
++	JDBC fetch size: 100
++	Pool: DataSourceConnectionProvider
++	Minimum pool size: undefined/unknown
++	Maximum pool size: undefined/unknown
++2026-03-09T15:08:29.403+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
++2026-03-09T15:08:29.404+01:00  INFO 9048 --- [person-service] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
++2026-03-09T15:08:30.078+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:30.079+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:30.082+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms
++2026-03-09T15:08:30.105+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : Started PersonTeamIntegrationTests in 1.699 seconds (process running for 16.03)
++2026-03-09T15:08:30.312+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3001 actor=it-tester
++2026-03-09T15:08:30.323+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3001 actor=it-tester
++2026-03-09T15:08:30.400+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
++2026-03-09T15:08:30.413+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
++2026-03-09T15:08:30.529+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3001 person=30e8ac68-a336-4877-acbb-d139e8df90f0
++2026-03-09T15:08:30.532+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3001 person=30e8ac68-a336-4877-acbb-d139e8df90f0
++2026-03-09T15:08:30.683+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Found personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
++2026-03-09T15:08:30.685+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
++2026-03-09T15:08:30.739+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3005 actor=it-tester
++2026-03-09T15:08:30.740+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3005 actor=it-tester
++2026-03-09T15:08:30.742+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=6b22b2ea-36be-4bd6-8f54-05ef63561784 for company=3005
++2026-03-09T15:08:30.743+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=6b22b2ea-36be-4bd6-8f54-05ef63561784 for company=3005
++2026-03-09T15:08:30.748+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
++2026-03-09T15:08:30.748+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
++2026-03-09T15:08:30.750+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Person not found for company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
++2026-03-09T15:08:30.802+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
++2026-03-09T15:08:30.803+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
++2026-03-09T15:08:30.805+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Person not found for company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
++2026-03-09T15:08:30.821+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3003 actor=it-tester
++2026-03-09T15:08:30.822+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3003 actor=it-tester
++2026-03-09T15:08:30.825+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
++2026-03-09T15:08:30.826+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
++2026-03-09T15:08:30.835+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=3003 person=e6ca8e47-ff29-4c84-928b-7435558bc650 actor=it-tester
++2026-03-09T15:08:30.836+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updating person request company=3003 person=e6ca8e47-ff29-4c84-928b-7435558bc650 actor=it-tester
++2026-03-09T15:08:30.840+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updated personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
++2026-03-09T15:08:30.855+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
++2026-03-09T15:08:30.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3002 actor=it-tester
++2026-03-09T15:08:30.877+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3002 actor=it-tester
++2026-03-09T15:08:30.880+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
++2026-03-09T15:08:30.882+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
++2026-03-09T15:08:30.886+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3002 person=97445f95-1eba-4ca2-93b6-aa54b171a255
++2026-03-09T15:08:30.886+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3002 person=97445f95-1eba-4ca2-93b6-aa54b171a255
++2026-03-09T15:08:30.888+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Found personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
++2026-03-09T15:08:30.889+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
++2026-03-09T15:08:31.512+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Restoring person request company=1 person=10 actor=ignored-header-actor
++2026-03-09T15:08:31.525+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Restored personId=null for company=1
++2026-03-09T15:08:31.553+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updating person request company=1 person=10 actor=ignored-header-actor
++2026-03-09T15:08:31.554+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updated personId=null for company=1
++2026-03-09T15:08:31.565+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=1 actor=ignored-header-actor
++2026-03-09T15:08:31.566+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=null for company=1
++2026-03-09T15:08:31.575+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Trashing person request company=1 person=10 actor=ignored-header-actor
++2026-03-09T15:08:31.577+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Trashed personId=null for company=1
++2026-03-09T15:08:32.168+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
++2026-03-09T15:08:32.177+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Added membershipId=membership-10 team=team-20 person=null
++2026-03-09T15:08:32.216+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
++2026-03-09T15:08:32.228+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
++2026-03-09T15:08:32.229+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Person person-10 already has an active primary membership in company=company-1
++2026-03-09T15:08:32.271+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.JwtScopesConverterSecurityTest]: JwtScopesConverterSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:32.364+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.JwtScopesConverterSecurityTest
++2026-03-09T15:08:32.375+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:32.448+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : Starting JwtScopesConverterSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:32.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : The following 1 profile is active: "test"
++2026-03-09T15:08:33.526+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:33.526+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:33.529+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms
++2026-03-09T15:08:33.555+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : Started JwtScopesConverterSecurityTest in 1.18 seconds (process running for 19.48)
++2026-03-09T15:08:33.742+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:33.743+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:33.743+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
++2026-03-09T15:08:33.765+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:33.765+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:33.766+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
++2026-03-09T15:08:33.786+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:33.787+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:33.787+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
++2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
++2026-03-09T15:08:33.838+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.OpsControllerBuildPropertiesTest]: OpsControllerBuildPropertiesTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:33.855+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.OpsControllerBuildPropertiesTest
++2026-03-09T15:08:33.865+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:33.903+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : Starting OpsControllerBuildPropertiesTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:33.904+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : The following 1 profile is active: "test"
++2026-03-09T15:08:34.505+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:34.505+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:34.510+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
++2026-03-09T15:08:34.526+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : Started OpsControllerBuildPropertiesTest in 0.661 seconds (process running for 20.451)
++2026-03-09T15:08:34.605+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.OpsControllerTest]: OpsControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:34.673+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.OpsControllerTest
++2026-03-09T15:08:34.683+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:34.736+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : Starting OpsControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:34.737+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : The following 1 profile is active: "test"
++2026-03-09T15:08:35.107+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:35.108+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:35.110+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
++2026-03-09T15:08:35.120+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : Started OpsControllerTest in 0.438 seconds (process running for 21.045)
++2026-03-09T15:08:35.171+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonCommandControllerTest]: PersonCommandControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:35.186+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonCommandControllerTest
++2026-03-09T15:08:35.193+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:35.225+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : Starting PersonCommandControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:35.225+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : The following 1 profile is active: "test"
++2026-03-09T15:08:35.678+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:35.678+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:35.682+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
++2026-03-09T15:08:35.690+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : Started PersonCommandControllerTest in 0.498 seconds (process running for 21.615)
++2026-03-09T15:08:35.726+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=1 person=404 actor=actor-1
++2026-03-09T15:08:35.813+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=1 person=10 actor=actor-1
++2026-03-09T15:08:35.814+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restored personId=person-10 for company=1
++2026-03-09T15:08:35.845+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=1 person=10 actor=actor-1
++2026-03-09T15:08:35.845+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trashed personId=person-10 for company=1
++2026-03-09T15:08:35.870+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=1 person=404 actor=actor-1
++2026-03-09T15:08:35.890+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=1 actor=actor-1
++2026-03-09T15:08:35.891+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=person-10 for company=1
++2026-03-09T15:08:35.913+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=1 person=10 actor=actor-1
++2026-03-09T15:08:35.913+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=person-10 for company=1
++2026-03-09T15:08:35.928+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=1 person=404 actor=actor-1
++2026-03-09T15:08:35.944+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonCommunicationRefControllerTest]: PersonCommunicationRefControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:35.958+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonCommunicationRefControllerTest
++2026-03-09T15:08:35.964+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:35.994+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : Starting PersonCommunicationRefControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:35.995+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : The following 1 profile is active: "test"
++2026-03-09T15:08:36.351+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:36.351+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:36.353+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
++2026-03-09T15:08:36.365+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : Started PersonCommunicationRefControllerTest in 0.403 seconds (process running for 22.29)
++2026-03-09T15:08:36.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replace communication refs request company=1 person=10 actor=actor-1 desiredCount=2
++2026-03-09T15:08:36.416+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replaced 2 communication refs for person=10 company=1
++2026-03-09T15:08:36.445+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=1 person=404
++2026-03-09T15:08:36.460+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=1 person=10
++2026-03-09T15:08:36.461+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Returning 2 communication refs for person=10 company=1
++2026-03-09T15:08:36.486+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonEndpointsSecurityTest]: PersonEndpointsSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:36.494+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonEndpointsSecurityTest
++2026-03-09T15:08:36.503+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:36.528+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : Starting PersonEndpointsSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:36.528+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : The following 1 profile is active: "test"
++2026-03-09T15:08:37.144+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:37.146+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:37.148+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
++2026-03-09T15:08:37.165+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : Started PersonEndpointsSecurityTest in 0.663 seconds (process running for 23.09)
++2026-03-09T15:08:37.212+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
++2026-03-09T15:08:37.213+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
++2026-03-09T15:08:37.356+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
++2026-03-09T15:08:37.357+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
++2026-03-09T15:08:37.411+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replace communication refs request company=100 person=10 actor=null desiredCount=2
++2026-03-09T15:08:37.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replaced 2 communication refs for person=10 company=100
++2026-03-09T15:08:37.434+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=999
++2026-03-09T15:08:37.475+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=100 person=10
++2026-03-09T15:08:37.475+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Returning 2 communication refs for person=10 company=100
++2026-03-09T15:08:37.600+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=100 actor=null
++2026-03-09T15:08:37.602+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=null for company=100
++2026-03-09T15:08:37.664+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=100 person=10 actor=null
++2026-03-09T15:08:37.665+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trashed personId=null for company=100
++2026-03-09T15:08:37.684+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
++2026-03-09T15:08:37.684+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
++2026-03-09T15:08:37.777+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=100 person=10 actor=null
++2026-03-09T15:08:37.778+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=null for company=100
++2026-03-09T15:08:37.821+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=100 person=10 actor=null
++2026-03-09T15:08:37.822+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restored personId=null for company=100
++2026-03-09T15:08:37.883+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=100 includeTrashed=false query=null
++2026-03-09T15:08:37.884+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=100 includeTrashed=false query=null
++2026-03-09T15:08:37.895+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
++	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
++	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
++
++2026-03-09T15:08:37.920+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonQueryControllerTest]: PersonQueryControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:37.933+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonQueryControllerTest
++2026-03-09T15:08:37.944+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:37.970+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : Starting PersonQueryControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:37.971+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : The following 1 profile is active: "test"
++2026-03-09T15:08:38.251+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:38.251+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:38.252+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
++2026-03-09T15:08:38.261+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : Started PersonQueryControllerTest in 0.318 seconds (process running for 24.187)
++2026-03-09T15:08:38.278+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=1 person=99
++2026-03-09T15:08:38.296+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=1 person=10
++2026-03-09T15:08:38.296+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=person-10 for company=1
++2026-03-09T15:08:38.318+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=1 includeTrashed=false query=null
++2026-03-09T15:08:38.319+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=1 includeTrashed=false query=null
++2026-03-09T15:08:38.326+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
++	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
++	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
++
++2026-03-09T15:08:38.374+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.SecurityBaselineTest]: SecurityBaselineTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:38.382+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.SecurityBaselineTest
++2026-03-09T15:08:38.388+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:38.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : Starting SecurityBaselineTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:38.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : The following 1 profile is active: "test"
++2026-03-09T15:08:38.766+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:38.766+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:38.770+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
++2026-03-09T15:08:38.783+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : Started SecurityBaselineTest in 0.394 seconds (process running for 24.708)
++2026-03-09T15:08:38.829+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamCommandControllerTest]: TeamCommandControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:38.844+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamCommandControllerTest
++2026-03-09T15:08:38.848+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:38.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : Starting TeamCommandControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:38.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : The following 1 profile is active: "test"
++2026-03-09T15:08:39.196+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:39.196+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:39.200+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
++2026-03-09T15:08:39.211+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : Started TeamCommandControllerTest in 0.363 seconds (process running for 25.137)
++2026-03-09T15:08:39.230+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=1 team=team-20 person=person-10 actor=actor-1
++2026-03-09T15:08:39.231+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Removed member person=person-10 from team=team-20 for company=1
++2026-03-09T15:08:39.248+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=1 team=team-20 actor=actor-1
++2026-03-09T15:08:39.249+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restored teamId=team-20 for company=1
++2026-03-09T15:08:39.280+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=null actor=actor-1
++2026-03-09T15:08:39.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=1 team=team-404 actor=actor-1
++2026-03-09T15:08:39.315+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=true actor=actor-1
++2026-03-09T15:08:39.331+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=1 name=Core Team actor=actor-1
++2026-03-09T15:08:39.332+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Created teamId=team-20 for company=1
++2026-03-09T15:08:39.348+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=1 team=team-20 actor=actor-1
++2026-03-09T15:08:39.349+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Updated teamId=team-20 for company=1
++2026-03-09T15:08:39.361+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=1 team=team-20 person=person-404 actor=actor-1
++2026-03-09T15:08:39.391+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=1 name=Core Team actor=actor-1
++2026-03-09T15:08:39.404+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=1 team=team-404 actor=actor-1
++2026-03-09T15:08:39.418+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=1 team=team-404 actor=actor-1
++2026-03-09T15:08:39.432+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=null actor=actor-1
++2026-03-09T15:08:39.433+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Added membershipId=membership-50 team=team-20 person=person-10
++2026-03-09T15:08:39.455+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=1 team=team-20 actor=actor-1
++2026-03-09T15:08:39.455+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trashed teamId=team-20 for company=1
++2026-03-09T15:08:39.467+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamEndpointsSecurityTest]: TeamEndpointsSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:39.483+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamEndpointsSecurityTest
++2026-03-09T15:08:39.492+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:39.519+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : Starting TeamEndpointsSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:39.520+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : The following 1 profile is active: "test"
++2026-03-09T15:08:39.967+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:39.968+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:39.973+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 5 ms
++2026-03-09T15:08:39.980+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : Started TeamEndpointsSecurityTest in 0.489 seconds (process running for 25.906)
++2026-03-09T15:08:40.067+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=100 name=Core Team actor=null
++2026-03-09T15:08:40.069+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Created teamId=team-20 for company=100
++2026-03-09T15:08:40.170+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=100 team=team-20 actor=null
++2026-03-09T15:08:40.173+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restored teamId=team-20 for company=100
++2026-03-09T15:08:40.203+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : List teams request company=100 includeTrashed=false query=null
++2026-03-09T15:08:40.205+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 teams for company=100 includeTrashed=false query=null
++2026-03-09T15:08:40.210+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
++	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
++	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
++
++2026-03-09T15:08:40.246+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=100 team=team-20
++2026-03-09T15:08:40.246+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 members for team=team-20 company=100
++2026-03-09T15:08:40.285+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=100 team=team-20 person=person-10 actor=null
++2026-03-09T15:08:40.286+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Removed member person=person-10 from team=team-20 for company=100
++2026-03-09T15:08:40.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=100 team=team-20 actor=null
++2026-03-09T15:08:40.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Updated teamId=team-20 for company=100
++2026-03-09T15:08:40.327+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=100 team=team-20
++2026-03-09T15:08:40.327+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Found teamId=team-20 for company=100
++2026-03-09T15:08:40.351+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=100 team=team-20 person=person-10 primary=null actor=null
++2026-03-09T15:08:40.352+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Added membershipId=membership-99 team=team-20 person=person-10
++2026-03-09T15:08:40.379+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=100 team=team-999
++2026-03-09T15:08:40.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=100 team=team-20 person=person-10 primary=null actor=null
++2026-03-09T15:08:40.560+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=100 team=team-20 actor=null
++2026-03-09T15:08:40.562+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trashed teamId=team-20 for company=100
++2026-03-09T15:08:40.606+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamQueryControllerTest]: TeamQueryControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:40.613+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamQueryControllerTest
++2026-03-09T15:08:40.620+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:40.657+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : Starting TeamQueryControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:40.657+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : The following 1 profile is active: "test"
++2026-03-09T15:08:40.896+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:40.896+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:40.897+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
++2026-03-09T15:08:40.909+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : Started TeamQueryControllerTest in 0.29 seconds (process running for 26.834)
++2026-03-09T15:08:40.922+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=1 team=team-20
++2026-03-09T15:08:40.924+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Found teamId=team-20 for company=1
++2026-03-09T15:08:40.943+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=1 team=team-404
++2026-03-09T15:08:40.970+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=1 team=team-20
++2026-03-09T15:08:40.971+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 members for team=team-20 company=1
++2026-03-09T15:08:40.989+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : List teams request company=1 includeTrashed=false query=null
++2026-03-09T15:08:40.989+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 teams for company=1 includeTrashed=false query=null
++2026-03-09T15:08:40.992+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
++	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
++	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
++
++2026-03-09T15:08:41.010+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=1 team=team-404
++2026-03-09T15:08:41.022+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TenantGuardFilterTest]: TenantGuardFilterTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
++2026-03-09T15:08:41.029+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TenantGuardFilterTest
++2026-03-09T15:08:41.036+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
++
++  .   ____          _            __ _ _
++ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
++( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
++ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
++  '  |____| .__|_| |_|_| |_\__, | / / / /
++ =========|_|==============|___/=/_/_/_/
++
++ :: Spring Boot ::                (v4.0.2)
++
++2026-03-09T15:08:41.060+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : Starting TenantGuardFilterTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
++2026-03-09T15:08:41.060+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : The following 1 profile is active: "test"
++2026-03-09T15:08:41.395+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
++2026-03-09T15:08:41.395+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
++2026-03-09T15:08:41.397+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
++2026-03-09T15:08:41.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : Started TenantGuardFilterTest in 0.376 seconds (process running for 27.337)
++2026-03-09T15:08:41.478+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=100 includeTrashed=false query=null
++2026-03-09T15:08:41.478+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=100 includeTrashed=false query=null
++2026-03-09T15:08:41.482+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
++	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
++	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
++
++`
+
+`
+
+## Raw build/test output
+`	ext
+15:08:16.100 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [de.innologic.personservice.PersonServiceApplicationTests]: PersonServiceApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+15:08:16.359 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.PersonServiceApplicationTests
+15:08:16.593 [main] INFO org.springframework.boot.devtools.restart.RestartApplicationListener -- Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:17.047+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : Starting PersonServiceApplicationTests using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:17.051+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : The following 1 profile is active: "test"
+2026-03-09T15:08:19.076+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
+2026-03-09T15:08:19.210+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 117 ms. Found 4 JPA repository interfaces.
+2026-03-09T15:08:20.560+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
+2026-03-09T15:08:20.814+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:person_service_test user=SA
+2026-03-09T15:08:20.817+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
+2026-03-09T15:08:20.887+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:person_service_test (H2 2.4)
+2026-03-09T15:08:20.907+01:00  WARN 9048 --- [person-service] [           main] o.f.c.internal.database.base.Database    : Using H2 2.4.240 which is newer than the version Flyway has been verified with. The latest verified version of H2 is 2.3.232.
+2026-03-09T15:08:20.950+01:00  INFO 9048 --- [person-service] [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Schema history table "PUBLIC"."flyway_schema_history_person" does not exist yet
+2026-03-09T15:08:20.952+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.019s)
+2026-03-09T15:08:20.959+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.Flyway                 : All configured schemas are empty; baseline operation skipped. A baseline or migration script with a lower version than the baseline version may execute if available. Check the Schemas parameter if this is not intended.
+2026-03-09T15:08:20.960+01:00  INFO 9048 --- [person-service] [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Creating Schema History table "PUBLIC"."flyway_schema_history_person" ...
+2026-03-09T15:08:21.015+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": << Empty Schema >>
+2026-03-09T15:08:21.029+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "1 - init"
+2026-03-09T15:08:21.068+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "2 - person communication refs"
+2026-03-09T15:08:21.079+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "3 - company id to varchar36"
+2026-03-09T15:08:21.124+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "4 - person public id"
+2026-03-09T15:08:21.136+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Successfully applied 4 migrations to schema "PUBLIC", now at version v4 (execution time 00:00.075s)
+2026-03-09T15:08:21.301+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
+2026-03-09T15:08:21.430+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.2.1.Final
+2026-03-09T15:08:22.053+01:00  INFO 9048 --- [person-service] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
+2026-03-09T15:08:22.132+01:00  WARN 9048 --- [person-service] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
+2026-03-09T15:08:22.152+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
+	Database JDBC URL [jdbc:h2:mem:person_service_test]
+	Database driver: H2 JDBC Driver
+	Database dialect: H2Dialect
+	Database version: 2.4.240
+	Default catalog/schema: person_service_test/PUBLIC
+	Autocommit mode: undefined/unknown
+	Isolation level: READ_COMMITTED [default READ_COMMITTED]
+	JDBC fetch size: 100
+	Pool: DataSourceConnectionProvider
+	Minimum pool size: undefined/unknown
+	Maximum pool size: undefined/unknown
+2026-03-09T15:08:23.588+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
+2026-03-09T15:08:23.596+01:00  INFO 9048 --- [person-service] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
+2026-03-09T15:08:23.795+01:00  INFO 9048 --- [person-service] [           main] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
+2026-03-09T15:08:27.687+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : Started PersonServiceApplicationTests in 11.109 seconds (process running for 13.612)
+2026-03-09T15:08:28.359+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.PersonTeamIntegrationTests]: PersonTeamIntegrationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:28.380+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.PersonTeamIntegrationTests
+2026-03-09T15:08:28.407+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:28.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : Starting PersonTeamIntegrationTests using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:28.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : The following 1 profile is active: "test"
+2026-03-09T15:08:28.929+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
+2026-03-09T15:08:28.954+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 22 ms. Found 4 JPA repository interfaces.
+2026-03-09T15:08:29.168+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Starting...
+2026-03-09T15:08:29.170+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-2 - Added connection conn10: url=jdbc:h2:mem:person_service_test user=SA
+2026-03-09T15:08:29.171+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Start completed.
+2026-03-09T15:08:29.174+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:person_service_test (H2 2.4)
+2026-03-09T15:08:29.179+01:00  WARN 9048 --- [person-service] [           main] o.f.c.internal.database.base.Database    : Using H2 2.4.240 which is newer than the version Flyway has been verified with. The latest verified version of H2 is 2.3.232.
+2026-03-09T15:08:29.188+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.006s)
+2026-03-09T15:08:29.196+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": 4
+2026-03-09T15:08:29.197+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Schema "PUBLIC" is up to date. No migration necessary.
+2026-03-09T15:08:29.229+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
+2026-03-09T15:08:29.287+01:00  INFO 9048 --- [person-service] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
+2026-03-09T15:08:29.289+01:00  WARN 9048 --- [person-service] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
+2026-03-09T15:08:29.290+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
+	Database JDBC URL [jdbc:h2:mem:person_service_test]
+	Database driver: H2 JDBC Driver
+	Database dialect: H2Dialect
+	Database version: 2.4.240
+	Default catalog/schema: person_service_test/PUBLIC
+	Autocommit mode: undefined/unknown
+	Isolation level: READ_COMMITTED [default READ_COMMITTED]
+	JDBC fetch size: 100
+	Pool: DataSourceConnectionProvider
+	Minimum pool size: undefined/unknown
+	Maximum pool size: undefined/unknown
+2026-03-09T15:08:29.403+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
+2026-03-09T15:08:29.404+01:00  INFO 9048 --- [person-service] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
+2026-03-09T15:08:30.078+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:30.079+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:30.082+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms
+2026-03-09T15:08:30.105+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : Started PersonTeamIntegrationTests in 1.699 seconds (process running for 16.03)
+2026-03-09T15:08:30.312+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3001 actor=it-tester
+2026-03-09T15:08:30.323+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3001 actor=it-tester
+2026-03-09T15:08:30.400+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
+2026-03-09T15:08:30.413+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
+2026-03-09T15:08:30.529+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3001 person=30e8ac68-a336-4877-acbb-d139e8df90f0
+2026-03-09T15:08:30.532+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3001 person=30e8ac68-a336-4877-acbb-d139e8df90f0
+2026-03-09T15:08:30.683+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Found personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
+2026-03-09T15:08:30.685+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
+2026-03-09T15:08:30.739+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3005 actor=it-tester
+2026-03-09T15:08:30.740+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3005 actor=it-tester
+2026-03-09T15:08:30.742+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=6b22b2ea-36be-4bd6-8f54-05ef63561784 for company=3005
+2026-03-09T15:08:30.743+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=6b22b2ea-36be-4bd6-8f54-05ef63561784 for company=3005
+2026-03-09T15:08:30.748+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
+2026-03-09T15:08:30.748+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
+2026-03-09T15:08:30.750+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Person not found for company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
+2026-03-09T15:08:30.802+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
+2026-03-09T15:08:30.803+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
+2026-03-09T15:08:30.805+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Person not found for company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
+2026-03-09T15:08:30.821+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3003 actor=it-tester
+2026-03-09T15:08:30.822+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3003 actor=it-tester
+2026-03-09T15:08:30.825+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
+2026-03-09T15:08:30.826+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
+2026-03-09T15:08:30.835+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=3003 person=e6ca8e47-ff29-4c84-928b-7435558bc650 actor=it-tester
+2026-03-09T15:08:30.836+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updating person request company=3003 person=e6ca8e47-ff29-4c84-928b-7435558bc650 actor=it-tester
+2026-03-09T15:08:30.840+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updated personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
+2026-03-09T15:08:30.855+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
+2026-03-09T15:08:30.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3002 actor=it-tester
+2026-03-09T15:08:30.877+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3002 actor=it-tester
+2026-03-09T15:08:30.880+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
+2026-03-09T15:08:30.882+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
+2026-03-09T15:08:30.886+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3002 person=97445f95-1eba-4ca2-93b6-aa54b171a255
+2026-03-09T15:08:30.886+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3002 person=97445f95-1eba-4ca2-93b6-aa54b171a255
+2026-03-09T15:08:30.888+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Found personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
+2026-03-09T15:08:30.889+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
+2026-03-09T15:08:31.512+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Restoring person request company=1 person=10 actor=ignored-header-actor
+2026-03-09T15:08:31.525+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Restored personId=null for company=1
+2026-03-09T15:08:31.553+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updating person request company=1 person=10 actor=ignored-header-actor
+2026-03-09T15:08:31.554+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updated personId=null for company=1
+2026-03-09T15:08:31.565+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=1 actor=ignored-header-actor
+2026-03-09T15:08:31.566+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=null for company=1
+2026-03-09T15:08:31.575+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Trashing person request company=1 person=10 actor=ignored-header-actor
+2026-03-09T15:08:31.577+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Trashed personId=null for company=1
+2026-03-09T15:08:32.168+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
+2026-03-09T15:08:32.177+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Added membershipId=membership-10 team=team-20 person=null
+2026-03-09T15:08:32.216+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
+2026-03-09T15:08:32.228+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
+2026-03-09T15:08:32.229+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Person person-10 already has an active primary membership in company=company-1
+2026-03-09T15:08:32.271+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.JwtScopesConverterSecurityTest]: JwtScopesConverterSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:32.364+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.JwtScopesConverterSecurityTest
+2026-03-09T15:08:32.375+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:32.448+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : Starting JwtScopesConverterSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:32.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : The following 1 profile is active: "test"
+2026-03-09T15:08:33.526+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.526+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.529+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms
+2026-03-09T15:08:33.555+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : Started JwtScopesConverterSecurityTest in 1.18 seconds (process running for 19.48)
+2026-03-09T15:08:33.742+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.743+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.743+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
+2026-03-09T15:08:33.765+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.765+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.766+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
+2026-03-09T15:08:33.786+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.787+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.787+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
+2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
+2026-03-09T15:08:33.838+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.OpsControllerBuildPropertiesTest]: OpsControllerBuildPropertiesTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:33.855+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.OpsControllerBuildPropertiesTest
+2026-03-09T15:08:33.865+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:33.903+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : Starting OpsControllerBuildPropertiesTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:33.904+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : The following 1 profile is active: "test"
+2026-03-09T15:08:34.505+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:34.505+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:34.510+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
+2026-03-09T15:08:34.526+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : Started OpsControllerBuildPropertiesTest in 0.661 seconds (process running for 20.451)
+2026-03-09T15:08:34.605+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.OpsControllerTest]: OpsControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:34.673+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.OpsControllerTest
+2026-03-09T15:08:34.683+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:34.736+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : Starting OpsControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:34.737+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : The following 1 profile is active: "test"
+2026-03-09T15:08:35.107+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:35.108+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:35.110+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
+2026-03-09T15:08:35.120+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : Started OpsControllerTest in 0.438 seconds (process running for 21.045)
+2026-03-09T15:08:35.171+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonCommandControllerTest]: PersonCommandControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:35.186+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonCommandControllerTest
+2026-03-09T15:08:35.193+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:35.225+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : Starting PersonCommandControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:35.225+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : The following 1 profile is active: "test"
+2026-03-09T15:08:35.678+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:35.678+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:35.682+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
+2026-03-09T15:08:35.690+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : Started PersonCommandControllerTest in 0.498 seconds (process running for 21.615)
+2026-03-09T15:08:35.726+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=1 person=404 actor=actor-1
+2026-03-09T15:08:35.813+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=1 person=10 actor=actor-1
+2026-03-09T15:08:35.814+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restored personId=person-10 for company=1
+2026-03-09T15:08:35.845+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=1 person=10 actor=actor-1
+2026-03-09T15:08:35.845+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trashed personId=person-10 for company=1
+2026-03-09T15:08:35.870+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=1 person=404 actor=actor-1
+2026-03-09T15:08:35.890+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=1 actor=actor-1
+2026-03-09T15:08:35.891+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=person-10 for company=1
+2026-03-09T15:08:35.913+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=1 person=10 actor=actor-1
+2026-03-09T15:08:35.913+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=person-10 for company=1
+2026-03-09T15:08:35.928+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=1 person=404 actor=actor-1
+2026-03-09T15:08:35.944+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonCommunicationRefControllerTest]: PersonCommunicationRefControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:35.958+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonCommunicationRefControllerTest
+2026-03-09T15:08:35.964+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:35.994+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : Starting PersonCommunicationRefControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:35.995+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : The following 1 profile is active: "test"
+2026-03-09T15:08:36.351+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:36.351+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:36.353+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
+2026-03-09T15:08:36.365+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : Started PersonCommunicationRefControllerTest in 0.403 seconds (process running for 22.29)
+2026-03-09T15:08:36.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replace communication refs request company=1 person=10 actor=actor-1 desiredCount=2
+2026-03-09T15:08:36.416+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replaced 2 communication refs for person=10 company=1
+2026-03-09T15:08:36.445+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=1 person=404
+2026-03-09T15:08:36.460+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=1 person=10
+2026-03-09T15:08:36.461+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Returning 2 communication refs for person=10 company=1
+2026-03-09T15:08:36.486+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonEndpointsSecurityTest]: PersonEndpointsSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:36.494+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonEndpointsSecurityTest
+2026-03-09T15:08:36.503+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:36.528+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : Starting PersonEndpointsSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:36.528+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : The following 1 profile is active: "test"
+2026-03-09T15:08:37.144+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:37.146+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:37.148+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
+2026-03-09T15:08:37.165+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : Started PersonEndpointsSecurityTest in 0.663 seconds (process running for 23.09)
+2026-03-09T15:08:37.212+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
+2026-03-09T15:08:37.213+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
+2026-03-09T15:08:37.356+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
+2026-03-09T15:08:37.357+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
+2026-03-09T15:08:37.411+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replace communication refs request company=100 person=10 actor=null desiredCount=2
+2026-03-09T15:08:37.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replaced 2 communication refs for person=10 company=100
+2026-03-09T15:08:37.434+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=999
+2026-03-09T15:08:37.475+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=100 person=10
+2026-03-09T15:08:37.475+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Returning 2 communication refs for person=10 company=100
+2026-03-09T15:08:37.600+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=100 actor=null
+2026-03-09T15:08:37.602+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=null for company=100
+2026-03-09T15:08:37.664+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=100 person=10 actor=null
+2026-03-09T15:08:37.665+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trashed personId=null for company=100
+2026-03-09T15:08:37.684+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
+2026-03-09T15:08:37.684+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
+2026-03-09T15:08:37.777+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=100 person=10 actor=null
+2026-03-09T15:08:37.778+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=null for company=100
+2026-03-09T15:08:37.821+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=100 person=10 actor=null
+2026-03-09T15:08:37.822+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restored personId=null for company=100
+2026-03-09T15:08:37.883+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=100 includeTrashed=false query=null
+2026-03-09T15:08:37.884+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=100 includeTrashed=false query=null
+2026-03-09T15:08:37.895+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+2026-03-09T15:08:37.920+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonQueryControllerTest]: PersonQueryControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:37.933+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonQueryControllerTest
+2026-03-09T15:08:37.944+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:37.970+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : Starting PersonQueryControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:37.971+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : The following 1 profile is active: "test"
+2026-03-09T15:08:38.251+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:38.251+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:38.252+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
+2026-03-09T15:08:38.261+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : Started PersonQueryControllerTest in 0.318 seconds (process running for 24.187)
+2026-03-09T15:08:38.278+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=1 person=99
+2026-03-09T15:08:38.296+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=1 person=10
+2026-03-09T15:08:38.296+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=person-10 for company=1
+2026-03-09T15:08:38.318+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=1 includeTrashed=false query=null
+2026-03-09T15:08:38.319+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=1 includeTrashed=false query=null
+2026-03-09T15:08:38.326+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+2026-03-09T15:08:38.374+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.SecurityBaselineTest]: SecurityBaselineTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:38.382+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.SecurityBaselineTest
+2026-03-09T15:08:38.388+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:38.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : Starting SecurityBaselineTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:38.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : The following 1 profile is active: "test"
+2026-03-09T15:08:38.766+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:38.766+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:38.770+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
+2026-03-09T15:08:38.783+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : Started SecurityBaselineTest in 0.394 seconds (process running for 24.708)
+2026-03-09T15:08:38.829+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamCommandControllerTest]: TeamCommandControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:38.844+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamCommandControllerTest
+2026-03-09T15:08:38.848+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:38.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : Starting TeamCommandControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:38.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : The following 1 profile is active: "test"
+2026-03-09T15:08:39.196+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:39.196+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:39.200+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
+2026-03-09T15:08:39.211+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : Started TeamCommandControllerTest in 0.363 seconds (process running for 25.137)
+2026-03-09T15:08:39.230+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=1 team=team-20 person=person-10 actor=actor-1
+2026-03-09T15:08:39.231+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Removed member person=person-10 from team=team-20 for company=1
+2026-03-09T15:08:39.248+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=1 team=team-20 actor=actor-1
+2026-03-09T15:08:39.249+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restored teamId=team-20 for company=1
+2026-03-09T15:08:39.280+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=null actor=actor-1
+2026-03-09T15:08:39.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=1 team=team-404 actor=actor-1
+2026-03-09T15:08:39.315+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=true actor=actor-1
+2026-03-09T15:08:39.331+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=1 name=Core Team actor=actor-1
+2026-03-09T15:08:39.332+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Created teamId=team-20 for company=1
+2026-03-09T15:08:39.348+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=1 team=team-20 actor=actor-1
+2026-03-09T15:08:39.349+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Updated teamId=team-20 for company=1
+2026-03-09T15:08:39.361+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=1 team=team-20 person=person-404 actor=actor-1
+2026-03-09T15:08:39.391+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=1 name=Core Team actor=actor-1
+2026-03-09T15:08:39.404+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=1 team=team-404 actor=actor-1
+2026-03-09T15:08:39.418+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=1 team=team-404 actor=actor-1
+2026-03-09T15:08:39.432+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=null actor=actor-1
+2026-03-09T15:08:39.433+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Added membershipId=membership-50 team=team-20 person=person-10
+2026-03-09T15:08:39.455+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=1 team=team-20 actor=actor-1
+2026-03-09T15:08:39.455+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trashed teamId=team-20 for company=1
+2026-03-09T15:08:39.467+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamEndpointsSecurityTest]: TeamEndpointsSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:39.483+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamEndpointsSecurityTest
+2026-03-09T15:08:39.492+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:39.519+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : Starting TeamEndpointsSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:39.520+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : The following 1 profile is active: "test"
+2026-03-09T15:08:39.967+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:39.968+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:39.973+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 5 ms
+2026-03-09T15:08:39.980+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : Started TeamEndpointsSecurityTest in 0.489 seconds (process running for 25.906)
+2026-03-09T15:08:40.067+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=100 name=Core Team actor=null
+2026-03-09T15:08:40.069+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Created teamId=team-20 for company=100
+2026-03-09T15:08:40.170+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=100 team=team-20 actor=null
+2026-03-09T15:08:40.173+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restored teamId=team-20 for company=100
+2026-03-09T15:08:40.203+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : List teams request company=100 includeTrashed=false query=null
+2026-03-09T15:08:40.205+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 teams for company=100 includeTrashed=false query=null
+2026-03-09T15:08:40.210+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+2026-03-09T15:08:40.246+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=100 team=team-20
+2026-03-09T15:08:40.246+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 members for team=team-20 company=100
+2026-03-09T15:08:40.285+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=100 team=team-20 person=person-10 actor=null
+2026-03-09T15:08:40.286+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Removed member person=person-10 from team=team-20 for company=100
+2026-03-09T15:08:40.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=100 team=team-20 actor=null
+2026-03-09T15:08:40.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Updated teamId=team-20 for company=100
+2026-03-09T15:08:40.327+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=100 team=team-20
+2026-03-09T15:08:40.327+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Found teamId=team-20 for company=100
+2026-03-09T15:08:40.351+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=100 team=team-20 person=person-10 primary=null actor=null
+2026-03-09T15:08:40.352+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Added membershipId=membership-99 team=team-20 person=person-10
+2026-03-09T15:08:40.379+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=100 team=team-999
+2026-03-09T15:08:40.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=100 team=team-20 person=person-10 primary=null actor=null
+2026-03-09T15:08:40.560+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=100 team=team-20 actor=null
+2026-03-09T15:08:40.562+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trashed teamId=team-20 for company=100
+2026-03-09T15:08:40.606+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamQueryControllerTest]: TeamQueryControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:40.613+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamQueryControllerTest
+2026-03-09T15:08:40.620+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:40.657+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : Starting TeamQueryControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:40.657+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : The following 1 profile is active: "test"
+2026-03-09T15:08:40.896+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:40.896+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:40.897+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
+2026-03-09T15:08:40.909+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : Started TeamQueryControllerTest in 0.29 seconds (process running for 26.834)
+2026-03-09T15:08:40.922+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=1 team=team-20
+2026-03-09T15:08:40.924+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Found teamId=team-20 for company=1
+2026-03-09T15:08:40.943+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=1 team=team-404
+2026-03-09T15:08:40.970+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=1 team=team-20
+2026-03-09T15:08:40.971+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 members for team=team-20 company=1
+2026-03-09T15:08:40.989+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : List teams request company=1 includeTrashed=false query=null
+2026-03-09T15:08:40.989+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 teams for company=1 includeTrashed=false query=null
+2026-03-09T15:08:40.992+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+2026-03-09T15:08:41.010+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=1 team=team-404
+2026-03-09T15:08:41.022+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TenantGuardFilterTest]: TenantGuardFilterTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:41.029+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TenantGuardFilterTest
+2026-03-09T15:08:41.036+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:41.060+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : Starting TenantGuardFilterTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:41.060+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : The following 1 profile is active: "test"
+2026-03-09T15:08:41.395+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:41.395+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:41.397+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
+2026-03-09T15:08:41.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : Started TenantGuardFilterTest in 0.376 seconds (process running for 27.337)
+2026-03-09T15:08:41.478+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=100 includeTrashed=false query=null
+2026-03-09T15:08:41.478+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=100 includeTrashed=false query=null
+2026-03-09T15:08:41.482+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+`

+`
+
+## Raw build/test output
+`	ext
+15:08:16.100 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [de.innologic.personservice.PersonServiceApplicationTests]: PersonServiceApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+15:08:16.359 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.PersonServiceApplicationTests
+15:08:16.593 [main] INFO org.springframework.boot.devtools.restart.RestartApplicationListener -- Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:17.047+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : Starting PersonServiceApplicationTests using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:17.051+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : The following 1 profile is active: "test"
+2026-03-09T15:08:19.076+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
+2026-03-09T15:08:19.210+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 117 ms. Found 4 JPA repository interfaces.
+2026-03-09T15:08:20.560+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
+2026-03-09T15:08:20.814+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:person_service_test user=SA
+2026-03-09T15:08:20.817+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
+2026-03-09T15:08:20.887+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:person_service_test (H2 2.4)
+2026-03-09T15:08:20.907+01:00  WARN 9048 --- [person-service] [           main] o.f.c.internal.database.base.Database    : Using H2 2.4.240 which is newer than the version Flyway has been verified with. The latest verified version of H2 is 2.3.232.
+2026-03-09T15:08:20.950+01:00  INFO 9048 --- [person-service] [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Schema history table "PUBLIC"."flyway_schema_history_person" does not exist yet
+2026-03-09T15:08:20.952+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.019s)
+2026-03-09T15:08:20.959+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.Flyway                 : All configured schemas are empty; baseline operation skipped. A baseline or migration script with a lower version than the baseline version may execute if available. Check the Schemas parameter if this is not intended.
+2026-03-09T15:08:20.960+01:00  INFO 9048 --- [person-service] [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Creating Schema History table "PUBLIC"."flyway_schema_history_person" ...
+2026-03-09T15:08:21.015+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": << Empty Schema >>
+2026-03-09T15:08:21.029+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "1 - init"
+2026-03-09T15:08:21.068+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "2 - person communication refs"
+2026-03-09T15:08:21.079+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "3 - company id to varchar36"
+2026-03-09T15:08:21.124+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "4 - person public id"
+2026-03-09T15:08:21.136+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Successfully applied 4 migrations to schema "PUBLIC", now at version v4 (execution time 00:00.075s)
+2026-03-09T15:08:21.301+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
+2026-03-09T15:08:21.430+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.2.1.Final
+2026-03-09T15:08:22.053+01:00  INFO 9048 --- [person-service] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
+2026-03-09T15:08:22.132+01:00  WARN 9048 --- [person-service] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
+2026-03-09T15:08:22.152+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
+	Database JDBC URL [jdbc:h2:mem:person_service_test]
+	Database driver: H2 JDBC Driver
+	Database dialect: H2Dialect
+	Database version: 2.4.240
+	Default catalog/schema: person_service_test/PUBLIC
+	Autocommit mode: undefined/unknown
+	Isolation level: READ_COMMITTED [default READ_COMMITTED]
+	JDBC fetch size: 100
+	Pool: DataSourceConnectionProvider
+	Minimum pool size: undefined/unknown
+	Maximum pool size: undefined/unknown
+2026-03-09T15:08:23.588+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
+2026-03-09T15:08:23.596+01:00  INFO 9048 --- [person-service] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
+2026-03-09T15:08:23.795+01:00  INFO 9048 --- [person-service] [           main] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
+2026-03-09T15:08:27.687+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : Started PersonServiceApplicationTests in 11.109 seconds (process running for 13.612)
+2026-03-09T15:08:28.359+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.PersonTeamIntegrationTests]: PersonTeamIntegrationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:28.380+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.PersonTeamIntegrationTests
+2026-03-09T15:08:28.407+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:28.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : Starting PersonTeamIntegrationTests using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:28.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : The following 1 profile is active: "test"
+2026-03-09T15:08:28.929+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
+2026-03-09T15:08:28.954+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 22 ms. Found 4 JPA repository interfaces.
+2026-03-09T15:08:29.168+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Starting...
+2026-03-09T15:08:29.170+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-2 - Added connection conn10: url=jdbc:h2:mem:person_service_test user=SA
+2026-03-09T15:08:29.171+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Start completed.
+2026-03-09T15:08:29.174+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:person_service_test (H2 2.4)
+2026-03-09T15:08:29.179+01:00  WARN 9048 --- [person-service] [           main] o.f.c.internal.database.base.Database    : Using H2 2.4.240 which is newer than the version Flyway has been verified with. The latest verified version of H2 is 2.3.232.
+2026-03-09T15:08:29.188+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.006s)
+2026-03-09T15:08:29.196+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": 4
+2026-03-09T15:08:29.197+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Schema "PUBLIC" is up to date. No migration necessary.
+2026-03-09T15:08:29.229+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
+2026-03-09T15:08:29.287+01:00  INFO 9048 --- [person-service] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
+2026-03-09T15:08:29.289+01:00  WARN 9048 --- [person-service] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
+2026-03-09T15:08:29.290+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
+	Database JDBC URL [jdbc:h2:mem:person_service_test]
+	Database driver: H2 JDBC Driver
+	Database dialect: H2Dialect
+	Database version: 2.4.240
+	Default catalog/schema: person_service_test/PUBLIC
+	Autocommit mode: undefined/unknown
+	Isolation level: READ_COMMITTED [default READ_COMMITTED]
+	JDBC fetch size: 100
+	Pool: DataSourceConnectionProvider
+	Minimum pool size: undefined/unknown
+	Maximum pool size: undefined/unknown
+2026-03-09T15:08:29.403+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
+2026-03-09T15:08:29.404+01:00  INFO 9048 --- [person-service] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
+2026-03-09T15:08:30.078+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:30.079+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:30.082+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms
+2026-03-09T15:08:30.105+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : Started PersonTeamIntegrationTests in 1.699 seconds (process running for 16.03)
+2026-03-09T15:08:30.312+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3001 actor=it-tester
+2026-03-09T15:08:30.323+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3001 actor=it-tester
+2026-03-09T15:08:30.400+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
+2026-03-09T15:08:30.413+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
+2026-03-09T15:08:30.529+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3001 person=30e8ac68-a336-4877-acbb-d139e8df90f0
+2026-03-09T15:08:30.532+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3001 person=30e8ac68-a336-4877-acbb-d139e8df90f0
+2026-03-09T15:08:30.683+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Found personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
+2026-03-09T15:08:30.685+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
+2026-03-09T15:08:30.739+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3005 actor=it-tester
+2026-03-09T15:08:30.740+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3005 actor=it-tester
+2026-03-09T15:08:30.742+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=6b22b2ea-36be-4bd6-8f54-05ef63561784 for company=3005
+2026-03-09T15:08:30.743+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=6b22b2ea-36be-4bd6-8f54-05ef63561784 for company=3005
+2026-03-09T15:08:30.748+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
+2026-03-09T15:08:30.748+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
+2026-03-09T15:08:30.750+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Person not found for company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
+2026-03-09T15:08:30.802+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
+2026-03-09T15:08:30.803+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
+2026-03-09T15:08:30.805+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Person not found for company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
+2026-03-09T15:08:30.821+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3003 actor=it-tester
+2026-03-09T15:08:30.822+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3003 actor=it-tester
+2026-03-09T15:08:30.825+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
+2026-03-09T15:08:30.826+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
+2026-03-09T15:08:30.835+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=3003 person=e6ca8e47-ff29-4c84-928b-7435558bc650 actor=it-tester
+2026-03-09T15:08:30.836+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updating person request company=3003 person=e6ca8e47-ff29-4c84-928b-7435558bc650 actor=it-tester
+2026-03-09T15:08:30.840+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updated personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
+2026-03-09T15:08:30.855+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
+2026-03-09T15:08:30.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3002 actor=it-tester
+2026-03-09T15:08:30.877+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3002 actor=it-tester
+2026-03-09T15:08:30.880+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
+2026-03-09T15:08:30.882+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
+2026-03-09T15:08:30.886+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3002 person=97445f95-1eba-4ca2-93b6-aa54b171a255
+2026-03-09T15:08:30.886+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3002 person=97445f95-1eba-4ca2-93b6-aa54b171a255
+2026-03-09T15:08:30.888+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Found personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
+2026-03-09T15:08:30.889+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
+2026-03-09T15:08:31.512+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Restoring person request company=1 person=10 actor=ignored-header-actor
+2026-03-09T15:08:31.525+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Restored personId=null for company=1
+2026-03-09T15:08:31.553+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updating person request company=1 person=10 actor=ignored-header-actor
+2026-03-09T15:08:31.554+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updated personId=null for company=1
+2026-03-09T15:08:31.565+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=1 actor=ignored-header-actor
+2026-03-09T15:08:31.566+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=null for company=1
+2026-03-09T15:08:31.575+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Trashing person request company=1 person=10 actor=ignored-header-actor
+2026-03-09T15:08:31.577+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Trashed personId=null for company=1
+2026-03-09T15:08:32.168+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
+2026-03-09T15:08:32.177+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Added membershipId=membership-10 team=team-20 person=null
+2026-03-09T15:08:32.216+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
+2026-03-09T15:08:32.228+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
+2026-03-09T15:08:32.229+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Person person-10 already has an active primary membership in company=company-1
+2026-03-09T15:08:32.271+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.JwtScopesConverterSecurityTest]: JwtScopesConverterSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:32.364+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.JwtScopesConverterSecurityTest
+2026-03-09T15:08:32.375+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:32.448+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : Starting JwtScopesConverterSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:32.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : The following 1 profile is active: "test"
+2026-03-09T15:08:33.526+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.526+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.529+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms
+2026-03-09T15:08:33.555+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : Started JwtScopesConverterSecurityTest in 1.18 seconds (process running for 19.48)
+2026-03-09T15:08:33.742+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.743+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.743+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
+2026-03-09T15:08:33.765+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.765+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.766+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
+2026-03-09T15:08:33.786+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.787+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.787+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
+2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
+2026-03-09T15:08:33.838+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.OpsControllerBuildPropertiesTest]: OpsControllerBuildPropertiesTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:33.855+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.OpsControllerBuildPropertiesTest
+2026-03-09T15:08:33.865+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:33.903+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : Starting OpsControllerBuildPropertiesTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:33.904+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : The following 1 profile is active: "test"
+2026-03-09T15:08:34.505+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:34.505+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:34.510+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
+2026-03-09T15:08:34.526+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : Started OpsControllerBuildPropertiesTest in 0.661 seconds (process running for 20.451)
+2026-03-09T15:08:34.605+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.OpsControllerTest]: OpsControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:34.673+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.OpsControllerTest
+2026-03-09T15:08:34.683+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:34.736+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : Starting OpsControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:34.737+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : The following 1 profile is active: "test"
+2026-03-09T15:08:35.107+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:35.108+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:35.110+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
+2026-03-09T15:08:35.120+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : Started OpsControllerTest in 0.438 seconds (process running for 21.045)
+2026-03-09T15:08:35.171+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonCommandControllerTest]: PersonCommandControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:35.186+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonCommandControllerTest
+2026-03-09T15:08:35.193+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:35.225+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : Starting PersonCommandControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:35.225+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : The following 1 profile is active: "test"
+2026-03-09T15:08:35.678+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:35.678+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:35.682+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
+2026-03-09T15:08:35.690+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : Started PersonCommandControllerTest in 0.498 seconds (process running for 21.615)
+2026-03-09T15:08:35.726+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=1 person=404 actor=actor-1
+2026-03-09T15:08:35.813+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=1 person=10 actor=actor-1
+2026-03-09T15:08:35.814+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restored personId=person-10 for company=1
+2026-03-09T15:08:35.845+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=1 person=10 actor=actor-1
+2026-03-09T15:08:35.845+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trashed personId=person-10 for company=1
+2026-03-09T15:08:35.870+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=1 person=404 actor=actor-1
+2026-03-09T15:08:35.890+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=1 actor=actor-1
+2026-03-09T15:08:35.891+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=person-10 for company=1
+2026-03-09T15:08:35.913+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=1 person=10 actor=actor-1
+2026-03-09T15:08:35.913+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=person-10 for company=1
+2026-03-09T15:08:35.928+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=1 person=404 actor=actor-1
+2026-03-09T15:08:35.944+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonCommunicationRefControllerTest]: PersonCommunicationRefControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:35.958+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonCommunicationRefControllerTest
+2026-03-09T15:08:35.964+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:35.994+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : Starting PersonCommunicationRefControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:35.995+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : The following 1 profile is active: "test"
+2026-03-09T15:08:36.351+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:36.351+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:36.353+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
+2026-03-09T15:08:36.365+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : Started PersonCommunicationRefControllerTest in 0.403 seconds (process running for 22.29)
+2026-03-09T15:08:36.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replace communication refs request company=1 person=10 actor=actor-1 desiredCount=2
+2026-03-09T15:08:36.416+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replaced 2 communication refs for person=10 company=1
+2026-03-09T15:08:36.445+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=1 person=404
+2026-03-09T15:08:36.460+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=1 person=10
+2026-03-09T15:08:36.461+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Returning 2 communication refs for person=10 company=1
+2026-03-09T15:08:36.486+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonEndpointsSecurityTest]: PersonEndpointsSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:36.494+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonEndpointsSecurityTest
+2026-03-09T15:08:36.503+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:36.528+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : Starting PersonEndpointsSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:36.528+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : The following 1 profile is active: "test"
+2026-03-09T15:08:37.144+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:37.146+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:37.148+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
+2026-03-09T15:08:37.165+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : Started PersonEndpointsSecurityTest in 0.663 seconds (process running for 23.09)
+2026-03-09T15:08:37.212+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
+2026-03-09T15:08:37.213+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
+2026-03-09T15:08:37.356+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
+2026-03-09T15:08:37.357+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
+2026-03-09T15:08:37.411+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replace communication refs request company=100 person=10 actor=null desiredCount=2
+2026-03-09T15:08:37.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replaced 2 communication refs for person=10 company=100
+2026-03-09T15:08:37.434+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=999
+2026-03-09T15:08:37.475+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=100 person=10
+2026-03-09T15:08:37.475+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Returning 2 communication refs for person=10 company=100
+2026-03-09T15:08:37.600+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=100 actor=null
+2026-03-09T15:08:37.602+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=null for company=100
+2026-03-09T15:08:37.664+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=100 person=10 actor=null
+2026-03-09T15:08:37.665+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trashed personId=null for company=100
+2026-03-09T15:08:37.684+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
+2026-03-09T15:08:37.684+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
+2026-03-09T15:08:37.777+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=100 person=10 actor=null
+2026-03-09T15:08:37.778+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=null for company=100
+2026-03-09T15:08:37.821+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=100 person=10 actor=null
+2026-03-09T15:08:37.822+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restored personId=null for company=100
+2026-03-09T15:08:37.883+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=100 includeTrashed=false query=null
+2026-03-09T15:08:37.884+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=100 includeTrashed=false query=null
+2026-03-09T15:08:37.895+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+2026-03-09T15:08:37.920+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonQueryControllerTest]: PersonQueryControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:37.933+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonQueryControllerTest
+2026-03-09T15:08:37.944+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:37.970+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : Starting PersonQueryControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:37.971+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : The following 1 profile is active: "test"
+2026-03-09T15:08:38.251+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:38.251+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:38.252+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
+2026-03-09T15:08:38.261+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : Started PersonQueryControllerTest in 0.318 seconds (process running for 24.187)
+2026-03-09T15:08:38.278+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=1 person=99
+2026-03-09T15:08:38.296+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=1 person=10
+2026-03-09T15:08:38.296+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=person-10 for company=1
+2026-03-09T15:08:38.318+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=1 includeTrashed=false query=null
+2026-03-09T15:08:38.319+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=1 includeTrashed=false query=null
+2026-03-09T15:08:38.326+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+2026-03-09T15:08:38.374+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.SecurityBaselineTest]: SecurityBaselineTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:38.382+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.SecurityBaselineTest
+2026-03-09T15:08:38.388+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:38.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : Starting SecurityBaselineTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:38.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : The following 1 profile is active: "test"
+2026-03-09T15:08:38.766+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:38.766+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:38.770+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
+2026-03-09T15:08:38.783+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : Started SecurityBaselineTest in 0.394 seconds (process running for 24.708)
+2026-03-09T15:08:38.829+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamCommandControllerTest]: TeamCommandControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:38.844+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamCommandControllerTest
+2026-03-09T15:08:38.848+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:38.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : Starting TeamCommandControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:38.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : The following 1 profile is active: "test"
+2026-03-09T15:08:39.196+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:39.196+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:39.200+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
+2026-03-09T15:08:39.211+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : Started TeamCommandControllerTest in 0.363 seconds (process running for 25.137)
+2026-03-09T15:08:39.230+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=1 team=team-20 person=person-10 actor=actor-1
+2026-03-09T15:08:39.231+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Removed member person=person-10 from team=team-20 for company=1
+2026-03-09T15:08:39.248+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=1 team=team-20 actor=actor-1
+2026-03-09T15:08:39.249+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restored teamId=team-20 for company=1
+2026-03-09T15:08:39.280+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=null actor=actor-1
+2026-03-09T15:08:39.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=1 team=team-404 actor=actor-1
+2026-03-09T15:08:39.315+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=true actor=actor-1
+2026-03-09T15:08:39.331+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=1 name=Core Team actor=actor-1
+2026-03-09T15:08:39.332+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Created teamId=team-20 for company=1
+2026-03-09T15:08:39.348+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=1 team=team-20 actor=actor-1
+2026-03-09T15:08:39.349+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Updated teamId=team-20 for company=1
+2026-03-09T15:08:39.361+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=1 team=team-20 person=person-404 actor=actor-1
+2026-03-09T15:08:39.391+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=1 name=Core Team actor=actor-1
+2026-03-09T15:08:39.404+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=1 team=team-404 actor=actor-1
+2026-03-09T15:08:39.418+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=1 team=team-404 actor=actor-1
+2026-03-09T15:08:39.432+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=null actor=actor-1
+2026-03-09T15:08:39.433+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Added membershipId=membership-50 team=team-20 person=person-10
+2026-03-09T15:08:39.455+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=1 team=team-20 actor=actor-1
+2026-03-09T15:08:39.455+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trashed teamId=team-20 for company=1
+2026-03-09T15:08:39.467+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamEndpointsSecurityTest]: TeamEndpointsSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:39.483+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamEndpointsSecurityTest
+2026-03-09T15:08:39.492+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:39.519+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : Starting TeamEndpointsSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:39.520+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : The following 1 profile is active: "test"
+2026-03-09T15:08:39.967+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:39.968+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:39.973+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 5 ms
+2026-03-09T15:08:39.980+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : Started TeamEndpointsSecurityTest in 0.489 seconds (process running for 25.906)
+2026-03-09T15:08:40.067+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=100 name=Core Team actor=null
+2026-03-09T15:08:40.069+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Created teamId=team-20 for company=100
+2026-03-09T15:08:40.170+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=100 team=team-20 actor=null
+2026-03-09T15:08:40.173+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restored teamId=team-20 for company=100
+2026-03-09T15:08:40.203+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : List teams request company=100 includeTrashed=false query=null
+2026-03-09T15:08:40.205+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 teams for company=100 includeTrashed=false query=null
+2026-03-09T15:08:40.210+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+2026-03-09T15:08:40.246+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=100 team=team-20
+2026-03-09T15:08:40.246+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 members for team=team-20 company=100
+2026-03-09T15:08:40.285+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=100 team=team-20 person=person-10 actor=null
+2026-03-09T15:08:40.286+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Removed member person=person-10 from team=team-20 for company=100
+2026-03-09T15:08:40.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=100 team=team-20 actor=null
+2026-03-09T15:08:40.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Updated teamId=team-20 for company=100
+2026-03-09T15:08:40.327+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=100 team=team-20
+2026-03-09T15:08:40.327+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Found teamId=team-20 for company=100
+2026-03-09T15:08:40.351+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=100 team=team-20 person=person-10 primary=null actor=null
+2026-03-09T15:08:40.352+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Added membershipId=membership-99 team=team-20 person=person-10
+2026-03-09T15:08:40.379+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=100 team=team-999
+2026-03-09T15:08:40.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=100 team=team-20 person=person-10 primary=null actor=null
+2026-03-09T15:08:40.560+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=100 team=team-20 actor=null
+2026-03-09T15:08:40.562+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trashed teamId=team-20 for company=100
+2026-03-09T15:08:40.606+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamQueryControllerTest]: TeamQueryControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:40.613+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamQueryControllerTest
+2026-03-09T15:08:40.620+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:40.657+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : Starting TeamQueryControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:40.657+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : The following 1 profile is active: "test"
+2026-03-09T15:08:40.896+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:40.896+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:40.897+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
+2026-03-09T15:08:40.909+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : Started TeamQueryControllerTest in 0.29 seconds (process running for 26.834)
+2026-03-09T15:08:40.922+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=1 team=team-20
+2026-03-09T15:08:40.924+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Found teamId=team-20 for company=1
+2026-03-09T15:08:40.943+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=1 team=team-404
+2026-03-09T15:08:40.970+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=1 team=team-20
+2026-03-09T15:08:40.971+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 members for team=team-20 company=1
+2026-03-09T15:08:40.989+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : List teams request company=1 includeTrashed=false query=null
+2026-03-09T15:08:40.989+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 teams for company=1 includeTrashed=false query=null
+2026-03-09T15:08:40.992+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+2026-03-09T15:08:41.010+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=1 team=team-404
+2026-03-09T15:08:41.022+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TenantGuardFilterTest]: TenantGuardFilterTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
+2026-03-09T15:08:41.029+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TenantGuardFilterTest
+2026-03-09T15:08:41.036+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running
+
+  .   ____          _            __ _ _
+ /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
+( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
+ \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
+  '  |____| .__|_| |_|_| |_\__, | / / / /
+ =========|_|==============|___/=/_/_/_/
+
+ :: Spring Boot ::                (v4.0.2)
+
+2026-03-09T15:08:41.060+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : Starting TenantGuardFilterTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
+2026-03-09T15:08:41.060+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : The following 1 profile is active: "test"
+2026-03-09T15:08:41.395+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
+2026-03-09T15:08:41.395+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
+2026-03-09T15:08:41.397+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
+2026-03-09T15:08:41.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : Started TenantGuardFilterTest in 0.376 seconds (process running for 27.337)
+2026-03-09T15:08:41.478+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=100 includeTrashed=false query=null
+2026-03-09T15:08:41.478+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=100 includeTrashed=false query=null
+2026-03-09T15:08:41.482+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
+	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
+	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
+
+`

`

## Raw build/test output
`	ext
15:08:16.100 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [de.innologic.personservice.PersonServiceApplicationTests]: PersonServiceApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
15:08:16.359 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.PersonServiceApplicationTests
15:08:16.593 [main] INFO org.springframework.boot.devtools.restart.RestartApplicationListener -- Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:17.047+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : Starting PersonServiceApplicationTests using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:17.051+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : The following 1 profile is active: "test"
2026-03-09T15:08:19.076+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2026-03-09T15:08:19.210+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 117 ms. Found 4 JPA repository interfaces.
2026-03-09T15:08:20.560+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2026-03-09T15:08:20.814+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:person_service_test user=SA
2026-03-09T15:08:20.817+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2026-03-09T15:08:20.887+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:person_service_test (H2 2.4)
2026-03-09T15:08:20.907+01:00  WARN 9048 --- [person-service] [           main] o.f.c.internal.database.base.Database    : Using H2 2.4.240 which is newer than the version Flyway has been verified with. The latest verified version of H2 is 2.3.232.
2026-03-09T15:08:20.950+01:00  INFO 9048 --- [person-service] [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Schema history table "PUBLIC"."flyway_schema_history_person" does not exist yet
2026-03-09T15:08:20.952+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.019s)
2026-03-09T15:08:20.959+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.Flyway                 : All configured schemas are empty; baseline operation skipped. A baseline or migration script with a lower version than the baseline version may execute if available. Check the Schemas parameter if this is not intended.
2026-03-09T15:08:20.960+01:00  INFO 9048 --- [person-service] [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Creating Schema History table "PUBLIC"."flyway_schema_history_person" ...
2026-03-09T15:08:21.015+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": << Empty Schema >>
2026-03-09T15:08:21.029+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "1 - init"
2026-03-09T15:08:21.068+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "2 - person communication refs"
2026-03-09T15:08:21.079+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "3 - company id to varchar36"
2026-03-09T15:08:21.124+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "4 - person public id"
2026-03-09T15:08:21.136+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Successfully applied 4 migrations to schema "PUBLIC", now at version v4 (execution time 00:00.075s)
2026-03-09T15:08:21.301+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
2026-03-09T15:08:21.430+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.2.1.Final
2026-03-09T15:08:22.053+01:00  INFO 9048 --- [person-service] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2026-03-09T15:08:22.132+01:00  WARN 9048 --- [person-service] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2026-03-09T15:08:22.152+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
	Database JDBC URL [jdbc:h2:mem:person_service_test]
	Database driver: H2 JDBC Driver
	Database dialect: H2Dialect
	Database version: 2.4.240
	Default catalog/schema: person_service_test/PUBLIC
	Autocommit mode: undefined/unknown
	Isolation level: READ_COMMITTED [default READ_COMMITTED]
	JDBC fetch size: 100
	Pool: DataSourceConnectionProvider
	Minimum pool size: undefined/unknown
	Maximum pool size: undefined/unknown
2026-03-09T15:08:23.588+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-03-09T15:08:23.596+01:00  INFO 9048 --- [person-service] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-03-09T15:08:23.795+01:00  INFO 9048 --- [person-service] [           main] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
2026-03-09T15:08:27.687+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonServiceApplicationTests      : Started PersonServiceApplicationTests in 11.109 seconds (process running for 13.612)
2026-03-09T15:08:28.359+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.PersonTeamIntegrationTests]: PersonTeamIntegrationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:28.380+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.PersonTeamIntegrationTests
2026-03-09T15:08:28.407+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:28.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : Starting PersonTeamIntegrationTests using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:28.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : The following 1 profile is active: "test"
2026-03-09T15:08:28.929+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2026-03-09T15:08:28.954+01:00  INFO 9048 --- [person-service] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 22 ms. Found 4 JPA repository interfaces.
2026-03-09T15:08:29.168+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Starting...
2026-03-09T15:08:29.170+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-2 - Added connection conn10: url=jdbc:h2:mem:person_service_test user=SA
2026-03-09T15:08:29.171+01:00  INFO 9048 --- [person-service] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Start completed.
2026-03-09T15:08:29.174+01:00  INFO 9048 --- [person-service] [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:person_service_test (H2 2.4)
2026-03-09T15:08:29.179+01:00  WARN 9048 --- [person-service] [           main] o.f.c.internal.database.base.Database    : Using H2 2.4.240 which is newer than the version Flyway has been verified with. The latest verified version of H2 is 2.3.232.
2026-03-09T15:08:29.188+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbValidate     : Successfully validated 4 migrations (execution time 00:00.006s)
2026-03-09T15:08:29.196+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": 4
2026-03-09T15:08:29.197+01:00  INFO 9048 --- [person-service] [           main] o.f.core.internal.command.DbMigrate      : Schema "PUBLIC" is up to date. No migration necessary.
2026-03-09T15:08:29.229+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
2026-03-09T15:08:29.287+01:00  INFO 9048 --- [person-service] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2026-03-09T15:08:29.289+01:00  WARN 9048 --- [person-service] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2026-03-09T15:08:29.290+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
	Database JDBC URL [jdbc:h2:mem:person_service_test]
	Database driver: H2 JDBC Driver
	Database dialect: H2Dialect
	Database version: 2.4.240
	Default catalog/schema: person_service_test/PUBLIC
	Autocommit mode: undefined/unknown
	Isolation level: READ_COMMITTED [default READ_COMMITTED]
	JDBC fetch size: 100
	Pool: DataSourceConnectionProvider
	Minimum pool size: undefined/unknown
	Maximum pool size: undefined/unknown
2026-03-09T15:08:29.403+01:00  INFO 9048 --- [person-service] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-03-09T15:08:29.404+01:00  INFO 9048 --- [person-service] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-03-09T15:08:30.078+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:30.079+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:30.082+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms
2026-03-09T15:08:30.105+01:00  INFO 9048 --- [person-service] [           main] d.i.p.PersonTeamIntegrationTests         : Started PersonTeamIntegrationTests in 1.699 seconds (process running for 16.03)
2026-03-09T15:08:30.312+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3001 actor=it-tester
2026-03-09T15:08:30.323+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3001 actor=it-tester
2026-03-09T15:08:30.400+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
2026-03-09T15:08:30.413+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
2026-03-09T15:08:30.529+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3001 person=30e8ac68-a336-4877-acbb-d139e8df90f0
2026-03-09T15:08:30.532+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3001 person=30e8ac68-a336-4877-acbb-d139e8df90f0
2026-03-09T15:08:30.683+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Found personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
2026-03-09T15:08:30.685+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=30e8ac68-a336-4877-acbb-d139e8df90f0 for company=3001
2026-03-09T15:08:30.739+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3005 actor=it-tester
2026-03-09T15:08:30.740+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3005 actor=it-tester
2026-03-09T15:08:30.742+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=6b22b2ea-36be-4bd6-8f54-05ef63561784 for company=3005
2026-03-09T15:08:30.743+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=6b22b2ea-36be-4bd6-8f54-05ef63561784 for company=3005
2026-03-09T15:08:30.748+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
2026-03-09T15:08:30.748+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
2026-03-09T15:08:30.750+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Person not found for company=3999 person=6b22b2ea-36be-4bd6-8f54-05ef63561784
2026-03-09T15:08:30.802+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
2026-03-09T15:08:30.803+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
2026-03-09T15:08:30.805+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Person not found for company=3004 person=e322c6c4-ef5c-4236-aac1-fbee41d15643
2026-03-09T15:08:30.821+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3003 actor=it-tester
2026-03-09T15:08:30.822+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3003 actor=it-tester
2026-03-09T15:08:30.825+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
2026-03-09T15:08:30.826+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
2026-03-09T15:08:30.835+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=3003 person=e6ca8e47-ff29-4c84-928b-7435558bc650 actor=it-tester
2026-03-09T15:08:30.836+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updating person request company=3003 person=e6ca8e47-ff29-4c84-928b-7435558bc650 actor=it-tester
2026-03-09T15:08:30.840+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updated personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
2026-03-09T15:08:30.855+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=e6ca8e47-ff29-4c84-928b-7435558bc650 for company=3003
2026-03-09T15:08:30.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=3002 actor=it-tester
2026-03-09T15:08:30.877+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=3002 actor=it-tester
2026-03-09T15:08:30.880+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
2026-03-09T15:08:30.882+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
2026-03-09T15:08:30.886+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=3002 person=97445f95-1eba-4ca2-93b6-aa54b171a255
2026-03-09T15:08:30.886+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Fetching person company=3002 person=97445f95-1eba-4ca2-93b6-aa54b171a255
2026-03-09T15:08:30.888+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.person.PersonQueryService  : Found personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
2026-03-09T15:08:30.889+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=97445f95-1eba-4ca2-93b6-aa54b171a255 for company=3002
2026-03-09T15:08:31.512+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Restoring person request company=1 person=10 actor=ignored-header-actor
2026-03-09T15:08:31.525+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Restored personId=null for company=1
2026-03-09T15:08:31.553+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updating person request company=1 person=10 actor=ignored-header-actor
2026-03-09T15:08:31.554+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Updated personId=null for company=1
2026-03-09T15:08:31.565+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Creating person request company=1 actor=ignored-header-actor
2026-03-09T15:08:31.566+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Created personId=null for company=1
2026-03-09T15:08:31.575+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Trashing person request company=1 person=10 actor=ignored-header-actor
2026-03-09T15:08:31.577+01:00  INFO 9048 --- [person-service] [           main] d.i.p.s.person.PersonCommandService      : Trashed personId=null for company=1
2026-03-09T15:08:32.168+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
2026-03-09T15:08:32.177+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Added membershipId=membership-10 team=team-20 person=null
2026-03-09T15:08:32.216+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
2026-03-09T15:08:32.228+01:00  INFO 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Adding team member request company=company-1 team=team-20 person=person-10 actor=null
2026-03-09T15:08:32.229+01:00  WARN 9048 --- [person-service] [           main] d.i.p.service.team.TeamCommandService    : Person person-10 already has an active primary membership in company=company-1
2026-03-09T15:08:32.271+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.JwtScopesConverterSecurityTest]: JwtScopesConverterSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:32.364+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.JwtScopesConverterSecurityTest
2026-03-09T15:08:32.375+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:32.448+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : Starting JwtScopesConverterSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:32.449+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : The following 1 profile is active: "test"
2026-03-09T15:08:33.526+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:33.526+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:33.529+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms
2026-03-09T15:08:33.555+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.JwtScopesConverterSecurityTest   : Started JwtScopesConverterSecurityTest in 1.18 seconds (process running for 19.48)
2026-03-09T15:08:33.742+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:33.743+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:33.743+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
2026-03-09T15:08:33.765+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:33.765+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:33.766+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
2026-03-09T15:08:33.786+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:33.787+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:33.787+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.mock.web.MockServletContext          : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:33.806+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
2026-03-09T15:08:33.838+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.OpsControllerBuildPropertiesTest]: OpsControllerBuildPropertiesTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:33.855+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.OpsControllerBuildPropertiesTest
2026-03-09T15:08:33.865+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:33.903+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : Starting OpsControllerBuildPropertiesTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:33.904+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : The following 1 profile is active: "test"
2026-03-09T15:08:34.505+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:34.505+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:34.510+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
2026-03-09T15:08:34.526+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.OpsControllerBuildPropertiesTest : Started OpsControllerBuildPropertiesTest in 0.661 seconds (process running for 20.451)
2026-03-09T15:08:34.605+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.OpsControllerTest]: OpsControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:34.673+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.OpsControllerTest
2026-03-09T15:08:34.683+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:34.736+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : Starting OpsControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:34.737+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : The following 1 profile is active: "test"
2026-03-09T15:08:35.107+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:35.108+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:35.110+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
2026-03-09T15:08:35.120+01:00  INFO 9048 --- [person-service] [           main] d.i.personservice.web.OpsControllerTest  : Started OpsControllerTest in 0.438 seconds (process running for 21.045)
2026-03-09T15:08:35.171+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonCommandControllerTest]: PersonCommandControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:35.186+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonCommandControllerTest
2026-03-09T15:08:35.193+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:35.225+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : Starting PersonCommandControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:35.225+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : The following 1 profile is active: "test"
2026-03-09T15:08:35.678+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:35.678+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:35.682+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
2026-03-09T15:08:35.690+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandControllerTest    : Started PersonCommandControllerTest in 0.498 seconds (process running for 21.615)
2026-03-09T15:08:35.726+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=1 person=404 actor=actor-1
2026-03-09T15:08:35.813+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=1 person=10 actor=actor-1
2026-03-09T15:08:35.814+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restored personId=person-10 for company=1
2026-03-09T15:08:35.845+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=1 person=10 actor=actor-1
2026-03-09T15:08:35.845+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trashed personId=person-10 for company=1
2026-03-09T15:08:35.870+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=1 person=404 actor=actor-1
2026-03-09T15:08:35.890+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=1 actor=actor-1
2026-03-09T15:08:35.891+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=person-10 for company=1
2026-03-09T15:08:35.913+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=1 person=10 actor=actor-1
2026-03-09T15:08:35.913+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=person-10 for company=1
2026-03-09T15:08:35.928+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=1 person=404 actor=actor-1
2026-03-09T15:08:35.944+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonCommunicationRefControllerTest]: PersonCommunicationRefControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:35.958+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonCommunicationRefControllerTest
2026-03-09T15:08:35.964+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:35.994+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : Starting PersonCommunicationRefControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:35.995+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : The following 1 profile is active: "test"
2026-03-09T15:08:36.351+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:36.351+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:36.353+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2026-03-09T15:08:36.365+01:00  INFO 9048 --- [person-service] [           main] p.w.PersonCommunicationRefControllerTest : Started PersonCommunicationRefControllerTest in 0.403 seconds (process running for 22.29)
2026-03-09T15:08:36.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replace communication refs request company=1 person=10 actor=actor-1 desiredCount=2
2026-03-09T15:08:36.416+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replaced 2 communication refs for person=10 company=1
2026-03-09T15:08:36.445+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=1 person=404
2026-03-09T15:08:36.460+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=1 person=10
2026-03-09T15:08:36.461+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Returning 2 communication refs for person=10 company=1
2026-03-09T15:08:36.486+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonEndpointsSecurityTest]: PersonEndpointsSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:36.494+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonEndpointsSecurityTest
2026-03-09T15:08:36.503+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:36.528+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : Starting PersonEndpointsSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:36.528+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : The following 1 profile is active: "test"
2026-03-09T15:08:37.144+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:37.146+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:37.148+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
2026-03-09T15:08:37.165+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonEndpointsSecurityTest    : Started PersonEndpointsSecurityTest in 0.663 seconds (process running for 23.09)
2026-03-09T15:08:37.212+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
2026-03-09T15:08:37.213+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
2026-03-09T15:08:37.356+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
2026-03-09T15:08:37.357+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
2026-03-09T15:08:37.411+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replace communication refs request company=100 person=10 actor=null desiredCount=2
2026-03-09T15:08:37.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Replaced 2 communication refs for person=10 company=100
2026-03-09T15:08:37.434+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=999
2026-03-09T15:08:37.475+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Get communication refs request company=100 person=10
2026-03-09T15:08:37.475+01:00  INFO 9048 --- [person-service] [           main] d.i.p.w.PersonCommunicationRefController : Returning 2 communication refs for person=10 company=100
2026-03-09T15:08:37.600+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Create person request company=100 actor=null
2026-03-09T15:08:37.602+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Created personId=null for company=100
2026-03-09T15:08:37.664+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trash person request company=100 person=10 actor=null
2026-03-09T15:08:37.665+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Trashed personId=null for company=100
2026-03-09T15:08:37.684+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=100 person=10
2026-03-09T15:08:37.684+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=null for company=100
2026-03-09T15:08:37.777+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Update person request company=100 person=10 actor=null
2026-03-09T15:08:37.778+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Updated personId=null for company=100
2026-03-09T15:08:37.821+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restore person request company=100 person=10 actor=null
2026-03-09T15:08:37.822+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonCommandController        : Restored personId=null for company=100
2026-03-09T15:08:37.883+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=100 includeTrashed=false query=null
2026-03-09T15:08:37.884+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=100 includeTrashed=false query=null
2026-03-09T15:08:37.895+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.

2026-03-09T15:08:37.920+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.PersonQueryControllerTest]: PersonQueryControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:37.933+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.PersonQueryControllerTest
2026-03-09T15:08:37.944+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:37.970+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : Starting PersonQueryControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:37.971+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : The following 1 profile is active: "test"
2026-03-09T15:08:38.251+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:38.251+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:38.252+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2026-03-09T15:08:38.261+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryControllerTest      : Started PersonQueryControllerTest in 0.318 seconds (process running for 24.187)
2026-03-09T15:08:38.278+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=1 person=99
2026-03-09T15:08:38.296+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Get person request company=1 person=10
2026-03-09T15:08:38.296+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Found personId=person-10 for company=1
2026-03-09T15:08:38.318+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=1 includeTrashed=false query=null
2026-03-09T15:08:38.319+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=1 includeTrashed=false query=null
2026-03-09T15:08:38.326+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.

2026-03-09T15:08:38.374+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.SecurityBaselineTest]: SecurityBaselineTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:38.382+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.SecurityBaselineTest
2026-03-09T15:08:38.388+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:38.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : Starting SecurityBaselineTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:38.415+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : The following 1 profile is active: "test"
2026-03-09T15:08:38.766+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:38.766+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:38.770+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
2026-03-09T15:08:38.783+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.SecurityBaselineTest           : Started SecurityBaselineTest in 0.394 seconds (process running for 24.708)
2026-03-09T15:08:38.829+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamCommandControllerTest]: TeamCommandControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:38.844+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamCommandControllerTest
2026-03-09T15:08:38.848+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:38.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : Starting TeamCommandControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:38.876+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : The following 1 profile is active: "test"
2026-03-09T15:08:39.196+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:39.196+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:39.200+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 4 ms
2026-03-09T15:08:39.211+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandControllerTest      : Started TeamCommandControllerTest in 0.363 seconds (process running for 25.137)
2026-03-09T15:08:39.230+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=1 team=team-20 person=person-10 actor=actor-1
2026-03-09T15:08:39.231+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Removed member person=person-10 from team=team-20 for company=1
2026-03-09T15:08:39.248+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=1 team=team-20 actor=actor-1
2026-03-09T15:08:39.249+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restored teamId=team-20 for company=1
2026-03-09T15:08:39.280+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=null actor=actor-1
2026-03-09T15:08:39.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=1 team=team-404 actor=actor-1
2026-03-09T15:08:39.315+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=true actor=actor-1
2026-03-09T15:08:39.331+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=1 name=Core Team actor=actor-1
2026-03-09T15:08:39.332+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Created teamId=team-20 for company=1
2026-03-09T15:08:39.348+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=1 team=team-20 actor=actor-1
2026-03-09T15:08:39.349+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Updated teamId=team-20 for company=1
2026-03-09T15:08:39.361+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=1 team=team-20 person=person-404 actor=actor-1
2026-03-09T15:08:39.391+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=1 name=Core Team actor=actor-1
2026-03-09T15:08:39.404+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=1 team=team-404 actor=actor-1
2026-03-09T15:08:39.418+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=1 team=team-404 actor=actor-1
2026-03-09T15:08:39.432+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=1 team=team-20 person=person-10 primary=null actor=actor-1
2026-03-09T15:08:39.433+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Added membershipId=membership-50 team=team-20 person=person-10
2026-03-09T15:08:39.455+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=1 team=team-20 actor=actor-1
2026-03-09T15:08:39.455+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trashed teamId=team-20 for company=1
2026-03-09T15:08:39.467+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamEndpointsSecurityTest]: TeamEndpointsSecurityTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:39.483+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamEndpointsSecurityTest
2026-03-09T15:08:39.492+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:39.519+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : Starting TeamEndpointsSecurityTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:39.520+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : The following 1 profile is active: "test"
2026-03-09T15:08:39.967+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:39.968+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:39.973+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 5 ms
2026-03-09T15:08:39.980+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamEndpointsSecurityTest      : Started TeamEndpointsSecurityTest in 0.489 seconds (process running for 25.906)
2026-03-09T15:08:40.067+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Create team request company=100 name=Core Team actor=null
2026-03-09T15:08:40.069+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Created teamId=team-20 for company=100
2026-03-09T15:08:40.170+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restore team request company=100 team=team-20 actor=null
2026-03-09T15:08:40.173+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Restored teamId=team-20 for company=100
2026-03-09T15:08:40.203+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : List teams request company=100 includeTrashed=false query=null
2026-03-09T15:08:40.205+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 teams for company=100 includeTrashed=false query=null
2026-03-09T15:08:40.210+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.

2026-03-09T15:08:40.246+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=100 team=team-20
2026-03-09T15:08:40.246+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 members for team=team-20 company=100
2026-03-09T15:08:40.285+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Remove team member request company=100 team=team-20 person=person-10 actor=null
2026-03-09T15:08:40.286+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Removed member person=person-10 from team=team-20 for company=100
2026-03-09T15:08:40.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Update team request company=100 team=team-20 actor=null
2026-03-09T15:08:40.300+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Updated teamId=team-20 for company=100
2026-03-09T15:08:40.327+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=100 team=team-20
2026-03-09T15:08:40.327+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Found teamId=team-20 for company=100
2026-03-09T15:08:40.351+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=100 team=team-20 person=person-10 primary=null actor=null
2026-03-09T15:08:40.352+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Added membershipId=membership-99 team=team-20 person=person-10
2026-03-09T15:08:40.379+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=100 team=team-999
2026-03-09T15:08:40.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Add team member request company=100 team=team-20 person=person-10 primary=null actor=null
2026-03-09T15:08:40.560+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trash team request company=100 team=team-20 actor=null
2026-03-09T15:08:40.562+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamCommandController          : Trashed teamId=team-20 for company=100
2026-03-09T15:08:40.606+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TeamQueryControllerTest]: TeamQueryControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:40.613+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TeamQueryControllerTest
2026-03-09T15:08:40.620+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:40.657+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : Starting TeamQueryControllerTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:40.657+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : The following 1 profile is active: "test"
2026-03-09T15:08:40.896+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:40.896+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:40.897+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2026-03-09T15:08:40.909+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryControllerTest        : Started TeamQueryControllerTest in 0.29 seconds (process running for 26.834)
2026-03-09T15:08:40.922+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=1 team=team-20
2026-03-09T15:08:40.924+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Found teamId=team-20 for company=1
2026-03-09T15:08:40.943+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team request company=1 team=team-404
2026-03-09T15:08:40.970+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=1 team=team-20
2026-03-09T15:08:40.971+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 members for team=team-20 company=1
2026-03-09T15:08:40.989+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : List teams request company=1 includeTrashed=false query=null
2026-03-09T15:08:40.989+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Returning 1 teams for company=1 includeTrashed=false query=null
2026-03-09T15:08:40.992+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.

2026-03-09T15:08:41.010+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TeamQueryController            : Get team members request company=1 team=team-404
2026-03-09T15:08:41.022+01:00  INFO 9048 --- [person-service] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [de.innologic.personservice.web.TenantGuardFilterTest]: TenantGuardFilterTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2026-03-09T15:08:41.029+01:00  INFO 9048 --- [person-service] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration de.innologic.personservice.PersonServiceApplication for test class de.innologic.personservice.web.TenantGuardFilterTest
2026-03-09T15:08:41.036+01:00  INFO 9048 --- [person-service] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

2026-03-09T15:08:41.060+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : Starting TenantGuardFilterTest using Java 21.0.9 with PID 9048 (started by User in E:\DevProjects\Flowtrack Projekt\src\pure\person-service)
2026-03-09T15:08:41.060+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : The following 1 profile is active: "test"
2026-03-09T15:08:41.395+01:00  INFO 9048 --- [person-service] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-03-09T15:08:41.395+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-03-09T15:08:41.397+01:00  INFO 9048 --- [person-service] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
2026-03-09T15:08:41.412+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.TenantGuardFilterTest          : Started TenantGuardFilterTest in 0.376 seconds (process running for 27.337)
2026-03-09T15:08:41.478+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : List persons request company=100 includeTrashed=false query=null
2026-03-09T15:08:41.478+01:00  INFO 9048 --- [person-service] [           main] d.i.p.web.PersonQueryController          : Returning 1 persons for company=100 includeTrashed=false query=null
2026-03-09T15:08:41.482+01:00  WARN 9048 --- [person-service] [           main] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.

`

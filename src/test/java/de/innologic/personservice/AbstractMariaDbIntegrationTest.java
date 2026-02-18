package de.innologic.personservice;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers(disabledWithoutDocker = true)
public abstract class AbstractMariaDbIntegrationTest {

    @Container
    @SuppressWarnings("resource")
    static final MariaDBContainer<?> MARIA_DB = new MariaDBContainer<>("mariadb:11.4")
            .withDatabaseName("person_service_test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void registerDatasource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MARIA_DB::getJdbcUrl);
        registry.add("spring.datasource.username", MARIA_DB::getUsername);
        registry.add("spring.datasource.password", MARIA_DB::getPassword);
        registry.add("spring.datasource.driver-class-name", MARIA_DB::getDriverClassName);
    }
}

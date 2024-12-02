package com.finmid.challenge;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class BankingServiceJavaTest {
    @Container
    private static final PostgreSQLContainer container = new PostgreSQLContainer(DockerImageName.parse("postgres:17"));

    private BankingServiceJava service = new BankingServiceJava(
            ApplicationKt.startBankingApplication(container.getJdbcUrl(), container.getUsername(), container.getPassword())
    );

    @BeforeAll
    public static void migrate() {
        Flyway.configure()
                .dataSource(container.getJdbcUrl(), container.getUsername(), container.getPassword())
                .createSchemas(true)
                .load()
                .migrate();
    }
}

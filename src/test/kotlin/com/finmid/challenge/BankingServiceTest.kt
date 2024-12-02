package com.finmid.challenge

import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.math.BigDecimal
import java.util.UUID

@Testcontainers
class BankingServiceTest {
    private val service = startBankingApplication(container.jdbcUrl, container.username, container.password)

    @Test
    fun `should create account`() {

        val id = UUID(0, 1)
        service.createAccount(id, BigDecimal.TEN)

        val account = service.findAccount(id)
        assert(account != null)
        assert(account?.id == id)
        assert(account?.balance?.compareTo(BigDecimal.TEN) == 0)
    }

    companion object {

        @JvmStatic
        @BeforeAll
        fun migrateSchema() {
            Flyway.configure()
                .dataSource(container.jdbcUrl, container.username, container.password)
                .createSchemas(true)
                .load()
                .migrate()
        }

        @JvmStatic
        @Container
        val container = PostgreSQLContainer(DockerImageName.parse("postgres:17"))
    }
}
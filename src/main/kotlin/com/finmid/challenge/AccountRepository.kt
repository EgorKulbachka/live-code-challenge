package com.finmid.challenge

import java.util.UUID
import javax.sql.DataSource

class AccountRepository(
    private val dataSource: DataSource,
) {

    fun saveAccount(account: Account) {
        dataSource.connection.use { connection ->
            connection.prepareStatement("INSERT INTO accounts (id, balance) VALUES (?, ?)").use { stmt ->
                stmt.setObject(1, account.id)
                stmt.setBigDecimal(2, account.balance)
                stmt.executeUpdate()
            }
        }
    }

    fun findAccount(id: UUID): Account? {
        return dataSource.connection.use { connection ->
            connection.prepareStatement("SELECT * FROM accounts WHERE id = ?").use { stmt ->
                stmt.setObject(1, id)
                stmt.executeQuery().use {
                    when (it.next()) {
                        true -> Account(it.getObject(1, UUID::class.java), it.getBigDecimal(2))
                        false -> null
                    }
                }
            }
        }
    }
}
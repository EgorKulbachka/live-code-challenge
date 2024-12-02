package com.finmid.challenge

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

fun startBankingApplication(
    jdbcUrl: String,
    username: String,
    password: String,
): BankingService {
    val dataSource = HikariConfig().apply {
        this.jdbcUrl = jdbcUrl
        this.username = username
        this.password = password
    }.let { HikariDataSource(it) }

    return BankingService(AccountRepository(dataSource))
}
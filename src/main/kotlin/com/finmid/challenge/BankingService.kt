package com.finmid.challenge

import java.math.BigDecimal
import java.util.UUID

class BankingService(
    private val accountRepository: AccountRepository,
) {

    fun createAccount(id: UUID, balance: BigDecimal) {
        accountRepository.saveAccount(Account(id, balance))
    }

    fun findAccount(id: UUID): Account? =
        accountRepository.findAccount(id)

    fun transferMoney() {
        TODO()
    }
}
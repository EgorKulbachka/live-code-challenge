package com.finmid.challenge

import java.math.BigDecimal
import java.util.UUID

data class Account(
    val id: UUID,
    val balance: BigDecimal,
)
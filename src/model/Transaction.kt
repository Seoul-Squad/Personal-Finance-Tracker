package model

import java.time.LocalDate

data class Transaction(
    val id: String,
    val amount: Double,
    val type: TransactionType,
    val category: String,
    val date: LocalDate,
)

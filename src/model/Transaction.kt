package model

import java.time.LocalDate

/**
 * Represents the financial transaction
 * @property id Unique identifier for the transaction
 * @property amount The amount of money in the transaction, it should always be positive
 * @property type The type of the transaction
 * @property category The user-defined category of the transaction
 * @property date The date when the transaction happened
 * @see TransactionType
 */
data class Transaction(
    val id: String,
    val amount: Double,
    val type: TransactionType,
    val category: String,
    val date: LocalDate,
)

package model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: String,
    val amount: Double?,
    val type: TransactionType?,
    val category: String,
    val date: LocalDate
) {
    fun validateTransaction(): List<String> {
        val errors = mutableListOf<String>()

        if (amount.toString()
                .isBlank() || amount == null
        ) errors.add(TransactionValidationErrors.INVALID_AMOUNT_NOT_NUMBER.message)
        else if (amount <= 0) errors.add(TransactionValidationErrors.INVALID_AMOUNT_RANGE.message)

        if (type == null) errors.add(TransactionValidationErrors.INVALID_TYPE.message)

        if (category.isBlank()) errors.add(TransactionValidationErrors.INVALID_CATEGORY.message)

        return errors

    }
}




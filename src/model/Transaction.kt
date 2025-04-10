package model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Transaction(
    val id: String,
    val amount: Double,
    val type: TransactionType,
    val category: String,
    val date: LocalDate,
) {
    fun validateTransaction (): List<String> {
        val errors = mutableListOf<String>()
        if (amount <= 0)
            errors.add(TransactionValidationErrors.INVALID_AMOUNT.message)

        if(category.isBlank())
            errors.add(TransactionValidationErrors.INVALID_CATEGORY.message)

        return errors

    }
}




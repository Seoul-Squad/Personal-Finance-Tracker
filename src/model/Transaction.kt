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
        val errors = mutableListOf<TransactionValidationErrors>()
        if (amount <= 0)
            errors.add(TransactionValidationErrors.INVALID_AMOUNT)

        if(category.isBlank())
            errors.add(TransactionValidationErrors.INVALID_CATEGORY)

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        if(!date.format(formatter).matches(Regex("\\d{4}-\\d{2}-\\d{2}")))
            errors.add(TransactionValidationErrors.INVALID_DATE)

        return getMessages(errors)

    }
}

private fun getMessages (listOfErrors: List<TransactionValidationErrors>): List<String> {
    return listOfErrors.map { error ->
        when (error) {
            TransactionValidationErrors.INVALID_AMOUNT -> "Amount must be greater than 0"
            TransactionValidationErrors.INVALID_CATEGORY -> "Category cannot be empty or blank"
            TransactionValidationErrors.INVALID_DATE -> "Date must be in valid yyyy-MM-dd format"
        }
    }
}


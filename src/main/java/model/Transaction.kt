package model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import storage.util.LocalDateSerializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
data class Transaction(
    val id: String,
    val amount: Double,
    val type: TransactionType,
    val category: String,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate = LocalDate.now()) {
    fun validateTransaction (): List<String> {
        val errors = mutableListOf<String>()
        if (amount <= 0)
            errors.add(TransactionValidationErrors.INVALID_AMOUNT.message)

        if(category.isBlank())
            errors.add(TransactionValidationErrors.INVALID_CATEGORY.message)

        return errors

    }
}




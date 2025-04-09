import model.Transaction
import model.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


object TransactionsValidator {
    fun isTransactionValid(transaction: Transaction): Boolean {
        if (transaction.amount <= 0.0) return false

        if (transaction.type != TransactionType.INCOME &&
            transaction.type != TransactionType.EXPENSE) return false

        if (transaction.category.isEmpty() || transaction.category.isBlank()) return false

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateString = transaction.date.toString()

        // Check if date matches the expected format
        return try {
            LocalDate.parse(dateString, formatter)
            true
        } catch (e: Exception) {
            false
        }
    }
}
import model.MonthlySummary
import model.Transaction
import model.TransactionType
import storage.TransactionStorage
import java.time.format.DateTimeFormatter

class TransactionManager(
    private val storage: TransactionStorage
) {
    fun addTransaction(transaction: Transaction): List<String> {
        val errors = transaction.validateTransaction()
        if (errors.isEmpty()) {
            storage.saveTransaction(transaction)
        }
        return errors
    }

    fun editTransaction(updatedTransaction: Transaction): List<String> {
        val errorList = updatedTransaction.validateTransaction()

        if(!errorList.isNotEmpty()){
            storage.editTransaction(updatedTransaction)
        }
        return errorList
    }

    fun deleteTransaction(transactionId: String) : Boolean{
        return storage.deleteTransaction(transactionId)
    }

    fun getAllTransactions(): List<Transaction> {
        return storage.loadTransactions()
    }

    fun getMonthlySummary(): List<MonthlySummary> {
        val transactions = getAllTransactions()

        val grouped = transactions.groupBy {
            it.date.format(DateTimeFormatter.ofPattern("MM-yyyy"))
        } // result is map<LocalDate, List<Transaction>>

        val summaries = grouped.map { (monthYear, transactions) ->
            val totalIncome = transactions
                .filter { it.type == TransactionType.INCOME }
                .sumOf { it.amount?: 0.0 }

            val totalExpense = transactions
                .filter { it.type == TransactionType.EXPENSE }
                .sumOf { it.amount?: 0.0 }

            MonthlySummary(monthYear, totalIncome, totalExpense)
        }
        return summaries.sortedBy { it.monthYear }
    }
}

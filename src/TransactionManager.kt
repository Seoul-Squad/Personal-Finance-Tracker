import model.MonthlySummary
import model.Transaction
import model.TransactionType
import storage.TransactionStorage
import java.time.format.DateTimeFormatter

class TransactionManager(
    private val storage: TransactionStorage
) {
    fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun edit(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun delete(transactionId: String) {
        TODO("Not yet implemented")
    }

    fun getAll(): List<Transaction> {
        TODO("Not yet implemented")
    }

    fun getMonthlySummary() : List<MonthlySummary> {
        val transactions = getAll()

        val grouped = transactions.groupBy {
            it.date.format(DateTimeFormatter.ofPattern("MMMM yyyy"))
        } // result is map<String,List<Transaction>>

        val summaries = grouped.map { (monthYear, transactions) ->
            val totalIncome = transactions
                .filter { it.type == TransactionType.INCOME }
                .sumOf { it.amount }

            val totalExpense = transactions
                .filter { it.type == TransactionType.EXPENSE }
                .sumOf { it.amount }

            MonthlySummary(monthYear, totalIncome, totalExpense)
        }
        return summaries.sortedBy { it.monthYear }
    }
}
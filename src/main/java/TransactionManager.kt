import main.java.SummaryGenerator
import model.MonthlySummary
import model.Transaction
import storage.TransactionStorage

class TransactionManager(
    private val storage: TransactionStorage,
    private val summaryGenerator: SummaryGenerator
) {
    fun addTransaction(transaction: Transaction): List<String> {
        val errors = transaction.validateTransaction()
        if (errors.isEmpty()) {
            storage.save(transaction)
        }
        return errors
    }

    fun edit(updatedTransaction: Transaction): List<String> {
        val errorList = updatedTransaction.validateTransaction()

        if (!errorList.isNotEmpty()) {
            storage.edit(updatedTransaction)
        }
        return errorList
    }

    fun delete(transactionId: String): Boolean {
        return storage.delete(transactionId)
    }

    fun getAll(): List<Transaction> {
        return storage.load()
    }

    fun getMonthlySummary(): List<MonthlySummary> {
        val transactions = getAll()
        return summaryGenerator.generateMonthlySummaries(transactions)
    }
}

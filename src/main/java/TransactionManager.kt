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
        val transactions = getAll()
        return summaryGenerator.generateMonthlySummaries(transactions)
    }
}

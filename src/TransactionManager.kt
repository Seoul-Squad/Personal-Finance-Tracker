import model.Transaction
import storage.TransactionStorage
import java.time.LocalDate

class TransactionManager(
    private val storage: TransactionStorage
) {
    fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun edit(updatedTransaction: Transaction): Boolean {
        val originalTransaction = storage.getByID(updatedTransaction.id)

        if (originalTransaction == null) return false
        if (updatedTransaction.amount < 0) return false
        if (updatedTransaction.category.isBlank()) return false
        if (updatedTransaction.date.isAfter(LocalDate.now())) return false

        storage.edit(updatedTransaction)
        return true
        //
    }

    fun delete(transactionId: String) {
        TODO("Not yet implemented")
    }

    fun getAll(): List<Transaction> {
        TODO("Not yet implemented")
    }

    fun getMonthlySummary() {
        //note: this function's parameters and return type were not set since it wasn't agreed upon in the meeting
        //you can set it based on your implementation
        TODO("Not yet implemented")
    }

}
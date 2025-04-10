import model.Transaction
import storage.TransactionStorage

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
        return storage.load()
    }

    fun getMonthlySummary() {
        //note: this function's parameters and return type were not set since it wasn't agreed upon in the meeting
        //you can set it based on your implementation
        TODO("Not yet implemented")
    }

}
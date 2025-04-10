import model.Transaction
import storage.TransactionStorage
import java.time.LocalDate

class TransactionManager(
    private val storage: TransactionStorage
) {
    fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun edit(updatedTransaction: Transaction) {
        val errorList = updatedTransaction.validateTransaction()

        if(!errorList.isNotEmpty()){
            storage.edit(updatedTransaction)
        }else{
            println(errorList)
        }

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
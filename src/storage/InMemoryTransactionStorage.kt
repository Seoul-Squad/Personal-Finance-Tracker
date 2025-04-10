package storage

import model.Transaction
import model.TransactionValidationErrors

class InMemoryTransactionStorage : TransactionStorage {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun save(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun edit(updatedTransaction: Transaction) {
        val index = transactions.indexOfFirst { it.id == updatedTransaction.id }
        return if (index != -1) {
            transactions[index] = updatedTransaction
        } else {
            println(TransactionValidationErrors.INVALID_ID.message)
        }
    }

    override fun delete(transactionId: String) {
        TODO("Not yet implemented")
    }

    override fun load(): List<Transaction> {
        TODO("Not yet implemented")
    }


}
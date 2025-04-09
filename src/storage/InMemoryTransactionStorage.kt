package storage

import model.Transaction

class InMemoryTransactionStorage : TransactionStorage {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun save(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun edit(updatedTransaction: Transaction): Boolean {
        val index = transactions.indexOfFirst { it.id == updatedTransaction.id }
        return if (index != -1) {
            transactions[index] = updatedTransaction
            true
        } else {
            false
        }
    }

    override fun delete(transactionId: String) {
        TODO("Not yet implemented")
    }

    override fun load(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getByID(id: String): Transaction? {
        //if ID contained, return transaction
        return transactions.find { it.id == id }
    }

    // we use it for testing
//    fun add(transaction: Transaction){
//        transactions.add(transaction)
//    }
}
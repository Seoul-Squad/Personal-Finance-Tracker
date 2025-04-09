package storage

import model.Transaction

class InMemoryTransactionStorage : TransactionStorage {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun save(transaction: Transaction) {
        transactions.add(transaction)
        //println(transactions)
    }

    override fun edit(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun delete(transactionId: String) {
        TODO("Not yet implemented")
    }

    override fun load(): List<Transaction> {
        TODO("Not yet implemented")
    }
}
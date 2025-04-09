package storage

import model.Transaction


interface TransactionStorage {
    fun save(transaction: Transaction)
    fun edit(updatedTransaction: Transaction): Boolean
    fun delete(transactionId: String)
    fun load(): List<Transaction>
    fun getByID(id: String): Transaction?
}
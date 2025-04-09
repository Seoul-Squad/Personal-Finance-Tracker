package storage

import model.Transaction


interface TransactionStorage {
    fun save(transaction: Transaction)
    fun edit(transaction: Transaction)
    fun delete(transactionId: String)
    fun load(): List<Transaction>
}
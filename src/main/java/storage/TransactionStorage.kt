package storage

import model.Transaction


interface TransactionStorage {
    fun saveTransaction(transaction: Transaction)
    fun editTransaction(updatedTransaction: Transaction)
    fun deleteTransaction(transactionId: String):Boolean
    fun loadTransactions(): List<Transaction>
}
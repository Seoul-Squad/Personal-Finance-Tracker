package storage

import model.Transaction
import model.TransactionValidationErrors

class InMemoryTransactionStorage : TransactionStorage {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun saveTransaction(transaction: Transaction) {
        transactions.add(transaction)
        //println(transactions)
    }

    override fun editTransaction(updatedTransaction: Transaction) {
        val index = transactions.indexOfFirst { it.id == updatedTransaction.id }
        if (index != -1) {
            transactions[index] = updatedTransaction
        } else {
            println(TransactionValidationErrors.INVALID_ID.message)
        }
    }

    override fun deleteTransaction(transactionId: String): Boolean {
        val transactionIndex = transactions.indexOfFirst { it.id == transactionId }
        return if(transactionIndex != -1){
            transactions.removeAt(transactionIndex)
            true
        } else {
            false
        }
    }

    override fun loadTransactions(): List<Transaction> {
        return transactions
    }
}
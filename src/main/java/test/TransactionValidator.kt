package main.java.test

import model.Transaction

object TransactionsValidator {
    fun isTransactionValid(transaction: Transaction): Boolean {
        val isAmountValid = transaction.amount != null && transaction.amount >= 0
        val isCategoryValid = transaction.category.isNotBlank()
        return isAmountValid && isCategoryValid
    }
}
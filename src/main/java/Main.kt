package main.java

import InFileTransactionStorage
import TransactionManager
import storage.TransactionStorage

fun main() {
    val storage: TransactionStorage = InFileTransactionStorage()
    val transactionSummaryGenerator: TransactionSummaryGenerator = TransactionSummaryGenerator()
    val manager = TransactionManager(storage, transactionSummaryGenerator)
    val consoleManager = ConsoleManager(manager)
    consoleManager.startApp()
}


package main.java

import InFileTransactionStorage
import TransactionManager
import storage.TransactionStorage

fun main() {
    val storage: TransactionStorage = InFileTransactionStorage
    val summaryGenerator: SummaryGenerator = SummaryGenerator()
    val manager = TransactionManager(storage, summaryGenerator)
    val consoleManager = ConsoleManager(manager)
    consoleManager.startApp()
}


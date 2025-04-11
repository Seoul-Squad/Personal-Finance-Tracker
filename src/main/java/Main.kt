package main.java

import InFileTransactionStorage
import TransactionManager
import storage.TransactionStorage

fun main() {
    val storage: TransactionStorage = InFileTransactionStorage
    val manager = TransactionManager(storage)
    val consoleManager = ConsoleManager(manager)
    consoleManager.startApp()
}


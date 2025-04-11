package main.java

import InFileTransactionStorage
import TransactionManager
import model.Transaction
import model.TransactionType
import main.java.utils.AnsiColor
import java.util.UUID
import java.time.LocalDate
import model.MonthlySummary
import storage.TransactionStorage
import java.time.format.DateTimeFormatter

fun main() {
    val storage: TransactionStorage = InFileTransactionStorage
    val manager = TransactionManager(storage)
    val consoleManager = ConsoleManager(manager)
    consoleManager.startApp()
}


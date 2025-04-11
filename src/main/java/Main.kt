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

    printWelcomeMessage()

    while (true) {
        showMenuOptions()
        when (readln().trim()) {
            "1" -> {
                addTransaction(manager)
            }
            "2" -> {
                getAllTransactions(manager)
            }
            "3" -> {
                startEditTransactionFlow(manager)
            }
            "4" -> {
                startDeleteItemFlow(manager)
            }
            "0" -> {
                println("Exiting... Goodbye!")
                return
            }
            else -> println("${AnsiColor.RED}Invalid option. Please enter one of the available options.${AnsiColor.RESET}")
        }
        if (askToContinue()) continue else return
    }
}

fun printWelcomeMessage() {
    println("${AnsiColor.CYAN}========================================")
    println("Welcome to Your Personal Finance Tracker")
    println("========================================${AnsiColor.RESET}")
}

fun askToContinue(): Boolean {
    while (true) {
        println("\nDo you want to do another operation?")
        println("1. YES")
        println("2. NO")
        when (readln().trim()) {
            "1" -> return true
            "2" -> return false
            else -> println("${AnsiColor.RED}Invalid option. Please enter 1 or 2.${AnsiColor.RESET}")
        }
    }
}

private fun addTransaction(manager: TransactionManager) {
    val transaction = createTransactionInput()
    val errors = manager.addTransaction(transaction)

    if (errors.isNotEmpty()) {
        println("${AnsiColor.RED}Transaction is not valid:${AnsiColor.RESET}")
        errors.forEach { println("- $it") }
    } else {
        println("${AnsiColor.GREEN}Transaction added successfully!${AnsiColor.RESET}")
    }
}

fun getMonthlySummaries(transactionManager: TransactionManager) {
    val summaries = transactionManager.getMonthlySummary()
    printMonthlySummaries(summaries)
}

fun printMonthlySummaries(summaries: List<MonthlySummary>) {
    println("\u001b[1m\u001b[4mMonthly Summary:\u001b[0m") // Bold + Underlined
    summaries.forEach { summary ->
        val netBalance = summary.income - summary.expense
        val balanceColor = if (netBalance >= 0) "\u001b[32m" else "\u001b[31m" // Green or Red
        println("\n\u001b[1m${summary.monthYear}:\u001b[0m") // Bold month name
        println("  \u001b[34mTotal Income:\u001b[0m $${summary.income}")
        println("  \u001b[34mTotal Expenses:\u001b[0m $${summary.expense}")
        println("  ${balanceColor}Net Balance:\u001b[0m $${"%.2f".format(netBalance)}")
    }
}

fun getAllTransactions(transactionManager: TransactionManager) {
    val allTransactions = transactionManager.getAll()
    printAllTransactions(allTransactions)
}

fun printAllTransactions(transactions: List<Transaction>) {
    println("\u001b[1m\u001b[4mAll transactions:\u001B[0m \n") // Bold + Underlined
    println("\u001B[1m+------+------------------+---------------------+-----------+-------------------------+")
    println("| ID   | Amount           | Category            | Type      |   Date(dd/mm/yyyy)      |")
    println("+------+------------------+---------------------+-----------+-------------------------+")
    transactions.forEach { transaction ->
        val dateString = "${transaction.date.dayOfMonth}/${transaction.date.monthValue}/${transaction.date.year}"
        val amount = "${transaction.amount} $"
        println(
            "| ${transaction.id.padEnd(4)} | ${amount.padEnd(16)} | ${transaction.category.padEnd(19)} | ${
                transaction.type.toString().padEnd(9)
            } |  ${dateString.padEnd(22)} |"
        )
    }
    println("+------+------------------+---------------------+-----------+-------------------------+\u001b[0m")
}

fun isValidTransactionDate(date: String): Boolean {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return date.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))
}

fun readFormattedDate(prompt: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    while (true) {
        print(prompt)
        val input = readln().trim()
        if (isValidTransactionDate(input)) {
            return LocalDate.parse(input, formatter)
        }
        println("ERROR INVALID DATE FORMAT")
    }
}

fun startDeleteItemFlow(transactionManager: TransactionManager) {
    val transactions = transactionManager.getAll()
    printAllTransactions(transactions)

    print("The id of the transaction to be deleted: ")
    val transactionId = readln().trim()
    if (transactionManager.delete(transactionId)) {
        println("Transaction deleted successfully!")
    } else {
        println("No transaction found with matching id.")
    }
}

fun startEditTransactionFlow(transactionManager: TransactionManager) {
    val transactions = transactionManager.getAll()
    printAllTransactions(transactions)

    print("Enter the ID of the transaction to edit: ")
    val transactionId = readln().trim()
    val transactionToEdit = transactions.find { it.id == transactionId }

    if (transactionToEdit == null) {
        println("${AnsiColor.RED}Transaction not found!${AnsiColor.RESET}")
        return
    }

    println("Enter new details for the transaction.")
    // Reuse createTransactionInput to get the new data, then keep the existing ID.
    val updatedTransactionData = createTransactionInput()
    // Create an updated transaction using the same ID.
    val updatedTransaction = updatedTransactionData.copy(id = transactionId)

    // Call the edit function from your TransactionManager (make sure it's implemented)
    val errors = transactionManager.edit(updatedTransaction)
    if (errors.isNotEmpty()) {
        println("${AnsiColor.RED}Errors editing transaction:${AnsiColor.RESET}")
        errors.forEach { println("- $it") }
    } else {
        println("${AnsiColor.GREEN}Transaction updated successfully!${AnsiColor.RESET}")
    }
}

fun showMenuOptions() {
    println("\nPlease choose an option:")
    println("1. Add Transaction")
    println("2. View All Transactions")
    println("3. Edit Transaction")
    println("4. Delete Transaction")
    println("0. Exit")
}

fun createTransactionInput(): Transaction {
    print("Enter the amount: ")
    val amount = readln().toDoubleOrNull()

    print("Enter the category (e.g., Groceries, Utilities, etc.): ")
    val category = readln().trim()

    println("Select transaction type:")
    println("1. INCOME")
    println("2. EXPENSE")
    val type = when (readln().trim()) {
        "1" -> TransactionType.INCOME
        "2" -> TransactionType.EXPENSE
        else -> TransactionType.INVALID
    }

    val localDate = readFormattedDate("Enter the date (yyyy-MM-dd): ")

    return Transaction(
        id = UUID.randomUUID().toString().take(6),
        amount = amount,
        category = category,
        type = type,
        date = localDate
    )
}

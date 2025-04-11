package main.java

import TransactionManager
import main.java.utils.AnsiColor
import model.MonthlySummary
import model.Transaction
import model.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ConsoleManager(
    private val transactionManager: TransactionManager
) {
    fun startApp(){
        printWelcomeMessage()

        while (true) {
            showMenuOptions()
            when (readln().trim()) {
                "1" -> {
                    addTransaction(transactionManager)
                }
                "2" -> {
                    startEditTransactionFlow(transactionManager)
                }
                "3" -> {
                    startDeleteItemFlow(transactionManager)
                }
                "4" -> {
                    getAllTransactions(transactionManager)
                }
                "5" -> {
                    getMonthlySummaries(transactionManager)
                }
                "0" -> {
                    println("Exiting... Goodbye!")
                    return
                }
                else -> println("${AnsiColor.RED}Invalid option. Please enter one of the available options.${AnsiColor.RESET}")
            }
            if (askToContinue()) {
                println("========================================${AnsiColor.RESET}")
                continue
            } else {
                return
            }
        }
    }
    private fun printWelcomeMessage() {
        println("${AnsiColor.CYAN}========================================")
        println("Welcome to Your Personal Finance Tracker")
        println("========================================${AnsiColor.RESET}")
    }

    private fun askToContinue(): Boolean {
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

    private fun getMonthlySummaries(transactionManager: TransactionManager) {
        val summaries = transactionManager.getMonthlySummary()
        printMonthlySummaries(summaries)
    }

    private fun printMonthlySummaries(summaries: List<MonthlySummary>) {
        println("${AnsiColor.BOLD}${AnsiColor.UNDERLINED}mMonthly Summary:${AnsiColor.RESET}") // Bold + Underlined
        summaries.forEach { summary ->
            val netBalance = summary.totalIncome - summary.totalExpense
            val balanceColor = if (netBalance >= 0) AnsiColor.GREEN else AnsiColor.RED // Green or Red
            println("\n${AnsiColor.BOLD}${summary.monthYear}:${AnsiColor.RESET}") // Bold month name
            println("  ${AnsiColor.BLUE}Total Income:${AnsiColor.RESET}m $${summary.totalIncome}")
            println("  ${AnsiColor.BLUE}Total Expenses:${AnsiColor.RESET} $${summary.totalExpense}")
            println("  ${balanceColor}Net Balance:${AnsiColor.RESET} $${"%.2f".format(netBalance)}")
        }
    }

    private fun getAllTransactions(transactionManager: TransactionManager) {
        val allTransactions = transactionManager.getAll()
        printAllTransactions(allTransactions)
    }

    private fun printAllTransactions(transactions: List<Transaction>) {
        val maxCategoryLength = transactions.maxOf { it.category.length }
        println("${AnsiColor.BOLD}${AnsiColor.UNDERLINED}All transactions:${AnsiColor.RESET} \n") // Bold + Underlined
        println("${AnsiColor.BOLD}+--------+-----------------+${"".padEnd(maxOf(30, maxCategoryLength + 2), '-')}+-----------+-------------------------+")
        println("| ID     | Amount          | ${"Category".padEnd(maxOf(28, maxCategoryLength), ' ')} | Type      |   Date(dd/mm/yyyy)      |")
        println("+--------+-----------------+${"".padEnd(maxOf(30, maxCategoryLength + 2), '-')}+-----------+-------------------------+")
        transactions.forEach { transaction ->
            val dateString = "${transaction.date.dayOfMonth}/${transaction.date.monthValue}/${transaction.date.year}"
            val amount = "${transaction.amount} $"
            println(
                "| ${transaction.id.padEnd(6)} | ${amount.padEnd(15)} | ${transaction.category.padEnd(maxOf(28, maxCategoryLength), ' ')} | ${
                    transaction.type.toString().padEnd(9)
                } |  ${dateString.padEnd(22)} |"
            )
        }
        println("+--------+-----------------+${"-".padEnd(maxOf(30, maxCategoryLength + 2), '-')}+-----------+-------------------------+${AnsiColor.RESET}")
    }

    private fun isValidTransactionDate(date: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))
    }

    private fun readFormattedDate(prompt: String): LocalDate {
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

    private fun startDeleteItemFlow(transactionManager: TransactionManager) {
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

    private fun startEditTransactionFlow(transactionManager: TransactionManager) {
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

    private fun showMenuOptions() {
        println("\nPlease choose an option:")
        println("1. Add Transaction")
        println("2. Edit Transaction")
        println("3. Delete Transaction")
        println("4. View All Transactions")
        println("5. Get Monthly Summary")
        println("0. Exit")
    }

    private fun createTransactionInput(): Transaction {
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

}
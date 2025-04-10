import model.Transaction
import model.TransactionType
import storage.InMemoryTransactionStorage
import utils.AnsiColor
import java.util.UUID
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun main() {
    val storage = InMemoryTransactionStorage()
    val manager = TransactionManager(storage)

    printWelcomeMessage()

    while (true) {
        showMenuOptions()
        when (readln().trim()) {
            "1" -> {
                if (addTransaction(manager)) return
            }

            "0" -> {
                println("Exiting... Goodbye!")
                return
            }

            else -> println("${AnsiColor.RED}Invalid option. Please enter 1 or 0.${AnsiColor.RESET}")
        }
    }
}

private fun addTransaction(manager: TransactionManager): Boolean {
    val transaction = createTransactionInput()
    val errors = manager.addTransaction(transaction)

    if (errors.isNotEmpty()) {
        println("${AnsiColor.RED}Transaction is not valid:${AnsiColor.RESET}")
        errors.forEach { println("- $it") }
    } else {
        println("${AnsiColor.GREEN}Transaction added successfully!${AnsiColor.RESET}")
    }

    if (!askToContinue()) return true
    return false
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
        id = UUID.randomUUID().toString(),
        amount = amount,
        category = category,
        type = type,
        date = localDate
    )
}

fun isValidTransactionDate(date: String): Boolean {
    return date.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))
}

fun readFormattedDate(prompt: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    while (true) {
        print(prompt)
        val input = readln().trim()
        if (isValidTransactionDate(input)) {
            try {
                return LocalDate.parse(input, formatter)
            } catch (e: Exception) {
                // Continue on parsing error
            }
        }
        println("${AnsiColor.RED}Invalid date format. Please use yyyy-MM-dd${AnsiColor.RESET}")
    }
}

fun printWelcomeMessage() {
    println("${AnsiColor.CYAN}========================================")
    println("Welcome to Your Personal Finance Tracker")
    println("========================================${AnsiColor.RESET}")
}

fun showMenuOptions() {
    println("\nPlease choose an option:")
    println("1. Add Transaction")
    println("0. Exit")
}
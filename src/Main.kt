import model.MonthlySummary
import storage.InMemoryTransactionStorage
import model.Transaction
import java.time.format.DateTimeFormatter

fun main() {
}

fun getMonthlySummaries(transactionManager: TransactionManager) {
    val summaries = transactionManager.getMonthlySummary()

    printMonthlySummaries(summaries)
}

fun printMonthlySummaries(summaries: List<MonthlySummary>) {
    println("\u001b[1m\u001b[4mMonthly Summary:\u001b[0m") // Bold + Underlined

    summaries.forEach { summary ->
        val netBalance = summary.income - summary.expense
        val balanceColor = if (netBalance >= 0) "\u001b[32m" else "\u001b[31m" // Green([32m) or Red()

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
    println("\u001b[1m\u001b[4mAll transactions:\u001B[0m \n") // Bold() + Underlined()

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
    return date.format(formatter).matches(Regex("\\d{4}-\\d{2}-\\d{2}"))
}

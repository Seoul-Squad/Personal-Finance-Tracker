import model.MonthlySummary
import storage.InMemoryTransactionStorage
import java.time.format.DateTimeFormatter

fun main() {
    val storage = InMemoryTransactionStorage()
    val transactionManager = TransactionManager(storage)

    getMonthlySummaries(transactionManager)
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

import model.MonthlySummary
import model.Transaction
import model.TransactionType
import storage.InMemoryTransactionStorage
import java.time.LocalDate

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val name = "Kotlin"
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    println("Hello, " + name + "!")

    for (i in 1..5) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        println("i = $i")
    }
}

fun allTransactions() {
    val transactionManager = TransactionManager(InMemoryTransactionStorage())

    val allTransactions = transactionManager.getAll()

    printAllTransaction(allTransactions)
}

fun printAllTransaction(transactions: List<Transaction>) {
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

fun monthlySummaries() {
    val transactionManager = TransactionManager(InMemoryTransactionStorage())

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
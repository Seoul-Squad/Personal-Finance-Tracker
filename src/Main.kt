import model.MonthlySummary
import model.Transaction
import storage.InMemoryTransactionStorage

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
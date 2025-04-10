import java.time.format.DateTimeFormatter

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

fun isValidTransactionDate(date: String): Boolean {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return date.format(formatter).matches(Regex("\\d{4}-\\d{2}-\\d{2}"))

}


fun startDeleteItemFlow(
    transactionManager: TransactionManager
) {
    val transactions = transactionManager.getAll()
    transactions.forEachIndexed { index, transaction ->
        println("$index- $transaction")
    }
    print("The id of the transaction to be delete: ")
    val transactionId = readln().trim()
    if(transactionManager.delete(transactionId)) {
        println("Transaction deleted successfully!")
    } else {
        println("No transaction found with matching id.")
    }
}
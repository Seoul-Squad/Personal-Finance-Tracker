import model.Transaction
import java.time.format.DateTimeFormatter

fun main() {
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

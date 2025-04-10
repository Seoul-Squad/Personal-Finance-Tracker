import model.Transaction
import model.TransactionType
import storage.InMemoryTransactionStorage
import java.util.UUID
import java.time.LocalDate


import java.time.format.DateTimeFormatter

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val storage: InMemoryTransactionStorage = InMemoryTransactionStorage()
    val manager: TransactionManager = TransactionManager(storage)

    println("========================================")
    println("Welcome to Your Personal Finance Tracker ")
    println("========================================")

    while (true) {
        // Menu options
        println("Please choose an option:")
        println("1. Add Transaction")
        println("0. Exit")
// REPLACE NUMBER WITH ENUM CLASS
        when (readln().trim()) {
            "1" -> {
                val transaction = createTransactionInput() ?: continue
                manager.add(transaction)

                println("Do you want to do other operation")
                println("1- YES")
                println("2- NO")
                when (readln().trim()){
                    "1" -> continue
                    "2" ->  return
                }
            }

            "0" -> {
                println("Exiting... Goodbye!")
                return
            }


        }
    }

}


fun createTransactionInput(): Transaction {
    print("Enter  the amount: ")
    val amount = readln().toDouble()

    print("Enter the category (e.g., Groceries, Utilities, etc.): ")
    val category = readln().trim()

    println("Select transaction type:")
    println("1. INCOME")
    println("2. EXPENSE")

    var type: TransactionType = TransactionType.INCOME

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
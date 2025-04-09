import model.Transaction
import model.TransactionType
import storage.InMemoryTransactionStorage
import java.util.UUID
import java.time.LocalDate


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

    when (readln().trim()) {
        "1" -> type = TransactionType.INCOME
        "2" -> type = TransactionType.EXPENSE
        else -> println("ERROR INVALID TRANSACTION TYPE")
    }


    print("Enter the date (yyyy-MM-dd): ")
    val date = LocalDate.parse(readln().trim())

    return Transaction(
        id = UUID.randomUUID().toString(),
        amount = amount,
        category = category,
        type = type,
        date = date
    )

}








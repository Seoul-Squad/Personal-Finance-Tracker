import model.Transaction
import model.TransactionType
import storage.InMemoryTransactionStorage
import storage.TransactionStorage
import java.time.LocalDate

fun main(){
    val storage: InMemoryTransactionStorage = InMemoryTransactionStorage()
    val transactionManager: TransactionManager = TransactionManager(storage)

    // add function not implemented in storage for that we use it just for testing
//    storage.add(Transaction("1", 100.0, TransactionType.INCOME, "Food", LocalDate.now()))
//    storage.add(Transaction("2", 200.0, TransactionType.INCOME, "money", LocalDate.now()))
//    storage.add(Transaction("3", 300.0, TransactionType.EXPENSE, "landry", LocalDate.now()))

    test(
        name = "Should update and return true, when transaction exists",
        result = transactionManager.edit(
            Transaction("1", 100.0, TransactionType.INCOME, "Food", LocalDate.now())),
        correctResult = true
    )

    test(
        name = "Should return false, when amount is negative",
        result = transactionManager.edit(
            Transaction("1", -1.0, TransactionType.INCOME, "Food", LocalDate.now())),
        correctResult = false
    )

    test(
        name = "Should return false, when category is empty",
        result = transactionManager.edit(
            Transaction("1", 100.0, TransactionType.INCOME, "", LocalDate.now())),
        correctResult = false
    )

    test(
        name = "Should return true, when amount is zero",
        result = transactionManager.edit(
            Transaction("1", 0.0, TransactionType.INCOME, "Food", LocalDate.now())),
        correctResult = true
    )

    test(
        name = "Should return false, when category is only whitespace",
        result = transactionManager.edit(
            Transaction("1", 50.0, TransactionType.EXPENSE, "   ", LocalDate.now())),
        correctResult = false
    )

    test(
        name = "Should return true, when category is contained upper and lower case",
        result = transactionManager.edit(
            Transaction("1", 150.0, TransactionType.INCOME, "TrAnSpOrT", LocalDate.now())),
        correctResult = true
    )

    test(
        name = "Should return true, when amount is a big value",
        result = transactionManager.edit(
            Transaction("1", 999999999.0, TransactionType.INCOME, "Bonus", LocalDate.now())),
        correctResult = true
    )

    test(
        name = "Should return true, when date is today",
        result = transactionManager.edit(
            Transaction("1", 123.0, TransactionType.EXPENSE, "Bills", LocalDate.now())),
        correctResult = true
    )
}

fun test(name: String, result: Boolean, correctResult: Boolean){
    if(result == correctResult){
        println("Success - $name")
    } else {
        println("Failed - $name")
    }
}
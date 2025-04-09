package test


import TransactionManager
import model.Transaction
import model.TransactionType
import storage.InMemoryTransactionStorage
import java.time.LocalDate

fun main() {
    val result = TransactionManager(InMemoryTransactionStorage()).getMonthlySummary()
    val transactions = listOf(
        Transaction(
            id = "1",
            amount = 265.5,
            type = TransactionType.EXPENSE,
            category = "Food",
            date = LocalDate.parse("2025-12-10"),
        ),
        Transaction(
            id = "2",
            amount = 265.5,
            type = TransactionType.EXPENSE,
            category = "Food",
            date = LocalDate.parse("2025-07-12"),
        ),
        Transaction(
            id = "3",
            amount = 265.5,
            type = TransactionType.EXPENSE,
            category = "Food",
            date = LocalDate.parse("2025-04-10"),
        ),
        Transaction(
            id = "4",
            amount = 2654.5,
            type = TransactionType.INCOME,
            category = "Food",
            date = LocalDate.parse("2025-08-12"),
        ),
        Transaction(
            id = "5",
            amount = 265.5,
            type = TransactionType.EXPENSE,
            category = "Food",
            date = LocalDate.parse("2025-11-22"),
        ),
    )
    check(
        name = "return true when result are not empty",
        result = transactions.isNotEmpty(),
        correctResult = true
    )

    check(
        name = "return true when result are empty",
        result = result.isEmpty(),
        correctResult = true
    )
}

fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("Passed - $name")
    } else {
        println("Failed - $name")
    }
}


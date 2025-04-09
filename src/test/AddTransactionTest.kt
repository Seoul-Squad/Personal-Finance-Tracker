package test

import TransactionsValidator.isTransactionValid
import model.Transaction
import model.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

fun main() {

    val date = "2025-04-09"
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val falseFormatter = DateTimeFormatter.ofPattern("yyyy-MM")

    check(
        name = "when have transaction with amount greater than 0, " +
                "type is INCOME or EXPENSE" +
                "category is not empty" +
                "and date in valid format, it should return true",
        result = isTransactionValid(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = 5000.0,
                type = TransactionType.INCOME,
                category = "Food",
                date = LocalDate.parse(date, formatter)
            )
        ),
        correctResult = true
    )

    check(
        name = "when have transaction with amount smaller than 1, it should return false",
        result = isTransactionValid(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = 0.0,
                type = TransactionType.INCOME,
                category = "Food",
                date = LocalDate.parse(date, formatter)
            )
        ),
        correctResult = false
    )

    check(
        name = "when have transaction with empty category, it should return false",
        result = isTransactionValid(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = 5000.0,
                type = TransactionType.INCOME,
                category = "",
                date = LocalDate.parse(date, formatter)
            )
        ),
        correctResult = false
    )

    check(
        name = "when have transaction with category contain spaces only, it should return false",
        result = isTransactionValid(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = 5000.0,
                type = TransactionType.INCOME,
                category = "      ",
                date = LocalDate.parse(date, formatter)
            )
        ),
        correctResult = false
    )

    check(
        name = "when have transaction with date in any invalid format, it should return false",
        result = isTransactionValid(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = 5000.0,
                type = TransactionType.INCOME,
                category = "Food",
                date = LocalDate.parse(date, falseFormatter)
            )
        ),
        correctResult = false
    )
}






fun check(name: String, result: Boolean, correctResult: Boolean) {
    if(result == correctResult) {
        println("Passed- $name")
    } else {
        println("Failed- $name")
    }
}
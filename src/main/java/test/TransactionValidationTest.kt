package test

import main.java.utils.check
import model.Transaction
import model.TransactionType
import model.TransactionValidationErrors
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {

    check(
        title = "when have transaction with amount greater than 0, " +
                "type is INCOME or EXPENSE" +
                "category is not empty" +
                "and date in valid format, it should return empty list",
        result = Transaction(
            id = UUID.randomUUID().toString(),
            amount = 5000.0,
            type = TransactionType.INCOME,
            category = "Food",
            date = LocalDate.now()

        ).validateTransaction(),
        expectedResult = listOf()
    )
    check(
        title = "when have transaction with type is null , it should return error list with INVALID_TYPE message",
        result = Transaction(
            id = UUID.randomUUID().toString(),
            amount = 5000.0,
            type = null,
            category = "Food",
            date = LocalDate.now()

        ).validateTransaction(),
        expectedResult = listOf(TransactionValidationErrors.INVALID_TYPE.message)
    )

    check(
        title = "when have transaction with amount smaller than 1, it should return list with INVALID_AMOUNT_RANGE message",
        result = Transaction(
            id = UUID.randomUUID().toString(),
            amount = 0.0,
            type = TransactionType.INCOME,
            category = "Food",
            date = LocalDate.now()
        ).validateTransaction(),
        expectedResult = listOf(TransactionValidationErrors.INVALID_AMOUNT_RANGE.message)
    )

    check(
        title = "when have transaction with amount not a number, it should return list with INVALID_AMOUNT_NOT_NUMBER message",
        result = Transaction(
            id = UUID.randomUUID().toString(),
            amount = ("").toDoubleOrNull(),
            type = TransactionType.INCOME,
            category = "Food",
            date = LocalDate.now()

        ).validateTransaction(),
        expectedResult = listOf(TransactionValidationErrors.INVALID_AMOUNT_NOT_NUMBER.message)
    )

    check(
        title = "when have transaction with empty category, it should return list with INVALID_CATEGORY message",
        result = Transaction(
            id = UUID.randomUUID().toString(),
            amount = 5000.0,
            type = TransactionType.INCOME,
            category = "",
            date = LocalDate.now()
        ).validateTransaction(),
        expectedResult = listOf(TransactionValidationErrors.INVALID_CATEGORY.message)
    )

    check(
        title = "when have transaction with category contain spaces only, it should return list with INVALID_CATEGORY message",
        result = Transaction(
            id = UUID.randomUUID().toString(),
            amount = 5000.0,
            type = TransactionType.INCOME,
            category = "       ",
            date = LocalDate.now()
        ).validateTransaction(),
        expectedResult = listOf(TransactionValidationErrors.INVALID_CATEGORY.message)
    )

}


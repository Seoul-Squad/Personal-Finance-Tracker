import java.time.format.DateTimeFormatter

import model.Transaction
import model.TransactionType
import java.time.LocalDate

fun main() {
    val storingTransactionsAtFile = InFileTransactionStorage
    val list = listOf(
        Transaction(id="1", amount =10.0 , type = TransactionType.INCOME , category = "a", LocalDate.now()),
        Transaction(id="2", amount =20.0 , type = TransactionType.INCOME , category = "b", LocalDate.now()),
        Transaction(id="3", amount =30.0 , type = TransactionType.INCOME , category = "c", LocalDate.now()),
        Transaction(id="4", amount =40.0 , type = TransactionType.INCOME , category = "d", LocalDate.now()),
        Transaction(id="5", amount =50.0 , type = TransactionType.INCOME , category = "e", LocalDate.now()),
        Transaction(id="6", amount =60.0 , type = TransactionType.INCOME , category = "f", LocalDate.now()),
    )
    list.forEach {
        storingTransactionsAtFile.save(it)
    }
//
//
//    val delete = storingTransactionsAtFile.delete("50")
//    println(delete)
    val delete1 = storingTransactionsAtFile.delete("5")
//    println(delete1)
    storingTransactionsAtFile.save(
        Transaction(id="23", amount =56515.0 , type = TransactionType.INCOME , category = "e", LocalDate.now())
    )
    storingTransactionsAtFile.edit(
        Transaction(id="1", amount =56515.0 , type = TransactionType.INCOME , category = "e", LocalDate.now())
    )
    storingTransactionsAtFile.load().forEach {
        println(it)
    }
}

fun isValidTransactionDate(date: String): Boolean {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return date.format(formatter).matches(Regex("\\d{4}-\\d{2}-\\d{2}"))

}
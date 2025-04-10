package storage

import com.google.gson.GsonBuilder
import model.Transaction
import storage.util.LocalDateAdapter
import java.io.File
import java.time.LocalDate

object StoringTransactionsAtFile :TransactionStorage{

    private val file: File = File("transactions.txt")
    private val transactions = mutableListOf<Transaction>()
    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        .create()


    override fun save(transaction: Transaction) {

      transactions.add(transaction)
        saveToFile()

    }

    override fun edit(transaction: Transaction) {
        transactions.removeIf { it.id == transaction.id }
        transactions.add(transaction)
        saveToFile()
    }

    override fun delete(transactionId: String) {

        // todo after delete transaction update file 
    }

    override fun load(): List<Transaction> {

        return emptyList() //todo get data from file then convert it to list of translations
    }
    private fun saveToFile() {
        if (!file.exists()) file.createNewFile()
        val jasonTransactions = gson.toJson(transactions)
        file.writeText(jasonTransactions)

        }
}
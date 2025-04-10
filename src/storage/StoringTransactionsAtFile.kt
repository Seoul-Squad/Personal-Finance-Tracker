package storage

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import model.Transaction
import storage.util.LocalDateAdapter
import java.io.File
import java.time.LocalDate

object StoringTransactionsAtFile :TransactionStorage{

    private val file: File = File("transactions.txt")
    private var transactions = mutableListOf<Transaction>()
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
        transactions.removeIf { it.id == transactionId }
        saveToFile()

    }

    override fun load(): List<Transaction> {
        return readTransactionsFromFile().toList()
    }

    private fun readTransactionsFromFile(): MutableList<Transaction> {
        transactions = if (file.exists()) {
            val jsonString = file.readText()
            val type = object : TypeToken<List<Transaction>>() {}.type
            gson.fromJson(jsonString, type)

        } else {
            mutableListOf()

        }
        return transactions

    }
    private fun saveToFile() {
        if (!file.exists()) file.createNewFile()
        val jasonTransactions = gson.toJson(transactions)
        file.writeText(jasonTransactions)

        }
}
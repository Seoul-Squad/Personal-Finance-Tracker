import kotlinx.serialization.json.Json
import model.Transaction
import storage.TransactionStorage
import java.io.File
import java.time.LocalDate

object InFileTransactionStorage : TransactionStorage {
    private val file = File("transactions.txt")
    private var transactions = mutableListOf<Transaction>()
    private val json = Json {
        prettyPrint = true // Optional: Makes JSON readable
        encodeDefaults = true // Ensures default values are serialized
    }

    init {
        transactions = readTransactionsFromFile()
    }

    override fun save(transaction: Transaction) {

        transactions.add(transaction)
        saveToFile()

    }

    override fun edit(transaction: Transaction) {
        transactions.removeIf { it.id == transaction.id }
        transactions.add(transaction)
        saveToFile()
    }

    override fun delete(transactionId: String): Boolean {
        return transactions.removeIf { it.id == transactionId }.also { if (it) saveToFile() }
    }

    override fun load(): List<Transaction> {
        return readTransactionsFromFile().toList()
    }

    private fun readTransactionsFromFile(): MutableList<Transaction> {
        return if (file.exists()) {
            val jsonString = file.readText()
            json.decodeFromString<MutableList<Transaction>>(jsonString)
        } else {
            mutableListOf()
        }
    }

    private fun saveToFile() {
        if (!file.exists()) file.createNewFile()
        val jsonTransactions = json.encodeToString(transactions)
        file.writeText(jsonTransactions)
    }
}
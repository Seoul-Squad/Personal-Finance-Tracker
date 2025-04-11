import kotlinx.serialization.json.Json
import model.Transaction
import storage.TransactionStorage
import java.io.File

object InFileTransactionStorage : TransactionStorage {
    private val file = File("transactions.txt")
    private var transactions = mutableListOf<Transaction>()
    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }

    init {
        transactions = readTransactionsFromFile()
    }

    override fun saveTransaction(transaction: Transaction) {

        transactions.add(transaction)
        saveToFile()

    }

    override fun editTransaction(transaction: Transaction) {
        transactions.removeIf { it.id == transaction.id }
            .also {
                if (it) {
                    transactions.add(transaction)
                    saveToFile()
                }
            }
    }

    override fun deleteTransaction(transactionId: String): Boolean {
        return transactions.removeIf { it.id == transactionId }.also { if (it) saveToFile() }
    }

    override fun loadTransactions(): List<Transaction> {
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
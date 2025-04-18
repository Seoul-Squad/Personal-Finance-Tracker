import kotlinx.serialization.json.Json
import main.java.utils.FILE_NAME
import model.Transaction
import storage.TransactionStorage
import java.io.File

class InFileTransactionStorage : TransactionStorage {
    private val file = File(FILE_NAME)
    private val transactions = mutableListOf<Transaction>()
    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }

    init { transactions.addAll(readTransactionsFromFile()) }

    override fun saveTransaction(transaction: Transaction) {

        transactions.add(transaction)
        saveToFile()

    }

    override fun editTransaction(updatedTransaction: Transaction) {
        transactions.removeIf { it.id == updatedTransaction.id }
            .also {
                if (it) {
                    transactions.add(updatedTransaction)
                    saveToFile()
                }
            }
    }

    override fun deleteTransaction(transactionId: String): Boolean {
        return transactions.removeIf { it.id == transactionId }.also { if (it) saveToFile() }
    }

    override fun loadTransactions(): List<Transaction> {
        return transactions
    }

    private fun readTransactionsFromFile(): List<Transaction> {
        return if (file.exists()) {
            val jsonString = file.readText()
            json.decodeFromString<MutableList<Transaction>>(jsonString)
        } else {
            emptyList()
        }
    }

    private fun saveToFile() {
        if (!file.exists()) file.createNewFile()
        val jsonTransactions = json.encodeToString(transactions)
        file.writeText(jsonTransactions)
    }
}
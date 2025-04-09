package storage

import model.Transaction
import java.io.File

class StorageInFile :TransactionStorage{

    val file: File = File("transactions.txt")
    
    override fun save(transaction: Transaction) {

      // todo save transaction at file 
    }

    override fun edit(transaction: Transaction) {

        // todo save transaction after edite at file
    }

    override fun delete(transactionId: String) {

        // todo after delete transaction update file 
    }

    override fun load(): List<Transaction> {

        return emptyList() //todo get data from file then convert it to list of translations
    }
}
package main.java

import model.MonthlySummary
import model.Transaction
import model.TransactionType
import java.time.format.DateTimeFormatter

class TransactionSummaryGenerator {
    fun generateMonthlySummaries(transactions: List<Transaction>): List<MonthlySummary> {
        val grouped = transactions.groupBy {
            it.date.format(DateTimeFormatter.ofPattern("MM-yyyy"))
        }
        val summaries = grouped.map { (monthYear, transactions) ->
            val totalIncome = transactions
                .filter { it.type == TransactionType.INCOME }
                .sumOf { it.amount ?: 0.0 }
            val totalExpense = transactions
                .filter { it.type == TransactionType.EXPENSE }
                .sumOf { it.amount ?: 0.0 }
            MonthlySummary(monthYear, totalIncome, totalExpense)
        }
        return summaries.sortedBy { it.monthYear }
    }
}
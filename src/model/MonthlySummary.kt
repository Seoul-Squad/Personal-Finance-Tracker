package model

import java.time.LocalDate

data class MonthlySummary(
    val monthYear: LocalDate,
    val income: Double,
    val expense: Double,
)

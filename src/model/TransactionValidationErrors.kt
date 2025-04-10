package model

enum class TransactionValidationErrors(val message: String) {
    INVALID_AMOUNT("Amount must be greater than 0"),
    INVALID_CATEGORY("Category cannot be empty or blank")
}
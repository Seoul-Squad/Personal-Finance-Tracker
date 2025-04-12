package main.java.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.format(format: String): String{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val formatted = this.toJavaLocalDate().format(formatter)
    return formatted
}
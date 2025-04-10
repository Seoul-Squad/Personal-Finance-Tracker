package storage.util

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter : TypeAdapter<LocalDate>() {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun write(out: JsonWriter, value: LocalDate?) {
        out.value(formatter.format(value))
    }

    override fun read(input: JsonReader): LocalDate {
        val dateStr = input.nextString()
        return LocalDate.parse(dateStr, formatter)
    }
}
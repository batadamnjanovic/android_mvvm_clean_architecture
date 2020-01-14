package rs.hc.android.helper

import java.text.SimpleDateFormat
import java.util.*

const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_PATTERN_PRETTY = "dd. MMM yyyy."

class DateHelper {

    companion object {
        fun getReadableDate(dateInput: String, inputPattern: String = DATE_PATTERN, outputPattern: String = DATE_PATTERN_PRETTY): String {
            val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
            val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

            try {
                val date = inputFormat.parse(dateInput)
                return outputFormat.format(date)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            return "" // TODO
        }
    }
}
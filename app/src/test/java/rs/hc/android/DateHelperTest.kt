package rs.hc.android

import org.junit.Assert
import org.junit.Test
import rs.hc.android.helper.DATE_PATTERN
import rs.hc.android.helper.DATE_PATTERN_PRETTY
import java.text.SimpleDateFormat
import java.util.*

class DateHelperTest{

    enum class Pattern(val input: String, val output: String) {
        PATTERN_1(DATE_PATTERN, DATE_PATTERN_PRETTY),
    }

    enum class TestCases(val input: String, val output: String, val patternInput: String, val patternOutput: String) {
        DATE_1("2019-10-28T15:21:14Z", "28. Oct 2019.", Pattern.PATTERN_1.input, Pattern.PATTERN_1.output),
        DATE_2("2020-12-31T15:21:14Z", "31. Dec 2020.", Pattern.PATTERN_1.input, Pattern.PATTERN_1.output)
    }

    @Test
    fun date1() {
        test(TestCases.DATE_1)
    }

    @Test
    fun date2() {
        test(TestCases.DATE_2)
    }

    private fun test(testCase: TestCases) {
        val dateFormatInput = SimpleDateFormat(testCase.patternInput, Locale.US)
        val dateFormatOutput = SimpleDateFormat(testCase.patternOutput, Locale.US)
        val date = dateFormatInput.parse(testCase.input)

        val output = dateFormatOutput.format(date)

        Assert.assertEquals(output, testCase.output)
    }
}
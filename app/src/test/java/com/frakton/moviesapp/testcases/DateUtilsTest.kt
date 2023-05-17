package com.frakton.moviesapp.testcases

import com.frakton.moviesapp.util.formatDateString
import org.junit.Test

class DateUtilsTest {
    @Test
    fun `test formating date string to 'MMM yyyy' format`() {
        val dateToFormat = "2023-05-04"
        val testDate = dateToFormat.formatDateString()
        val formattedDate = "May 2023"
        assert(testDate == formattedDate)
    }
}
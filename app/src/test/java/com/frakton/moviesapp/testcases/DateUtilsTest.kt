package com.frakton.moviesapp.testcases

import com.frakton.moviesapp.util.formatDateString
import com.frakton.moviesapp.util.getYearFromDate
import org.junit.Test

class DateUtilsTest {
    @Test
    fun `test formatting date string to 'MMM yyyy' format`() {
        val dateToFormat = "2023-05-17"
        val testDate = dateToFormat.formatDateString()
        val formattedDate = "May 2023"
        assert(testDate == formattedDate)
    }

    @Test
    fun `test getting year from date string`(){
        val dateToFormat = "2023-05-17"
        val testDate = dateToFormat.getYearFromDate()
        val formattedDate = "2023"
        assert(testDate == formattedDate)
    }
}
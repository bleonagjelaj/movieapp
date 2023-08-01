package com.bleonahasanaj.moviesapp.testcases

import com.bleonahasanaj.moviesapp.util.formatDateString
import com.bleonahasanaj.moviesapp.util.getYearFromDate
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
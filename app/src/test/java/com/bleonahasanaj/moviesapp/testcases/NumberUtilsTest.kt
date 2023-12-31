package com.bleonahasanaj.moviesapp.testcases

import com.bleonahasanaj.moviesapp.util.roundToOneDecimalPlace
import org.junit.Test

class NumberUtilsTest {
    @Test
    fun `test rounding number to one decimal place`() {
        val numberToRound = 3.2323F
        val testNumber = numberToRound.roundToOneDecimalPlace()
        val roundedNumber = 3.2F
        assert(testNumber == roundedNumber)
    }
}
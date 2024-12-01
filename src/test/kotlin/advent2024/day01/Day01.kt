package advent2024.day01

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestDay1 {

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile("day01", "inputTest.txt")
        assertEquals(11, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile("day01", "actualInput.txt")
        assertEquals(1834060, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile("day01", "inputTest.txt")
        assertEquals(31, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile("day01", "actualInput.txt")
        assertEquals(21607792, solveStep2(input))
    }
}
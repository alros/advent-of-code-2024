package advent2024.day04

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay04 {

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile("day04", "inputTest.txt")
        Assertions.assertEquals(18, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile("day04", "actualInput.txt")
        Assertions.assertEquals(2662, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile("day04", "inputTest.txt")
        Assertions.assertEquals(9, solveStep2(input))
    }

    @Test
    fun testMyExamplePhase2() {
        for (i in 1..0) {
            val input = Utils.readFile("day04", "myInputTest$i.txt")
            Assertions.assertEquals(1, solveStep2(input))
        }
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile("day04", "actualInput.txt")
        Assertions.assertEquals(2034, solveStep2(input))//low
    }

}
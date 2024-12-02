package advent2024.day02

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay02 {

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile("day02", "inputTest.txt")
        Assertions.assertEquals(2, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile("day02", "actualInput.txt")
        Assertions.assertEquals(202, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile("day02", "inputTest.txt")
        Assertions.assertEquals(4, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile("day02", "actualInput.txt")
        Assertions.assertEquals(271, solveStep2(input))
    }

}
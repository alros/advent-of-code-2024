package advent2024.day05

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay05 {

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile("day05", "inputTest.txt")
        Assertions.assertEquals(143, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile("day05", "actualInput.txt")
        Assertions.assertEquals(4281, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile("day05", "inputTest.txt")
        Assertions.assertEquals(123, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile("day05", "actualInput.txt")
        Assertions.assertEquals(5466, solveStep2(input))
    }

}
package advent2024.day06

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay06 {

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile("day06", "inputTest.txt")
        Assertions.assertEquals(41, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile("day06", "actualInput.txt")
        Assertions.assertEquals(4826, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile("day06", "inputTest.txt")
        Assertions.assertEquals(6, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile("day06", "actualInput.txt")
        Assertions.assertEquals(1721, solveStep2(input))
    }

}
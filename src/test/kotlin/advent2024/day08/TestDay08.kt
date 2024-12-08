package advent2024.day08

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay08 {

    private val folder = "day08"

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(14, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(357, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(34, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(1266, solveStep2(input))
    }

}
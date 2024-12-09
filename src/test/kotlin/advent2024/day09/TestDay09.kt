package advent2024.day09

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay09 {

    private val folder = "day09"

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(1928, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(6398608069280, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(2858, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(6427437134372, solveStep2(input))
    }

}
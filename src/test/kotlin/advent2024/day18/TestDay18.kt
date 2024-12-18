package advent2024.day18

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay18 {

    private val folder = "day18"

    @Test
    fun testExample1Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(22, solveStep1(input, Pair(7, 7), 12))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(374, solveStep1(input, Pair(71, 71), 1024))
    }

    @Test
    fun testExample1Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals("6,1", solveStep2(input, Pair(7, 7), 12))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals("30,12", solveStep2(input, Pair(71, 71), 1024))
    }

}
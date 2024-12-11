package advent2024.day11

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay11 {

    private val folder = "day11"

    @Test
    fun testExamplePhase1Short() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(22, solveStep1(input, 6))
    }

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(55312, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(213625, solveStep1(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(252442982856820, solveStep2(input))
    }

}
package advent2024.day07

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay07 {

    private val folder = "day07"

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(3749, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(7885693428401, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(11387, solveStep2(input))
    }

    @Test
    fun testMyExamplePhase2() {
        val input = "1: 999 999 999 999 999 999 999 1"
        Assertions.assertEquals(0, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(348360680516005, solveStep2(input))
    }

}
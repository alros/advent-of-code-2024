package advent2024.day03

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay03 {

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile("day03", "inputTest1.txt")
        Assertions.assertEquals(161, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile("day03", "actualInput.txt")
        Assertions.assertEquals(165225049, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile("day03", "inputTest2.txt")
        Assertions.assertEquals(48, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile("day03", "actualInput.txt")
        Assertions.assertEquals(108830766, solveStep2(input))
    }

}
package advent2024.day14

import advent2024.day11.solveStep1
import advent2024.day11.solveStep2
import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay14 {

    private val folder = "day14"


    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(12, solveStep1(input, Pair(11, 7)))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(215987200, solveStep1(input, Pair(101, 103)))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(8050, solveStep2(input, Pair(101, 103)))
    }

}
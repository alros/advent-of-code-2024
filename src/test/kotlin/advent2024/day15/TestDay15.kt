package advent2024.day15

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay15 {

    private val folder = "day15"


    @Test
    fun testExampleSmallPhase1() {
        val input = Utils.readFile(folder, "inputTestSmall.txt")
        Assertions.assertEquals(2028, solveStep1(input))
    }


    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(10092, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(1478649, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(9021, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(1495455, solveStep2(input, render=false))
    }

}
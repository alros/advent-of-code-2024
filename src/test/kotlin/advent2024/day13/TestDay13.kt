package advent2024.day13

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay13 {

    private val folder = "day13"

    @Test
    fun testExample1Phase1() {
        val input = Utils.readFile(folder, "inputTest1.txt")
        Assertions.assertEquals(280, solveStep1(input))
    }

    @Test
    fun testExample2Phase1() {
        val input = Utils.readFile(folder, "inputTest2.txt")
        Assertions.assertEquals(0, solveStep1(input))
    }

    @Test
    fun testExample3Phase1() {
        val input = Utils.readFile(folder, "inputTest3.txt")
        Assertions.assertEquals(200, solveStep1(input))
    }

    @Test
    fun testExample4Phase1() {
        val input = Utils.readFile(folder, "inputTest4.txt")
        Assertions.assertEquals(0, solveStep1(input))
    }

    @Test
    fun testExamplePhase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(480, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(29517, solveStep1(input))
    }

    @Test
    fun testExamplePhase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(875318608908, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(103570327981381, solveStep2(input))
    }

}
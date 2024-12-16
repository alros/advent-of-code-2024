package advent2024.day16

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay16 {

    private val folder = "day16"

    @Test
    fun testExample1Phase1() {
        val input = Utils.readFile(folder, "inputTest1.txt")
        Assertions.assertEquals(7036, solveStep1(input))
    }

    @Test
    fun testExample2Phase1() {
        val input = Utils.readFile(folder, "inputTest2.txt")
        Assertions.assertEquals(11048, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(78428, solveStep1(input))
    }


    @Test
    fun testExample1Phase2() {
        val input = Utils.readFile(folder, "inputTest1.txt")
        Assertions.assertEquals(45, solveStep2(input))
    }

    @Test
    fun testExample2Phase2() {
        val input = Utils.readFile(folder, "inputTest2.txt")
        Assertions.assertEquals(64, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(78428, solveStep2(input))
    }

}
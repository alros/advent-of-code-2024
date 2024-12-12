package advent2024.day12

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay12 {

    private val folder = "day12"

    @Test
    fun testExample1Phase1() {
        val input = Utils.readFile(folder, "inputTest1.txt")
        Assertions.assertEquals(140, solveStep1(input))
    }

    @Test
    fun testExample2Phase1() {
        val input = Utils.readFile(folder, "inputTest2.txt")
        Assertions.assertEquals(772, solveStep1(input))
    }

    @Test
    fun testExample3Phase1() {
        val input = Utils.readFile(folder, "inputTest3.txt")
        Assertions.assertEquals(1930, solveStep1(input))
    }

    @Test
    fun testExample6Phase1() {
        val input = Utils.readFile(folder, "inputTest6.txt")
        Assertions.assertEquals(186, solveStep1(input))
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(1457298, solveStep1(input))
    }

    @Test
    fun testExample1Phase2() {
        val input = Utils.readFile(folder, "inputTest1.txt")
        Assertions.assertEquals(80, solveStep2(input))
    }

    @Test
    fun testExample2Phase2() {
        val input = Utils.readFile(folder, "inputTest2.txt")
        Assertions.assertEquals(436, solveStep2(input))
    }

    @Test
    fun testExample3Phase2() {
        val input = Utils.readFile(folder, "inputTest3.txt")
        Assertions.assertEquals(1206, solveStep2(input))
    }

    @Test
    fun testExample4Phase2() {
        val input = Utils.readFile(folder, "inputTest4.txt")
        Assertions.assertEquals(946, solveStep2(input))
    }

    @Test
    fun testExample5Phase2() {
        val input = Utils.readFile(folder, "inputTest5.txt")
        Assertions.assertEquals(164, solveStep2(input))
    }

    @Test
    fun testExample7Phase2() {
        val input = Utils.readFile(folder, "inputTest7.txt")
        Assertions.assertEquals(1676, solveStep2(input))
    }

    @Test
    fun testExample8Phase2() {
        val input = Utils.readFile(folder, "inputTest8.txt")
        Assertions.assertEquals(180, solveStep2(input))
    }

    @Test
    fun testExample9Phase2() {
        val input = Utils.readFile(folder, "inputTest9.txt")
        Assertions.assertEquals(180, solveStep2(input))
    }

    @Test
    fun testExample10Phase2() {
        val input = Utils.readFile(folder, "inputTest10.txt")
        Assertions.assertEquals(307122, solveStep2(input))
    }

    @Test
    fun testExampleMPhase2() {
        val input = Utils.readFile(folder, "inputTestM.txt")
        Assertions.assertEquals(964, solveStep2(input))
    }

    @Test
    fun testExampleEPhase2() {
        val input = Utils.readFile(folder, "inputTestE.txt")
        Assertions.assertEquals(716, solveStep2(input))
    }

    @Test
    fun testExampleRPhase2() {
        val input = Utils.readFile(folder, "inputTestR.txt")
        Assertions.assertEquals(710, solveStep2(input))
    }

    @Test
    fun testExampleYPhase2() {
        val input = Utils.readFile(folder, "inputTestY.txt")
        Assertions.assertEquals(1016, solveStep2(input))
    }

    @Test
    fun testExampleCPhase2() {
        val input = Utils.readFile(folder, "inputTestC.txt")
        Assertions.assertEquals(956, solveStep2(input))
    }


    @Test
    fun testExample13Phase2() {
        val input = Utils.readFile(folder, "inputTest13.txt")
        Assertions.assertEquals(60, solveStep2(input))
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(921636, solveStep2(input))
    }

}
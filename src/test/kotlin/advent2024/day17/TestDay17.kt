package advent2024.day17

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay17 {

    private val folder = "day17"

    @Test
    fun testMini1Phase1(){
        val input = Utils.readFile(folder, "inputTestMini1.txt")
        val cpu = solveStep1(input)
        Assertions.assertEquals(1, cpu.regB())
    }

    @Test
    fun testMini2Phase1(){
        val input = Utils.readFile(folder, "inputTestMini2.txt")
        val cpu = solveStep1(input)
        Assertions.assertEquals("0,1,2", cpu.output())
    }

    @Test
    fun testMini3Phase1(){
        val input = Utils.readFile(folder, "inputTestMini3.txt")
        val cpu = solveStep1(input)
        Assertions.assertEquals("4,2,5,6,7,7,7,7,3,1,0", cpu.output())
        Assertions.assertEquals(0, cpu.regA())
    }

    @Test
    fun testMini4Phase1(){
        val input = Utils.readFile(folder, "inputTestMini4.txt")
        val cpu = solveStep1(input)
        Assertions.assertEquals(26, cpu.regB())
    }

    @Test
    fun testMini5Phase1(){
        val input = Utils.readFile(folder, "inputTestMini5.txt")
        val cpu = solveStep1(input)
        Assertions.assertEquals(44354, cpu.regB())
    }

    @Test
    fun testExample1Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        val cpu = solveStep1(input)
        Assertions.assertEquals("4,6,3,5,6,3,5,2,1,0", cpu.output())
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        val cpu = solveStep1(input)
        Assertions.assertEquals("6,0,6,3,0,2,3,1,6", cpu.output())
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
package advent2024.day20

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay20 {

    private val folder = "day20"

    @Test
    fun testExample2Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(14, solveStep1(input) { g -> g == 2 })
    }

    @Test
    fun testExample4Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(14, solveStep1(input) { g -> g == 4 })
    }

    @Test
    fun testExample6Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(2, solveStep1(input) { g -> g == 6 })
    }

    @Test
    fun testExample8Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(4, solveStep1(input) { g -> g == 8 })
    }

    @Test
    fun testExample10Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(2, solveStep1(input) { g -> g == 10 })
    }

    @Test
    fun testExample12Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(3, solveStep1(input) { g -> g == 12 })
    }

    @Test
    fun testExample20Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(1, solveStep1(input) { g -> g == 20 })
    }

    @Test
    fun testExample36Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(1, solveStep1(input) { g -> g == 36 })
    }

    @Test
    fun testExample38Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(1, solveStep1(input) { g -> g == 38 })
    }

    @Test
    fun testExample40Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(1, solveStep1(input) { g -> g == 40 })
    }

    @Test
    fun testExample64Phase1() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(1, solveStep1(input) { g -> g == 64 })
    }

    @Test
    fun testPhase1() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(1387, solveStep1(input) { g -> g >= 100 })
    }

    @Test
    fun testExample50Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(32, solveStep2(input) { g -> g == 50 })
    }

    @Test
    fun testExample52Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(31, solveStep2(input) { g -> g == 52 })
    }

    @Test
    fun testExample54Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(29, solveStep2(input) { g -> g == 54 })
    }

    @Test
    fun testExample56Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(39, solveStep2(input) { g -> g == 56 })
    }

    @Test
    fun testExample58Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(25, solveStep2(input) { g -> g == 58 })
    }

    @Test
    fun testExample60Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(23, solveStep2(input) { g -> g == 60 })
    }

    @Test
    fun testExample62Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(20, solveStep2(input) { g -> g == 62 })
    }


    @Test
    fun testExample64Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(19, solveStep2(input) { g -> g == 64 })
    }

    @Test
    fun testExample66Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(12, solveStep2(input) { g -> g == 66 })
    }

    @Test
    fun testExample68Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(14, solveStep2(input) { g -> g == 68 })
    }

    @Test
    fun testExample70Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(12, solveStep2(input) { g -> g == 70 })
    }

    @Test
    fun testExample72Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(22, solveStep2(input) { g -> g == 72 })
    }

    @Test
    fun testExample74Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(4, solveStep2(input) { g -> g == 74 })
    }

    @Test
    fun testExample76Phase2() {
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(3, solveStep2(input) { g -> g == 76 })
    }

    @Test
    fun testPhase2() {
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(1015092, solveStep2(input) { g -> g >= 100 })
    }

}
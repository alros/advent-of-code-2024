package advent2024.day19

import advent2024.utils.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentHashMap

class TestDay19 {

    private val folder = "day19"

    @Test
    fun testExample1Phase1() {
        val cache = ConcurrentHashMap<String, Long>()
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(6, solveStep1(input, cache))
    }

    @Test
    fun testPhase1() {
        val cache = ConcurrentHashMap<String, Long>()
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(278, solveStep1(input, cache))
    }

    @Test
    fun testExample1Phase2() {
        val cache = ConcurrentHashMap<String, Long>()
        val input = Utils.readFile(folder, "inputTest.txt")
        Assertions.assertEquals(16, solveStep2(input, cache))
    }

    @Test
    fun testPhase2() {
        val cache = ConcurrentHashMap<String, Long>()
        val input = Utils.readFile(folder, "actualInput.txt")
        Assertions.assertEquals(569808947758890L, solveStep2(input, cache))
    }

}
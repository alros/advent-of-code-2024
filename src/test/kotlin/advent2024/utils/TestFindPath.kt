package advent2024.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestFindPath {

    @Test
    fun testPopulateDistancesFromStartToEnd() {
        val mx = Integer.MAX_VALUE
        val map = arrayOf(
            intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1),
            intArrayOf(-1, mx, mx, mx, mx, mx, mx, mx, -1),
            intArrayOf(-1, -1, -1, mx, -1, -1, -1, -1, -1),
            intArrayOf(-1, mx, mx, mx, -1 - 1, -1, -1, -1),
            intArrayOf(-1, mx, -1, mx, -1 - 1, -1, -1, -1),
            intArrayOf(-1, mx, -1, mx, -1 - 1, -1, -1, -1),
            intArrayOf(-1, -1, -1, -1, -1 - 1, -1, -1, -1),
        )
        Assertions.assertEquals(8, FindPath.populateDistancesFromStartToEnd(map, Pair(1, 1), Pair(5, 1)))
        Assertions.assertEquals(0, map[1][1])
        Assertions.assertEquals(6, map[1][7])
        Assertions.assertEquals(8, map[5][1])
    }
}
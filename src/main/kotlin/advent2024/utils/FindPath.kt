package advent2024.utils

class FindPath {
    companion object {
        fun populateDistancesFromStartToEnd(
            map: Array<IntArray>,
            start: Pair<Int, Int>,
            end: Pair<Int, Int>,
            wall: Int = -1
        ): Int {
            val queue = ArrayDeque<Pair<Int, Int>>(listOf(start))
            map[start.first][start.second] = 0
            val moves = listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))
            while (!queue.isEmpty()) {
                val curPos = queue.removeFirst()
                if (curPos == end) {
                    return map[curPos.first][curPos.second]
                }
                for (move in moves) {
                    val nextPos = Pair(curPos.first + move.first, curPos.second + move.second)
                    if (nextPos.first in map.indices && nextPos.second in map[0].indices) {
                        if (map[nextPos.first][nextPos.second] != wall &&
                            map[nextPos.first][nextPos.second] > map[curPos.first][curPos.second] + 1
                        ) {
                            map[nextPos.first][nextPos.second] = map[curPos.first][curPos.second] + 1
                            queue.addLast(nextPos)
                        }
                    }
                }
            }
            return -1
        }
    }
}
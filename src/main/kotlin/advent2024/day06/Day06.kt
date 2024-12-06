package advent2024.day06

enum class Direction(symbol: Char, change: Pair<Int, Int>) {
    DIRECTION_N('^', Pair(-1, 0)),
    DIRECTION_S('v', Pair(1, 0)),
    DIRECTION_E('>', Pair(0, -1)),
    DIRECTION_W('<', Pair(0, 1));

    val change = change
    val symbol = symbol

    fun rotate(): Direction {
        return when (this) {
            DIRECTION_E -> DIRECTION_N
            DIRECTION_N -> DIRECTION_W
            DIRECTION_W -> DIRECTION_S
            DIRECTION_S -> DIRECTION_E
        }
    }

    companion object FromSymbol {
        fun fromSymbol(symbol: Char): Direction {
            return values().first { it.symbol == symbol }
        }

        fun isDirection(symbol: Char): Boolean {
            return values().map { it.symbol }.contains(symbol)
        }
    }
}

fun solveStep1(input: String): Int {
    val map = convertInput(input)
    val initialPosition = findTheGuard(map)
    val initialDirection = Direction.fromSymbol(map[initialPosition.first][initialPosition.second])
    map[initialPosition.first][initialPosition.second] = initialDirection.symbol
    return 1 + explore(map, initialPosition, initialDirection)
}

fun solveStep2(input: String): Int {
    val map = convertInput(input)
    val initialPosition = findTheGuard(map)
    val initialDirection = Direction.fromSymbol(map[initialPosition.first][initialPosition.second])
    map[initialPosition.first][initialPosition.second] = initialDirection.symbol
    var obstacles = 0
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == '.') {
                val cloneMap = cloneMap(map)
                cloneMap[i][j] = '#'
                try {
                    explore(cloneMap, initialPosition, initialDirection)
                } catch (e: StackOverflowError) {
                    obstacles++
                }
            }
        }
    }
    return obstacles
}

private fun convertInput(input: String): List<CharArray> {
    return input.split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .map { it.toCharArray() }
        .toList()
}

private fun findTheGuard(map: List<CharArray>): Pair<Int, Int> {
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (Direction.isDirection(map[i][j])) {
                return Pair(i, j)
            }
        }
    }
    throw RuntimeException("Guard not found")
}

private fun explore(
    map: List<CharArray>,
    position: Pair<Int, Int>,
    direction: Direction,
): Int {
    var newPosition = Pair(position.first + direction.change.first, position.second + direction.change.second)
    while (newPosition.first < map.size && newPosition.second < map[0].size &&
        newPosition.first >= 0 && newPosition.second >= 0
    ) {
        if (map[newPosition.first][newPosition.second] == '#') {
            return explore(map, position, direction.rotate())
        }
        var explored = 0
        if (map[newPosition.first][newPosition.second] == '.') {
            map[newPosition.first][newPosition.second] = direction.symbol
            explored++
        }
        return explored + explore(map, newPosition, direction)
    }
    return 0
}

fun addObstacle(map: List<CharArray>, position: Pair<Int, Int>, direction: Direction): Collection<Pair<Int, Int>> {
    val rotated = direction.rotate()
    return if (map[position.first + rotated.change.first][position.second + rotated.change.second] == rotated.symbol) {
        setOf(Pair(position.first + direction.change.first, position.second + direction.change.second))
    } else {
        emptySet()
    }
}

private fun cloneMap(map: List<CharArray>): List<CharArray> {
    val cloneMap = mutableListOf<CharArray>()
    for (l in map) {
        cloneMap.add(l.clone())
    }
    return cloneMap
}
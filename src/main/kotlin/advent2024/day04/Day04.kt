package advent2024.day04


val XMAS = mapOf(
    'M' to 'A',
    'A' to 'S'
)

val DIRECTION_N = Pair(-1, 0)
val DIRECTION_NE = Pair(-1, 1)
val DIRECTION_E = Pair(0, 1)
val DIRECTION_SE = Pair(1, 1)
val DIRECTION_S = Pair(1, 0)
val DIRECTION_SW = Pair(1, -1)
val DIRECTION_W = Pair(0, -1)
val DIRECTION_NW = Pair(-1, -1)

val DIRECTIONS = listOf(
    DIRECTION_N,
    DIRECTION_NE,
    DIRECTION_E,
    DIRECTION_SE,
    DIRECTION_S,
    DIRECTION_SW,
    DIRECTION_W,
    DIRECTION_NW
)

fun solveStep1(input: String): Int {
    val lines = convertInput(input)
    return countXmas(lines)
}

fun solveStep2(input: String): Int {
    val lines = convertInput(input)
    return countMasAsX(lines)
}

private fun convertInput(input: String): List<CharArray> {
    return input.split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .map { line -> line.toCharArray() }
        .toList()
}

private fun countXmas(lines: List<CharArray>): Int {
    var acc = 0
    for (i in lines.indices) {
        for (j in lines[i].indices) {
            if (lines[i][j] != 'X') {
                continue
            }
            acc += DIRECTIONS.stream()
                .map { searchForLetters('M', it, lines, i + it.first, j + it.second) }
                .filter { it }
                .count()
                .toInt()
        }
    }
    return acc
}

fun countMasAsX(lines: List<CharArray>): Int {
    var acc = 0
    for (i in lines.indices) {
        for (j in lines[i].indices) {
            if (lines[i][j] != 'M') {
                continue
            }
            acc += if (searchForLetters('A', DIRECTION_SE, lines, i + DIRECTION_SE.first, j + DIRECTION_SE.second) &&
                (searchForLetters('M', DIRECTION_SW, lines, i, j + 2) ||
                        searchForLetters('M', DIRECTION_NE, lines, i + 2, j))
            ) 1 else 0

            acc += if (searchForLetters('A', DIRECTION_NW, lines, i + DIRECTION_NW.first, j + DIRECTION_NW.second) &&
                (searchForLetters('M', DIRECTION_SW, lines, i - 2, j) ||
                        searchForLetters('M', DIRECTION_NE, lines, i, j - 2))
            ) 1 else 0
        }
    }
    return acc
}

private fun searchForLetters(c: Char?, direction: Pair<Int, Int>, lines: List<CharArray>, i: Int, j: Int): Boolean {
    if (c == null) {
        return true
    }
    if (i < 0 || j < 0 || i >= lines.size || j >= lines[0].size) {
        return false
    }
    if (lines[i][j] != c) {
        return false
    }
    return searchForLetters(XMAS[c], direction, lines, i + direction.first, j + direction.second)
}
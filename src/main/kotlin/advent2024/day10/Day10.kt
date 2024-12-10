package advent2024.day10

private const val PEAK = 10

fun solveStep1(input: String): Int {
    val map = convertInput(input)
    var trails = 0
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == 0) {
                trails += explore(map, Pair(i, j), 1).count()
            }
        }
    }
    return trails
}

fun solveStep2(input: String): Int {
    val map = convertInput(input)
    var trails = 0
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == 0) {
                trails += exploreAll(map, Pair(i, j), 1)
            }
        }
    }
    return trails
}

private fun convertInput(input: String): MutableList<IntArray> {
    return input.split("\n").stream()
        .filter { it.isNotEmpty() }
        .map {
            it.split("").stream()
                .filter { v -> v.isNotEmpty() }
                .mapToInt { v -> v.toInt() }
                .toArray()
        }.toList()
}

private fun explore(map: MutableList<IntArray>, start: Pair<Int, Int>, next: Int): Set<Pair<Int, Int>> {
    val candidates = mutableListOf<Pair<Int, Int>>()
    if (next == PEAK) {
        return setOf(start)
    }
    candidates.addAll(checkPosition(map, Pair(start.first - 1, start.second), next))
    candidates.addAll(checkPosition(map, Pair(start.first, start.second - 1), next))
    candidates.addAll(checkPosition(map, Pair(start.first + 1, start.second), next))
    candidates.addAll(checkPosition(map, Pair(start.first, start.second + 1), next))
    return candidates.stream()
        .map { explore(map, it, next + 1) }
        .flatMap { it.stream() }
        .toList()
        .toSet()
}

private fun exploreAll(map: MutableList<IntArray>, start: Pair<Int, Int>, next: Int): Int {
    val candidates = mutableListOf<Pair<Int, Int>>()
    if (next == PEAK) {
        return 1
    }
    candidates.addAll(checkPosition(map, Pair(start.first - 1, start.second), next))
    candidates.addAll(checkPosition(map, Pair(start.first, start.second - 1), next))
    candidates.addAll(checkPosition(map, Pair(start.first + 1, start.second), next))
    candidates.addAll(checkPosition(map, Pair(start.first, start.second + 1), next))
    return candidates.stream()
        .map { exploreAll(map, it, next + 1) }
        .reduce(0) { a: Int, b: Int -> a + b }
}

private fun checkPosition(map: MutableList<IntArray>, pos: Pair<Int, Int>, next: Int): List<Pair<Int, Int>> =
    if (isWithinBorders(pos, map) && map[pos.first][pos.second] == next) listOf(pos) else emptyList()

private fun isWithinBorders(pos: Pair<Int, Int>, map: MutableList<IntArray>) =
    pos.first >= 0 && pos.first < map.size && pos.second >= 0 && pos.second < map[0].size

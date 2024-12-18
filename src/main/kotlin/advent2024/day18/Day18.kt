package advent2024.day18


fun solveStep1(input: String, size: Pair<Int, Int>, steps: Int): Int {
    var list = convertInput(input)
    var map = Map(Array(size.first) { IntArray(size.second) { Integer.MAX_VALUE } })
    letThemFall(list, 0..<steps, map)
    return findShortestPath(map, Pair(0, 0), Pair(size.first - 1, size.second - 1))
}

fun solveStep2(input: String, size: Pair<Int, Int>, initialSteps: Int): String {
    var steps = initialSteps
    var cost = solveStep1(input, size, steps)
    var list = convertInput(input)
    while (cost > 0) {
        steps++
        cost = solveStep1(input, size, steps)
    }
    return "${list[steps - 1].second},${list[steps - 1].first}"
}

private fun letThemFall(list: List<Pair<Int, Int>>, steps: IntRange, map: Map) {
    for (i in steps) {
        map.block(list[i])
    }
}

class Map(val map: Array<IntArray>) {

    fun block(p: Pair<Int, Int>) {
        map[p.first][p.second] = -1
    }

    fun traverse(pos: Pair<Int, Int>, cost: Int): Boolean {
        if (pos.first >= map.size || pos.first < 0 || pos.second >= map[0].size || pos.second < 0) {
            return false
        }
        if (map[pos.first][pos.second] < 0 || map[pos.first][pos.second] < cost) {
            return false
        }
        map[pos.first][pos.second] = cost
        return true
    }
}

enum class Move(val change: Pair<Int, Int>) {
    N(Pair(-1, 0)),
    W(Pair(0, 1)),
    S(Pair(1, 0)),
    E(Pair(0, -1));

    fun move(pos: Pair<Int, Int>) = Pair(pos.first + change.first, pos.second + change.second)
}

private fun findShortestPath(map: Map, start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
    var positions = setOf(start)
    map.traverse(start, 0)
    var cost = 0
    while (true) {
        cost++
        var newPositions = mutableSetOf<Pair<Int, Int>>()
        for (currentPos in positions) {
            newPositions.addAll(Move.entries.stream()
                .map { it.move(currentPos) }
                .filter { map.traverse(it, cost) }
                .toList())
        }
        if (newPositions.isEmpty()) {
            return -1 // no paths found
        }
        if (newPositions.stream().filter { it == end }.findAny().isPresent) {
            return cost
        }
        positions = newPositions
    }
}


private fun convertInput(input: String): List<Pair<Int, Int>> {
    return input.split("\n").stream().filter { it.isNotEmpty() }.map {
        var (a, b) = it.split(",")
        Pair(b.toInt(), a.toInt())
    }.toList()
}

private fun printMap(map: Array<CharArray>) {
    for (line in map) {
        println(String(line))
    }
}
package advent2024.day08

fun solveStep1(input: String): Int {
    val map = convertInput(input)
    return map.map.values.stream()
        .map { findAntinodes(it, map.size) }
        .flatMap { it.stream() }
        .toList()
        .toSet()
        .size
}

fun solveStep2(input: String): Int {
    val map = convertInput(input)
    val antennas = map.map.values.stream()
        .flatMap { it.stream() }
        .toList()
        .toSet()
    val antinodes = map.map.values.stream()
        .map { findAntinodes(it, map.size, true) }
        .flatMap { it.stream() }
        .toList()
        .toMutableSet()
    antinodes.addAll(antennas)
    return antinodes.size
}

class AntennasMap(val map: Map<Char, Set<Pair<Int, Int>>>, val size: Pair<Int, Int>)

private fun convertInput(input: String): AntennasMap {
    val map = input.split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .map { it.toCharArray() }
        .toList()
    val antennas = mutableMapOf<Char, MutableSet<Pair<Int, Int>>>()
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] != '.') {
                val list = antennas.getOrDefault(map[i][j], mutableSetOf())
                list.add(Pair(i, j))
                antennas[map[i][j]] = list
            }
        }
    }
    return AntennasMap(antennas, Pair(map.size, map[0].size))
}

private fun findAntinodes(
    antennasSet: Set<Pair<Int, Int>>,
    mapSize: Pair<Int, Int>,
    withHarmonics: Boolean = false
): Set<Pair<Int, Int>> {
    val antennas = antennasSet.toList()
    val antinodes = mutableSetOf<Pair<Int, Int>>()
    for (i in 0..antennas.size - 2) {
        for (j in i + 1..<antennas.size) {
            val dI = antennas[i].first - antennas[j].first
            val dJ = antennas[i].second - antennas[j].second
            var mul = 1
            do {
                val pair1 = Pair(antennas[i].first + dI * mul, antennas[i].second + dJ * mul)
                val pair2 = Pair(antennas[j].first - dI * mul, antennas[j].second - dJ * mul)
                if (withinMap(pair1, mapSize)) {
                    antinodes.add(pair1)
                }
                if (withinMap(pair2, mapSize)) {
                    antinodes.add(pair2)
                }
                mul++
            } while (withHarmonics && (withinMap(pair1, mapSize) || withinMap(pair2, mapSize)))
        }
    }
    return antinodes
}

private fun withinMap(pair: Pair<Int, Int>, size: Pair<Int, Int>): Boolean {
    return pair.first >= 0 && pair.second >= 0 && pair.first < size.first && pair.second < size.second
}

package advent2024.day12

fun solveStep1(input: String): Long {
    val map = convertInput(input)
    val perimeterAreaList: List<Pair<Int, Int>> = explore(map)
    var cost = 0L
    for (perimeterArea in perimeterAreaList) {
        cost += perimeterArea.first * perimeterArea.second
    }
    return cost
}

fun solveStep2(input: String): Long {
    val map = convertInput(input)
    val perimeterAreaList: List<Pair<Int, Int>> = explore(map, useSides = true)
    var cost = 0L
    for (perimeterArea in perimeterAreaList) {
        cost += perimeterArea.first * perimeterArea.second
    }
    return cost
}


private fun convertInput(input: String): List<CharArray> {
    return input.split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .map { it.toCharArray() }
        .toList()
}

private fun explore(map: List<CharArray>, useSides: Boolean = false): List<Pair<Int, Int>> {
    val values = mutableListOf<Pair<Int, Int>>()
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j].isUpperCase()) {
                values.add((if (useSides) ::perimeterUseSides else ::explore)(map, Pair(i, j), map[i][j]))
            }
        }
    }
    return values
}

enum class Movement(val movement: Pair<Int, Int>) {
    NORTH(Pair(-1, 0)),
    NORTH_EAST(Pair(-1, 1)),
    EAST(Pair(0, 1)),
    SOUTH_EAST(Pair(1, 1)),
    SOUTH(Pair(1, 0)),
    SOUTH_WEST(Pair(1, -1)),
    WEST(Pair(0, -1)),
    NORTH_WEST(Pair(-1, -1));

    fun move(start: Pair<Int, Int>): Pair<Int, Int> = Pair(movement.first + start.first, movement.second + start.second)
}

private fun explore(map: List<CharArray>, pos: Pair<Int, Int>, plant: Char): Pair<Int, Int> {
    var perimeter = 4
    var area = 1
    map[pos.first][pos.second] = map[pos.first][pos.second].lowercaseChar()
    for (movement in listOf(Movement.NORTH, Movement.WEST, Movement.SOUTH, Movement.EAST)) {
        val nextPos = movement.move(pos)
        if (withinMap(map, nextPos)) {
            if (map[nextPos.first][nextPos.second] == plant) {
                val (p, a) = explore(map, nextPos, plant)
                perimeter = perimeter - 1 + p
                area += a
            } else if (map[nextPos.first][nextPos.second] == plant.lowercaseChar()) {
                perimeter--
            }
        }
    }
    return Pair(perimeter, area)
}

private fun perimeterUseSides(map: List<CharArray>, pos: Pair<Int, Int>, plant: Char): Pair<Int, Int> {
    var cornersR = 0
    var area = 1
    map[pos.first][pos.second] = map[pos.first][pos.second].lowercaseChar()
    for (movement in listOf(Movement.NORTH, Movement.WEST, Movement.SOUTH, Movement.EAST)) {
        val nextPos = movement.move(pos)
        if (withinMap(map, nextPos)) {
            if (map[nextPos.first][nextPos.second] == plant) {
                val (c, a) = perimeterUseSides(map, nextPos, plant)
                cornersR += c
                area += a
            }
        }
    }

    var corners = 0

    val north = Movement.NORTH.move(pos)
    val northEast = Movement.NORTH_EAST.move(pos)
    val east = Movement.EAST.move(pos)
    val southEast = Movement.SOUTH_EAST.move(pos)
    val south = Movement.SOUTH.move(pos)
    val southWest = Movement.SOUTH_WEST.move(pos)
    val west = Movement.WEST.move(pos)
    val northWest = Movement.NORTH_WEST.move(pos)

    corners += testCornerT(map, north, east, plant)
    corners += testCornerT(map, east, south, plant)
    corners += testCornerT(map, south, west, plant)
    corners += testCornerT(map, west, north, plant)

    corners += cornerI(map, north, east, northEast, plant)
    corners += cornerI(map, east, south, southEast, plant)
    corners += cornerI(map, south, west, southWest, plant)
    corners += cornerI(map, west, north, northWest, plant)

    return Pair(corners + cornersR, area)
}

private fun withinMap(map: List<CharArray>, coordinates: Pair<Int, Int>): Boolean =
    coordinates.first >= 0 && coordinates.first < map.size && coordinates.second >= 0 && coordinates.second < map[0].size

private fun outsideMap(map: List<CharArray>, coordinates: Pair<Int, Int>): Boolean = !withinMap(map, coordinates)

private fun testCornerT(map: List<CharArray>, a: Pair<Int, Int>, b: Pair<Int, Int>, plant: Char): Int {
    return if (
        (outsideMap(map, a) || !map[a.first][a.second].equals(plant, ignoreCase = true)) &&
        (outsideMap(map, b) || !map[b.first][b.second].equals(plant, ignoreCase = true))
    ) 1 else 0
}

private fun cornerI(map: List<CharArray>, a: Pair<Int, Int>, b: Pair<Int, Int>, c: Pair<Int, Int>, plant: Char): Int {
    return if (
        withinMap(map, a) && map[a.first][a.second].equals(plant, ignoreCase = true) &&
        withinMap(map, b) && map[b.first][b.second].equals(plant, ignoreCase = true) &&
        !map[c.first][c.second].equals(plant, ignoreCase = true)
    ) 1 else 0
}
package advent2024.day14

import java.io.File

class Robot(var pos: Pair<Int, Int>, val speed: Pair<Int, Int>, val roomSize: Pair<Int, Int>) {

    fun move() {
        pos = Pair(fix(pos.first + speed.first, roomSize.first), fix(pos.second + speed.second, roomSize.second))
    }

    private fun fix(v: Int, max: Int): Int = if (v < 0) v + max else if (v >= max) v - max else v
}

fun solveStep1(input: String, roomSize: Pair<Int, Int>): Long {
    return solveStep(input, roomSize, false)
}

fun solveStep2(input: String, roomSize: Pair<Int, Int>): Long {
    return solveStep(input, roomSize, true, 10000)
}

fun solveStep(input: String, room: Pair<Int, Int>, checkXmasTrees: Boolean, loops: Int = 100): Long {
    val file = if (checkXmasTrees) File("render/day14.txt") else null
    val robots = parseInput(input, room)
    for (i in 0..<loops) {
        robots.stream().forEach { it.move() }
        if (checkXmasTrees && checkXmasTree(file!!, robots, i)) {
            return i.toLong() + 1
        }
    }
    val q1 = countRobots(robots, Pair(0, 0), Pair(room.first / 2, room.second / 2))
    val q2 = countRobots(robots, Pair(room.first / 2 + 1, 0), Pair(room.first, room.second / 2))
    val q3 = countRobots(robots, Pair(0, room.second / 2 + 1), Pair(room.first / 2, room.second))
    val q4 = countRobots(robots, Pair(room.first / 2 + 1, room.second / 2 + 1), Pair(room.first, room.second))
    return q1 * q2 * q3 * q4
}

private val PARSE = "p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)".toRegex()

private fun parseInput(input: String, roomSize: Pair<Int, Int>): List<Robot> {
    return input.split("\n").stream()
        .filter { it.isNotEmpty() }
        .map {
            val (_, px, py, vx, vy) = PARSE.find(it)!!.groupValues
            Robot(Pair(px.toInt(), py.toInt()), Pair(vx.toInt(), vy.toInt()), roomSize)
        }.toList()
}


private fun countRobots(robots: List<Robot>, c1: Pair<Int, Int>, c2: Pair<Int, Int>): Long {
    return robots.stream().filter {
        it.pos.first >= c1.first &&
                it.pos.first < c2.first &&
                it.pos.second >= c1.second &&
                it.pos.second < c2.second
    }.count()
}

private val XMAS_FILTER = ".*XXXXXXXXXX.*".toRegex()

private fun checkXmasTree(file: File, robots: List<Robot>, iteration: Int): Boolean {
    val map = mutableListOf<CharArray>()
    for (i in 0..<robots[0].roomSize.second) {
        map.add(CharArray(robots[0].roomSize.first) { ' ' })
    }
    for (robot in robots) {
        map[robot.pos.second][robot.pos.first] = 'X'
    }

    if (map.stream().filter { XMAS_FILTER.matches(String(it)) }.findFirst().isPresent) {
        file.writeText("second ${iteration + 1}\n")
        map.stream().forEach { file.appendText("${String(it)}\n") }
        return true
    }
    return false
}
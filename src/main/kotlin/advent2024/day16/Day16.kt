package advent2024.day16

const val PRINT_DISABLED = true

enum class Direction(val mov: Pair<Int, Int>) {
    N(Pair(-1, 0)), E(Pair(0, 1)), S(Pair(1, 0)), W(Pair(0, -1));

    fun rotateClock(): Direction {
        return when (this) {
            N -> E
            E -> S
            S -> W
            W -> N
        }
    }

    fun rotateCounterClock(): Direction {
        return when (this) {
            N -> W
            W -> S
            S -> E
            E -> N
        }
    }
}

const val START_POS = 'S'
const val END_POS = 'E'
const val WALL = '#'
const val NOT_EXPLORED = Integer.MAX_VALUE
val START_DIR = Direction.E

fun solveStep1(input: String): Int {
    val map = convertInput(input)
    explore(map, listOf(Move(map.start, 0, START_DIR)))
    return map.costEnd()
}

fun solveStep2(input: String, render: Boolean = false): Int {
    return 0
}

private fun convertInput(input: String): Map {
    val lineList = input.split("\n").stream().filter { it.isNotEmpty() }.toList()
    var start: Pos? = null
    var end: Pos? = null
    val map = mutableListOf<IntArray>()
    for ((i, line) in lineList.withIndex()) {
        val mapLine = mutableListOf<Int>()
        for ((j, c) in line.toCharArray().withIndex()) {
            if (c == WALL) {
                mapLine.add(-1)
                continue
            } else if (c == START_POS) {
                start = Pos(i, j)
                mapLine.add(0)
                continue
            } else if (c == END_POS) {
                end = Pos(i, j)
            }
            mapLine.add(NOT_EXPLORED)
        }
        map.add(mapLine.toIntArray())
    }
    return Map(map, start!!, end!!)
}

private fun explore(map: Map, startingPoints: List<Move>) {
    if (startingPoints.isEmpty()) {
        return
    }
    val nextIteration = mutableListOf<Move>()
    for (point in startingPoints) {
        nextIteration.addAll(
            listOf(
                point.advance(), point.rotateClockAndAdvance(), point.rotateCounterClockAndAdvance()
            ).stream().filter { map.traverse(it) }.toList()
        )
    }
    printMap(map)
    return explore(map, nextIteration)
}



class Map(val map: List<IntArray>, val start: Pos, val end: Pos) {
    fun traverse(mov: Move): Boolean {
        if (map[mov.pos.i][mov.pos.j] != -1 && map[mov.pos.i][mov.pos.j] > mov.cost) {
            map[mov.pos.i][mov.pos.j] = mov.cost
            return true
        }
        return false
    }

    fun costEnd(): Int {
        return map[end.i][end.j]
    }
}

class Move(val pos: Pos, val cost: Int, val dir: Direction) {
    private val scoreAdvance = 1
    private val scoreRotate = 1000

    fun advance(): Move {
        return advance(dir, cost + scoreAdvance)
    }

    fun rotateClockAndAdvance(): Move {
        return advance(dir.rotateClock(), cost + scoreRotate + scoreAdvance)
    }

    fun rotateCounterClockAndAdvance(): Move {
        return advance(dir.rotateCounterClock(), cost + scoreRotate + scoreAdvance)
    }

    private fun advance(d: Direction, c: Int): Move {
        return Move(pos.move(d), c, d)
    }

    override fun toString(): String {
        return "pos=$pos dir=$dir cost=$cost"
    }
}

class Pos(val i: Int, val j: Int) {
    fun move(dir: Direction) = Pos(i + dir.mov.first, j + dir.mov.second)

    override fun toString(): String {
        return "$i,$j"
    }
}

private fun printMap(map: Map) {
    if (PRINT_DISABLED) {
        return
    }
    println("")
    for (line in map.map) {
        val lineStr = CharArray(line.size)
        for ((i, v) in line.withIndex()) {
            lineStr[i] = if (v == -1) WALL else if (v == NOT_EXPLORED) '.' else 'x'
        }
        println(String(lineStr))
    }
}
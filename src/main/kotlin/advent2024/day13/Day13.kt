package advent2024.day13

class Prize(val buttonA: Pair<Double, Double>, val buttonB: Pair<Double, Double>, val prize: Pair<Double, Double>)

fun solveStep1(input: String): Long {
    return solveStep(input, 0.0)
}

fun solveStep2(input: String): Long {
    return solveStep(input, 10000000000000.0)
}

fun solveStep(input: String, offset: Double): Long {
    val prizes = convertInput(input)
    var cost = 0L
    for (prize in prizes) {
        val solutions = findSolutionsFor(prize, offset)
        if (solutions.first.toLong().toDouble() == solutions.first &&
            solutions.second.toLong().toDouble() == solutions.second
        ) {
            cost += solutions.first.toLong() * 3 + solutions.second.toLong()
        }
    }
    return cost
}

private fun convertInput(input: String): List<Prize> {
    val lines = input.split("\n").stream().filter { it.isNotEmpty() }.toList()
    val prizes = mutableListOf<Prize>()
    var i = 0
    while (lines.size > i) {
        val buttonA = parse(lines[i++])
        val buttonB = parse(lines[i++])
        val prize = parse(lines[i++])
        prizes.add(Prize(buttonA, buttonB, prize))
    }
    return prizes
}

private val PARSE = ".*: X[+=](\\d+), Y[+=](\\d+)".toRegex()

private fun parse(s: String): Pair<Double, Double> {
    val (_, a, b) = PARSE.find(s)!!.groupValues
    return Pair(a.toDouble(), b.toDouble())
}

// aX + bY = q
// cX + dY = p
//
// Y = ( cq - ap ) / ( cb - ad )
// X = ( q - bY ) / a

private fun findSolutionsFor(prize: Prize, offset: Double): Pair<Double, Double> {
    val a = prize.buttonA.first
    val b = prize.buttonB.first
    val c = prize.buttonA.second
    val d = prize.buttonB.second
    val q = prize.prize.first + offset
    val p = prize.prize.second + offset

    val y = (c * q - a * p) / (c * b - a * d)
    val x = (q - b * y) / a

    return Pair(x, y)
}

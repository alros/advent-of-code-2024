package advent2024.day07

val sum = { a: Long, b: Long -> a + b }

fun solveStep1(input: String): Long {
    val input = convertInput(input)
    return input.stream()
        .filter { canCombine(it.second, 0, it.first, false) }
        .map { it.first }
        .reduce(0, sum)
}

fun solveStep2(input: String): Long {
    val input = convertInput(input)
    return input.stream()
        .filter { canCombine(it.second, 0, it.first, true) }
        .map { it.first }
        .reduce(0, sum)
}

private fun convertInput(input: String): List<Pair<Long, List<Long>>> {
    return input.split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .map {
            val (result, operandsString) = it.split(":")
            val operands = operandsString.trim().split(" ")
                .stream()
                .map { op -> op.toLong() }
                .toList()
            Pair(result.toLong(), operands)
        }.toList()
}

private fun canCombine(input: List<Long>, cur: Long, expected: Long, chain: Boolean): Boolean {
    if (cur > expected) {
        return false
    }
    val chained = ((if (cur > 0) "" + cur else "") + input[0]).toLong()
    if (input.size == 1) {
        return cur + input[0] == expected || cur * input[0] == expected || (chain && chained == expected)
    }
    return canCombine(input.subList(1, input.size), cur + input[0], expected, chain) ||
            (cur>0 && canCombine(input.subList(1, input.size), cur * input[0], expected, chain)) ||
            (chain && canCombine(input.subList(1, input.size), chained, expected, true))

}
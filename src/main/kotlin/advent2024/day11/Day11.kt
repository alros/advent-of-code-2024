package advent2024.day11

fun solveStep1(input: String, iterations: Int = 25): Long {
    return solve(input, iterations)
}

fun solveStep2(input: String): Long {
    return solve(input, 75)
}

private val sum = { a: Long, b: Long -> a + b }

private fun convertInput(input: String): List<Long> {
    return input.trim().split(" ").stream()
        .map { it.toLong() }
        .toList()
}

private fun solve(input: String, iterations: Int): Long {
    var arrangement = convertInput(input)
    val cache = mutableMapOf<Pair<Long, Int>, Long>()
    return arrangement.stream()
        .map { solve(it, iterations, cache) }
        .reduce(0, sum)
}

private fun solve(stone: Long, iterations: Int, cache: MutableMap<Pair<Long, Int>, Long>): Long {
    if (iterations == 0) {
        return 1
    }
    return cache[Pair(stone, iterations)] ?: solveAndFillCache(stone, iterations, cache)
}

private fun solveAndFillCache(stone: Long, iterations: Int, cache: MutableMap<Pair<Long, Int>, Long>): Long {
    val result = blink(stone).stream()
        .map { solve(it, iterations - 1, cache) }
        .reduce(0, sum)
    cache[Pair(stone, iterations)] = result
    return result
}

private fun blink(stone: Long): List<Long> {
    if (stone == 0L) {
        return listOf(1)
    }
    val stoneStr = stone.toString()
    if (stoneStr.length % 2 == 0) {
        return listOf(
            stoneStr.substring(0, stoneStr.length / 2).toLong(),
            stoneStr.substring(stoneStr.length / 2, stoneStr.length).toLong()
        )
    }
    return listOf(stone * 2024)
}
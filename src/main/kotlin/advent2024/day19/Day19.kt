package advent2024.day19

import java.util.concurrent.ConcurrentHashMap

fun solveStep1(input: String, cache: ConcurrentHashMap<String, Long>): Long {
    var (towels, patterns) = convertInput(input)
    return patterns.stream()
        .filter { it.isEmpty() || canProduce(it, towels, cache) > 0L }
        .count()
}

fun solveStep2(input: String, cache: ConcurrentHashMap<String, Long>): Long {
    var (towels, patterns) = convertInput(input)
    return patterns.parallelStream()
        .map { canProduce(it, towels, cache) }
        .reduce(0) { a, b -> a + b }
}

private fun convertInput(input: String): Pair<List<String>, List<String>> {
    val split = input.split("\n").stream().filter { it.isNotEmpty() }.toList()
    val towels = split[0].split(", ")
    val patterns = split.takeLast(split.size - 1)
    return Pair(towels, patterns)
}

private fun canProduce(pattern: String, towels: List<String>, cache: ConcurrentHashMap<String, Long>): Long {
    var result = cache[pattern]
    if (result == null) {
        result = canProduceInner(pattern, towels, cache)
        cache[pattern] = result
    }
    return result
}

private fun canProduceInner(pattern: String, towels: List<String>, cache: ConcurrentHashMap<String, Long>): Long {
    var counter = 0L
    for (towel in towels) {
        if (pattern.indexOf(towel) == 0) {
            val subPattern = pattern.substring(towel.length)
            if (subPattern.isEmpty()) {
                counter++
            } else {
                counter += canProduce(subPattern, towels, cache)
            }
        }
    }
    return counter
}
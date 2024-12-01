package advent2024.day01

import kotlin.math.abs

val INPUT_REGEX = "(?<a>\\d+) +(?<b>\\d+)".toRegex()

fun solveStep1(input: String): Int {
    val (listA, listB) = convertInput(input)
    return calculateDistances(listA, listB)
}

fun solveStep2(input: String): Int {
    val (listA, listB) = convertInput(input)
    return calculateSimilarity(listA, listB)
}

private fun convertInput(input: String): Array<List<Int>> {
    val (listA, listB) = input.split("\n")
        .stream()
        .filter { line -> line.isNotEmpty() }
        .map { line ->
            val matchResult = INPUT_REGEX.find(line)!!
            Pair(
                matchResult.groups["a"]!!.value.toInt(),
                matchResult.groups["b"]!!.value.toInt()
            )
        }.toList().unzip()
    return arrayOf(listA.sorted(), listB.sorted())
}

private fun calculateDistances(listA: List<Int>, listB: List<Int>): Int {
    var acc = 0
    for (i in listA.indices) {
        acc += abs(listA[i] - listB[i])
    }
    return acc
}

private fun calculateSimilarity(listA: List<Int>, listB: List<Int>): Int {
    val valueMap = listB.groupBy { it }
    var acc = 0
    for (i in listA) {
        acc += i * (valueMap[i]?.size ?: 0)
    }
    return acc
}
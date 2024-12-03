package advent2024.day03

import kotlin.streams.asStream

val REGEX_MUL = "mul\\((?<a>\\d+),(?<b>\\d+)\\)".toRegex()
val REGEX_DONT = "don't\\(\\).*?do\\(\\)".toRegex()

fun solveStep1(input: String): Int {
    val commands = convertInput(input)
    return performMultiplications(commands)
}

fun solveStep2(input: String): Int {
    val commands = convertInput(input, filterDonts = true)
    return performMultiplications(commands)
}

private fun convertInput(input: String, filterDonts: Boolean = false): String {
    val noNL = input.replace("\n", " ")
    return if (filterDonts) REGEX_DONT.replace(noNL, "") else noNL
}

private fun performMultiplications(commands: String): Int {
    return REGEX_MUL.findAll(commands).asStream()
        .map { it.groups["a"]!!.value.toInt() * it.groups["b"]!!.value.toInt() }
        .reduce(0) { a, b -> a + b }
}
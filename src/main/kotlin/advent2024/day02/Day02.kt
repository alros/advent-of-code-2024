package advent2024.day02

import kotlin.streams.toList

fun solveStep1(input: String): Int {
    val reports = convertInput(input)
    return countSafe(reports)
}

fun solveStep2(input: String): Int {
    val reports = convertInput(input)
    return countSafe(reports, useDampener = true)
}

private fun convertInput(input: String): List<List<Int>> {
    return input.split("\n")
        .stream()
        .filter { l -> l.isNotEmpty() }
        .map { line ->
            line.split(" ")
                .stream()
                .mapToInt { v -> v.toInt() }
                .toList()
        }
        .toList()
}

private fun countSafe(reports: List<List<Int>>, useDampener: Boolean = false): Int {
    return reports.count { isItSafe(it, useDampener) }
}

private fun isItSafe(report: List<Int>, useDampener: Boolean): Boolean {
    if (report[0] == report[1]) {
        return if (useDampener) {
            tryRemovingIndexes(report, 0, 1)
        } else {
            false
        }
    }
    val incremental = report[0] < report[1]
    for (i in 1..<report.size) {
        val diff = report[i - 1] - report[i]
        if (!diffWithinRange(incremental, diff)) {
            return if (useDampener) {
                tryRemovingIndexes(report, 0, i - 1, i, i + 1)
            } else {
                false
            }
        }
    }
    return true
}

private fun diffWithinRange(incremental: Boolean, diff: Int) =
    ((incremental && diff < 0 && diff > -4) || (!incremental && diff > 0 && diff < 4))


private fun tryRemovingIndexes(report: List<Int>, vararg indexes: Int): Boolean {
    for (i in indexes) {
        if (isItSafe(removeFromList(report, i), false)) {
            return true
        }
    }
    return false
}

private fun removeFromList(list: List<Int>, indexA: Int): List<Int> {
    val listMod = list.toMutableList()
    listMod.removeAt(indexA)
    return listMod
}

package advent2024.day05

val sum = { a: Int, b: Int -> a + b }

fun solveStep1(input: String): Int {
    val (rules, updates) = convertInput(input)
    val validUpdates = updates.stream()
        .filter { isValid(it, rules) }
        .toList()
    return validUpdates.stream()
        .map { it[it.size / 2] }
        .reduce(sum)
        .orElse(0)
}

fun solveStep2(input: String): Int {
    val (rules, updates) = convertInput(input)
    val invalidUpdates = updates.stream()
        .filter { !isValid(it, rules) }
        .toList()
    val fixedUpdates = invalidUpdates.stream()
        .map { it.sortedWith(RulesComparator(rules)) }
        .toList()
    return fixedUpdates.stream()
        .map { it[it.size / 2] }
        .reduce(sum)
        .orElse(0)
}

private fun convertInput(input: String): Pair<Map<Int, List<Int>>, List<List<Int>>> {
    val rulesPairs = input.split("\n")
        .stream()
        .filter { it.indexOf("|") > 0 }
        .map { it.split("|") }
        .map { Pair(it[0].toInt(), it[1].toInt()) }
        .toList()
        .groupBy { it.first }
    val rules = mutableMapOf<Int, List<Int>>()
    for (k in rulesPairs) {
        rules[k.key] = k.value.stream().map { it.second }.toList()
    }
    val updates = input.split("\n")
        .stream()
        .filter { it.indexOf(",") > 0 }
        .map { it.split(",") }
        .map { it.stream().map { n -> n.toInt() }.toList() }
        .toList()
    return Pair(rules, updates)
}

fun isValid(update: List<Int>, rules: Map<Int, List<Int>>): Boolean {
    val previousPages = mutableListOf<Int>()
    for (upd in update) {
        for (pg in previousPages) {
            if (rules[upd]?.contains(pg) == true) {
                return false
            }
        }
        previousPages.add(upd)
    }
    return true
}

class RulesComparator(validationRules: Map<Int, List<Int>>) : Comparator<Int> {
    private val rules: Map<Int, List<Int>> = validationRules

    override fun compare(a: Int, b: Int): Int {
        return if (rules.getOrDefault(a, emptyList()).contains(b)) {
            -1
        } else if (rules.getOrDefault(b, emptyList()).contains(a)) {
            1
        } else {
            0
        }
    }

}
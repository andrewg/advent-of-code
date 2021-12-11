import ca.gniadek.aoc.util.readInput

fun main() {
    fun MutableSet<String>.deduce(predicate: (String) -> Boolean): String {
        val result = this.single(predicate)
        this.remove(result)
        return result
    }

    val result = readInput().map { line ->
        val (left, right) = line
            .split("|")
            .map { text -> text.trim().split(" ").map { it.toSortedSet().joinToString("") } }
        val input = left.sorted().sortedBy { it.length }

        val text = Array(10) { "" }
        text[1] = input[0]
        text[4] = input[2]
        text[7] = input[1]
        text[8] = input[9]

        val setOf5 = mutableSetOf(input[3], input[4], input[5])
        val setOf6 = mutableSetOf(input[6], input[7], input[8])

        val leftSegments = setOf5
            .flatMap { it.toList() }
            .groupingBy { it }
            .eachCount()
            .filter { it.value == 1 }
            .map { it.key }
            .toSortedSet()
        val rightSegments = text[1].toSortedSet()

        // Deduce digits with 6 segments
        text[9] = setOf6.deduce { !it.contains(leftSegments.first()) || !it.contains(leftSegments.last()) }
        text[0] = setOf6.deduce { it.contains(rightSegments.first()) && it.contains(rightSegments.last()) }
        text[6] = setOf6.single()

        // Deduce digits with 5 segments
        text[3] = setOf5.deduce { !it.any(leftSegments::contains) }
        text[5] = setOf5.deduce { text[6].toSet().minus(it.toSet()).size == 1 }
        text[2] = setOf5.single()

        val values = text.mapIndexed { index, value -> value to index }.toMap()
        right.map { values[it] }.joinToString("").toInt()
    }.sum()
    println(result)
}
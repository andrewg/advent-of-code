import ca.gniadek.aoc.util.readInput

fun main() {
    fun codes(text: String) = text.trim().split(" ").map { it.toSortedSet().joinToString("") }
    var count = 0
    readInput().forEach { line ->
        val (input, output) = line.split("|")
            .map(::codes)
            .map { codes -> codes.sorted().sortedBy { it.length } }

        val text1 = input[0]
        val text4 = input[2]
        val text7 = input[1]
        val text8 = input[9]
        val values = setOf(text1, text4, text7, text8)

        count += output.filter(values::contains).size
    }
    println(count)
}
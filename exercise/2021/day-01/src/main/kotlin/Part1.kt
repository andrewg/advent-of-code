import ca.gniadek.aoc.util.readInput

fun main() {
    var count = 0
    var last: Int? = null
    readInput().map { it.toInt() }.forEach {
        if (last != null && it > last!!) {
            count++
        }
        last = it
    }
    println(count)
}
import ca.gniadek.aoc.util.readInput

fun main() {
    var depth = 0
    var position = 0
    val pattern = Regex("(forward|down|up) (\\d+)")
    readInput().mapNotNull { pattern.matchEntire(it) }.forEach {
        val command = it.groups[1]!!.value
        val value = it.groups[2]!!.value.toInt()
        when (command) {
            "forward" -> position += value
            "up" -> depth -= value
            "down" -> depth += value
        }
    }
    println(depth * position)
}
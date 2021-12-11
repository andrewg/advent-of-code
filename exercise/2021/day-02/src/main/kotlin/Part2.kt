import ca.gniadek.aoc.util.readInput

fun main() {
    var depth = 0
    var position = 0
    var aim = 0
    val pattern = Regex("(forward|down|up) (\\d+)")
    readInput().mapNotNull { pattern.matchEntire(it) }.forEach {
        val command = it.groups[1]!!.value
        val value = it.groups[2]!!.value.toInt()
        when (command) {
            "forward" -> {
                position += value
                depth += aim * value
            }
            "up" -> aim -= value
            "down" -> aim += value
        }
    }
    println(depth * position)
}
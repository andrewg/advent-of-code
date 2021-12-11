import ca.gniadek.aoc.util.readInput
import java.util.*

fun main() {
    val window = LinkedList<Int>()
    var count = 0
    readInput().map { it.toInt() }.forEach {
        val previous = window.sum()
        window.add(it)
        if (window.size == 4) {
            window.poll()
            if (window.sum() > previous) {
                count++
            }
        }
    }
    println(count)
}
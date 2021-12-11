import ca.gniadek.aoc.util.readInput
import java.util.*

fun main() {
    val grid = readInput().map { line -> line.map { it.digitToInt() } }.toList()

    data class Location(val row: Int, val column: Int)

    fun outOfBounds(location: Location) = location.row < 0
            || location.column < 0
            || location.row >= grid.size
            || location.column >= grid[location.row].size

    fun fillBasin(location: Location, basin: MutableSet<Location>) {
        if (basin.contains(location)) return
        if (outOfBounds(location)) return

        val value = grid[location.row][location.column]
        if (value == 9) return

        basin.add(location)

        listOf(
            Location(location.row, location.column + 1),
            Location(location.row, location.column - 1),
            Location(location.row + 1, location.column),
            Location(location.row - 1, location.column)
        ).forEach { fillBasin(it, basin) }
    }

    val max = PriorityQueue<Int>(Collections.reverseOrder())
    grid.indices.forEach {
        val row = grid[it]
        val above = if (it > 0) grid[it - 1] else null
        val below = if (it + 1 < grid.size) grid[it + 1] else null
        row.forEachIndexed next@ { column, value ->
            val left = if (column > 0) row[column - 1] else return@next
            val top = if (above != null) above[column] else return@next
            val right = if (column + 1 < row.size) row[column + 1] else return@next
            val bottom = if (below != null) below[column] else return@next

            fun min(other: Int?) = other == null || value < other
            if (min(left) && min(top) && min(right) && min(bottom)) {
                val location = Location(it, column)
                val basin = mutableSetOf<Location>()
                fillBasin(location, basin)
                max.add(basin.size)
            }
        }
    }

    val score = (1..3).mapNotNull { max.poll() }.reduce { acc, next -> acc * next }
    println(score)
}

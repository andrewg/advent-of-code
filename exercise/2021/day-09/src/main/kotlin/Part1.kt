import ca.gniadek.aoc.util.readInput

fun main() {
    fun risk(row: List<Int>, above: List<Int>?, below: List<Int>?): Int {
        val lowPoints = row.filterIndexed { index, value ->
            val left = if (index > 0) row[index - 1] else null
            val top = if (above != null) above[index] else null
            val right = if (index + 1 < row.size) row[index + 1] else null
            val bottom = if (below != null) below[index] else null

            fun min(other: Int?) = other == null || value < other

            min(left) && min(top) && min(right) && min(bottom)
        }
        return lowPoints.sum() + lowPoints.size
    }

    var risk = 0
    var above2: List<Int>? = null
    var above1: List<Int>? = null
    readInput().forEach { line ->
        val current = line.map { it.digitToInt() }
        if (above1 != null) {
            risk += risk(above1!!, above2, current)
        }
        above2 = above1
        above1 = current
    }
    risk += risk(above1!!, above2, null)
    println(risk)
}

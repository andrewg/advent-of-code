import ca.gniadek.aoc.util.readInput

fun main() {
    val grid = readInput()
        .map { line -> line.map { it.digitToInt() }.toIntArray() }
        .toList().toTypedArray()
    fun forEach(handle: (Int, Int, Int) -> Unit) {
        grid.indices.forEach { row ->
            (0 until grid[0].size).forEach { column ->
                handle(row, column, grid[row][column])
            }
        }
    }
    var flashes = 0
    repeat(100) {
        forEach { row, column, value ->
            grid[row][column] = value + 1
        }
        var flashed = true
        while (flashed) {
            flashed = false
            forEach next@ { row, column, value ->
                if (value <= 9) return@next
                flashed = true
                flashes += 1
                grid[row][column] = 0
                listOf(
                    row - 1 to column - 1,
                    row - 1 to column,
                    row - 1 to column + 1,
                    row to column - 1,
                    row to column + 1,
                    row + 1 to column - 1,
                    row + 1 to column,
                    row + 1 to column + 1
                ).forEach { (row, column) ->
                    if (row >= 0 && column >= 0 && row < grid.size && column < grid[row].size) {
                        val value = grid[row][column]
                        if (value > 0) {
                            grid[row][column] = value + 1
                        }
                    }
                }
            }
        }
    }
    println(flashes)
}
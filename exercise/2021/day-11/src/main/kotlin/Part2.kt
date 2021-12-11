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
    var flashedStep: Int? = null
    var step = 1
    while (flashedStep == null)  {
        var hadFlashed = true
        forEach { row, column, value ->
            if (hadFlashed && value > 0) hadFlashed = false
            grid[row][column] = value + 1
        }
        if (hadFlashed && step > 1) {
            flashedStep = step - 1
            break
        }
        var flashed = true
        while (flashed) {
            flashed = false
            forEach next@ { row, column, value ->
                if (value <= 9) return@next
                flashed = true
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
        step ++
    }
    println(flashedStep)
}
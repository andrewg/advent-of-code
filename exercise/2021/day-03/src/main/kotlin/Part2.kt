import ca.gniadek.aoc.util.readInput
import kotlin.math.max

fun main() {
    fun IntArray.asBitsToLong() = this.joinToString("").toLong(2)

    var maxBits = 0
    val input = readInput().map { line ->
        val characters = line.toList()
        val digits = characters.map { it.digitToInt() }
        val array = digits.toIntArray()
        maxBits = max(maxBits, array.size)
        array
    }.toList()

    fun List<IntArray>.rate(choose: (Int, Int) -> Int): IntArray {
        var list = this
        (0 until maxBits).forEach { bit ->
            if (list.size == 1) return list[0]
            val ones = list.count { it[bit] == 1 }
            val chosen = choose(ones, list.size - ones)
            list = list.filter { it[bit] == chosen }
        }
        return list[0]
    }

    val oxygen = input.rate { ones, zeroes -> if (ones >= zeroes) 1 else 0 }.asBitsToLong()
    val co2 = input.rate { ones, zeroes -> if (zeroes <= ones) 0 else 1 }.asBitsToLong()
    println(oxygen * co2)
}
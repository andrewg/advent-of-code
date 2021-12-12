import ca.gniadek.aoc.util.readInput
import kotlin.math.abs
import kotlin.math.max

fun main() {
    fun Array<IntArray>.resize(bits: IntArray) = forEachIndexed { index, array ->
        if (bits.size > array.size) {
            this[index] = array.copyOf(bits.size)
        }
    }
    fun Iterable<Int>.asBitsToLong() = this.joinToString("").toLong(2)
    fun IntArray.asBitsToLong() = this.toList().asBitsToLong()

    val counts = Array(2) { IntArray(12) }
    readInput().forEach { line ->
        val bits = line.toList().map { it.digitToInt() }.toIntArray()
        counts.resize(bits)
        bits.forEachIndexed { index, value -> counts[value][index]++ }
    }

    val common = IntArray(max(counts[0].size, counts[1].size))
    common.indices.forEach { index ->
        val zero = if (counts[0].size > index) counts[0][index] else 0
        val one = if (counts[1].size > index) counts[1][index] else 0
        if (one > zero) {
            common[index] = 1
        }
    }
    val gamma = common.asBitsToLong()
    val epsilon = common.map { abs(it - 1) }.asBitsToLong()
    println(gamma * epsilon)
}
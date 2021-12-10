package ca.gniadek.aoc.y21.d10

import ca.gniadek.aoc.util.readInput

fun main() {
    val closing = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )
    val points = mapOf<Char, Long>(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )
    var scores = ArrayList<Long>()
    readInput().forEach nextLine@ { line ->
        var stack = ArrayDeque<Char>()
        line.forEach { character ->
            val last = stack.lastOrNull()
            when (character) {
                '(', '[', '{', '<' -> stack.addLast(character)
                ')', ']', '}', '>' -> if (last == null || closing[last] != character) {
                    return@nextLine
                } else {
                    stack.removeLast()
                }
            }
        }
        if (stack.isEmpty()) {
            return@nextLine
        }
        var score = 0L
        stack.reversed().forEach {
            val expected = closing[it]
            score = (score * 5) + points[expected]!!
        }
        scores.add(score)
    }

    println(scores.sorted().let { it[it.size/2] })
}
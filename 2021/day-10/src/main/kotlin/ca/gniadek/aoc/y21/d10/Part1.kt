package ca.gniadek.aoc.y21.d10

import ca.gniadek.aoc.util.readInput

fun main() {
    val opening = mapOf(
        ')' to '(',
        ']' to '[',
        '}' to '{',
        '>' to '<'
    )
    val points = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )
    var score = 0
    readInput().forEach stop@ { line ->
        var stack = ArrayDeque<Char>()
        var stop = false
        line.forEach { character ->
            val last = stack.lastOrNull()
            if (!stop) {
                when (character) {
                    '(', '[', '{', '<' -> stack.addLast(character)
                    ')', ']', '}', '>' -> if (opening[character] != last) {
                        score += points[character]!!
                        return@stop
                    } else {
                        stack.removeLast()
                    }
                }
            }
        }
    }

    println(score)
}
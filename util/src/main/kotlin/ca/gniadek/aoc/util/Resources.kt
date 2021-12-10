package ca.gniadek.aoc.util

fun readInput(name: String = "input"): Sequence<String> {
    return sequence {
        this::class.java.classLoader.getResourceAsStream(name).use { input ->
            input?.bufferedReader().use { reader ->
                reader?.lineSequence()?.forEach {
                    yield(it)
                }
            }
        }
    }
}
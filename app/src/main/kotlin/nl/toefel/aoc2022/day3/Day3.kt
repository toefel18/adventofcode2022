package nl.toefel.aoc2022.day3

import nl.toefel.aoc2022.resource

fun Char.priority() = if (this.isLowerCase()) this.code - 'a'.code + 1 else this.code - 'A'.code + 27

fun main() {
    val lines = resource("input-day-3.txt").readLines()

    lines.map { it.substring(0 until (it.length / 2)) to it.substring(it.length / 2) }
        .map { it.first.toSet().intersect(it.second.toSet()).first() }
        .sumOf { it.priority()}
        .let { println("Day 3 part 1 = $it") }

    val lines2 = resource("input-day-3.txt").readLines()
    lines2.chunked(3)
        .map { setOf3 -> setOf3.map { it.toSet() }.reduce{ acc, next -> acc.intersect(next)}.first() }
        .sumOf { it.priority() }
        .let { println("Day 3 part 2 = $it") }
}
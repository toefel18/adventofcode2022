package nl.toefel.aoc2022.day1

import nl.toefel.aoc2022.day1.Shape.*
import nl.toefel.aoc2022.resource

enum class Shape(val points: Long, val representation: String) {
    ROCK(1, "AX"),
    PAPER(2, "BY"),
    SCISSORS(3, "CZ");

    companion object {
        fun fromString(input: String): Shape =
            values().find { it.representation.contains(input, ignoreCase = true) }
                ?: throw IllegalArgumentException("$input is not a valid Shape")
    }
}

infix fun <A, B> A.beats(that: B): Pair<A, B> = Pair(this, that)

data class Game(val opponentShape: Shape, val myShape: Shape) {
    val defeatsShape = mapOf<Shape, Shape>(
        ROCK beats SCISSORS,
        PAPER beats ROCK,
        SCISSORS beats PAPER
    )

    fun score(): Long = myShape.points + when {
        opponentShape == myShape -> 3
        defeatsShape[myShape] == opponentShape -> 6
        else -> 0
    }
}

fun day2() {
    val games = resource("input-day-2.txt").readLines()
        .map { line -> line.split(" ") }
        .map { Game(Shape.fromString(it[0]), Shape.fromString(it[1])) }

    println(games.sumOf { it.score() })
}

fun main() {
    day2()
}
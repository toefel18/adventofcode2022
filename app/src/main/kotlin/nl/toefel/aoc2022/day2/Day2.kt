package nl.toefel.aoc2022.day2

import nl.toefel.aoc2022.day2.Shape.Companion.losesBy
import nl.toefel.aoc2022.day2.Shape.Companion.winsOf
import nl.toefel.aoc2022.resource


infix fun <A, B> A.beats(that: B): Pair<A, B> = Pair(this, that)

enum class Shape(val points: Long, val representation: String) {
    ROCK(1, "AX"),
    PAPER(2, "BY"),
    SCISSORS(3, "CZ");

    companion object {
        fun fromString(input: String): Shape =
            values().find { it.representation.contains(input, ignoreCase = true) }
                ?: throw IllegalArgumentException("$input is not a valid Shape")

        val shapeDefeats: Map<Shape, Shape> = mapOf(
            ROCK beats SCISSORS,
            PAPER beats ROCK,
            SCISSORS beats PAPER
        )

        fun Shape.winsOf() =
            shapeDefeats[this] ?: throw IllegalArgumentException("shape $this does not lose of any other shape")

        fun Shape.losesBy() = shapeDefeats.entries.find { it.value == this }?.key
            ?: throw IllegalArgumentException("shape $this does not lose of any other shape")
    }
}

class GamePart1(val opponentShape: Shape, val myShape: Shape) {
    fun score(): Long = myShape.points + when {
        opponentShape == myShape -> 3
        myShape.winsOf() == opponentShape -> 6
        else -> 0
    }
}


data class GamePart2(val opponentShape: Shape, val desiredOutcome: String) {
    fun score(): Long {
        val myShape: Shape = when (desiredOutcome) {
            "X" -> opponentShape.winsOf()
            "Y" -> opponentShape
            "Z" -> opponentShape.losesBy()
            else -> throw IllegalArgumentException("Invalid state")
        }

        return myShape.points + when {
            opponentShape == myShape -> 3
            myShape.winsOf() == opponentShape -> 6
            else -> 0
        }
    }
}

fun main() {
    val lines = resource("input-day-2.txt").readLines()
    val gamesPart1 = lines
        .map { line -> line.split(" ") }
        .map { GamePart1(Shape.fromString(it[0]), Shape.fromString(it[1])) }

    println("part1: " + gamesPart1.sumOf { it.score() })

    val gamesPart2 = lines
        .map { line -> line.split(" ") }
        .map { GamePart2(Shape.fromString(it[0]), it[1]) }

    println(gamesPart2.sumOf { it.score() })
}
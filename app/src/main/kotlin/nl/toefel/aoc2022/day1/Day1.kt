package nl.toefel.aoc2022.day1

import nl.toefel.aoc2022.resource

data class Elf(val id : Int, val calories: MutableList<Long> = arrayListOf()) {
    val totalCalories get() = calories.sum()
}

fun day1() {
    val inputLines = resource("input-day-1.1.txt").readLines()
    val elves = inputLines.fold(listOf(Elf(0))) { elves, caloryEntry ->
        if (caloryEntry.isBlank()) {
            elves + Elf(elves.last().id + 1) // Blank line, create a new Elv bucket.
        } else {
            elves.last().calories.add(caloryEntry.toLong())
            elves
        }
    }

    val elfWithMostCalories = elves.maxBy { it.totalCalories }
    println("Day 1 - Part 1: Elf ${elfWithMostCalories.id} ${elfWithMostCalories.totalCalories} total calories")

    val totalCaloriesOfTop3 = elves.sortedBy { it.totalCalories }.takeLast(3).sumOf { it.totalCalories }
    println("Day 1 - Part 2: Total cal of top 3 elves: $totalCaloriesOfTop3")
}

fun main() {
    day1()
}
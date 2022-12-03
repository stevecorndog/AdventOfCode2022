package day3

import readInput

fun main() {

    val day = "day3"
    println("Output for $day")

    val points = arrayListOf('@') // dummy
    points.addAll(('a'..'z')) // 1 - 26
    points.addAll(('A'..'Z')) // 27 - 52

    fun part1(input: List<String>) : Int {
        var score = 0
        input.forEach { line ->
            val rucksackContents= line.toList()
            val compartmentA = rucksackContents.take(rucksackContents.size/2).toSet()
            val compartmentB = rucksackContents.takeLast(rucksackContents.size/2).toSet()
            val commonItem = compartmentA.intersect(compartmentB).first()

            score += points.indexOf(commonItem)
        }

        return score
    }

    fun part2(input: List<String>) : Int {
        var score = 0

        input.chunked(3).forEach {
            // what's the common item in the 3 rows?
            val elf1 = it[0].toSet()
            val elf2 = it[1].toSet()
            val elf3 = it[2].toSet()
            val commonItem = elf1.intersect(elf2).intersect(elf3).first()
            score += points.indexOf(commonItem)

        }
        return score
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")

    check(part1(testInput) == 157)
    println("Part 1 answer: ${part1(input)}")

    check(part2(testInput) == 70)
    println("Part 2 answer: ${part2(input)}")
}
package day3

import readInput

fun main() {

    val day = "day3"
    println("Output for $day")

    fun part1(input: List<String>) : Int {
        // vJrwpWtwJgWrhcsFMMfFFhFp
        var score = 0
        input.forEach { line ->
            val rucksackContents= line.toList()
            val compartmentA = rucksackContents.take(rucksackContents.size/2)
            val compartmentB = rucksackContents.takeLast(rucksackContents.size/2)


            val commonItem = compartmentA.filter { compartmentB.contains(it) }[0]
//            println("Rucksack $line has $compartmentA and $compartmentB with $commonItem in common")

            if (commonItem in 'a'..'z') {
                score  += commonItem - 'a' + 1
            }
            if (commonItem in 'A'..'Z') {
                score  += commonItem - 'A' + 27
            }

        }

        return score
    }

    fun part2(input: List<String>) : Int {
        var score = 0

        input.chunked(3).forEach {
            // what's the common item in the 3 rows?
            val commonItem = it[0].toList().filter { row0Char -> it[1].toList().contains(row0Char) }
                .filter { row01Char -> it[2].toList().contains(row01Char) }[0]
            println("Common char in first 3 rows is $commonItem")


            if (commonItem in 'a'..'z') {
                score  += commonItem - 'a' + 1
            }
            if (commonItem in 'A'..'Z') {
                score  += commonItem - 'A' + 27
            }

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
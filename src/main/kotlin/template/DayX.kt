package template

import readInput

fun main() {

    val day = "dayX"
    println("Output for $day")

    fun part1(input: List<String>) : Int {
        TODO()
    }

    fun part2(input: List<String>) : Long {
        TODO()
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")

    check(part1(testInput) == 0)
//    println("Part 1 answer: ${part1(input)}")

//    check(part2(testInput) == 0L)
//    println("Part 2 answer: ${part2(input)}")
}
package day4


import readInput

fun main() {

    val day = "day4"
    println("Output for $day")

    fun part1(input: List<String>) : Int {
        var conflicts = 0

        input.forEach {
            val split = it.split("-", ",").map { it.toInt() }

            val firstElfRange = split[0]..split[1]
            val secondElfRange = split[2]..split[3]

            if (firstElfRange.all { secondElfRange.contains(it) } ||
                    secondElfRange.all{ firstElfRange.contains(it)}) ++conflicts
        }

        return conflicts
    }

    fun part2(input: List<String>) : Int {
        var conflicts = 0

        input.forEach {
            val split = it.split("-", ",").map { it.toInt() }

            val firstElfRange = split[0]..split[1]
            val secondElfRange = split[2]..split[3]

            if (firstElfRange.any { secondElfRange.contains(it) } ||
                secondElfRange.any{ firstElfRange.contains(it)}) ++conflicts
        }

        return conflicts
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")

    check(part1(testInput) == 2)
    println("Part 1 answer: ${part1(input)}")

    check(part2(testInput) == 4)
    println("Part 2 answer: ${part2(input)}")
}
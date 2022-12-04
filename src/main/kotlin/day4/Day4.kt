package day4


import howLong
import readInput
import java.time.LocalDateTime

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

    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(part2(testInput) == 4)

    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: ${part2} - took ${duration.toMillis()}ms")
}
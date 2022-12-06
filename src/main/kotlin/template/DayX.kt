package template

import howLong
import readInput
import java.time.LocalDateTime

fun main() {

    val day = "dayX"
    println("Output for $day")

    fun part1(input: List<String>) : Int {
        var result = 0
        TODO()
        return result
    }

    fun part2(input: List<String>) : Long {
        var result = 0L
        TODO()
        return result
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")


    check(part1(testInput) == 0) // TODO replace test value

    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(part2(testInput) == 0L) // TODO replace test value

    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: $part2 - took ${duration.toMillis()}ms")
}
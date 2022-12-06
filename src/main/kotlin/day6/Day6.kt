package day6


import howLong
import readInput
import java.time.LocalDateTime

fun main() {

    val day = "day6"
    println("Output for $day")

    fun part1(input: List<String>) : Int {
        var result = 0
        val packetSize = 4

        val windowed = input[0].toList().windowed(packetSize)

        for (index in 0 until windowed.size) {
            val chars = windowed[index]
            // we have a window of 4 chars - are they different?
            if (!chars.groupingBy { it }.eachCount().any { it.value > 1 }) {
                // yes, what
                result = index + packetSize
                println("Different $chars at index $index, result is $result")
                break
            }
        }

        return result
    }

    fun part2(input: List<String>) : Int {
        var result = 0
        val packetSize = 14

        val windowed = input[0].toList().windowed(packetSize)

        for (index in 0 until windowed.size) {
            val chars = windowed[index]
            // we have a window of chars - are they different?
            if (!chars.groupingBy { it }.eachCount().any { it.value > 1 }) {
                // yes, what
                result = index + packetSize
                println("Different $chars at index $index, result is $result")
                break
            }
        }

        return result
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")


    check(part1(testInput) == 7)
    check(part1(readInput(day, "test2.txt")) == 5)
    check(part1(readInput(day, "test3.txt")) == 6)
    check(part1(readInput(day, "test4.txt")) == 10)
    check(part1(readInput(day, "test5.txt")) == 11)

    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(part2(testInput) == 19)
    check(part2(readInput(day, "test2.txt")) == 23)
    check(part2(readInput(day, "test3.txt")) == 23)
    check(part2(readInput(day, "test4.txt")) == 29)
    check(part2(readInput(day, "test5.txt")) == 26)

    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: $part2 - took ${duration.toMillis()}ms")
}
package day10

import howLong
import readInput
import java.time.LocalDateTime
import kotlin.math.abs

fun main() {

    val day = "day10"
    println("Output for $day")

    fun part1(input: List<String>) : Int {
        var xRegister = 1
        var currentInstruction : String? = null
        var instructions = input
        var result = 0

        (1..220).forEach { cycleCount ->
            // result = signal strength *during* 20th, 60th, 100th, 140th, 180th and 220th cycles
            if ((cycleCount - 20) % 40 == 0) {
                val signalStrength = xRegister * cycleCount
                result += signalStrength
            }
            // start of cycle - do we have an instruction?
            val currentlyExecuting = !currentInstruction.isNullOrBlank()

            if (!currentlyExecuting) {
                // read the next instruction
                currentInstruction = instructions.first()
                instructions = instructions.drop(1)
            }

            // end of cycle - at this point we definitely have a currentInstruction
            val splits = currentInstruction!!.split(" ")
            when (splits[0]) {
                "noop" -> currentInstruction = null
                "addx" -> {
                    if (currentlyExecuting) {
                        // this instruction ends, process it
                        xRegister += splits[1].toInt()
                        currentInstruction = null
                    } else {
                        // roll it over for next time
                    }
                }
                else -> {
                    println("Unexpected item in bagging area: $currentInstruction")
                }
            }

        }
        return result
    }

    fun part2(input: List<String>) : Int {
        var xRegister = 1
        var currentInstruction : String? = null
        var instructions = input

        val lit = '#'
        val dark = '.'

        val screenRow = ArrayList<Char>(40)

        (1..240).forEach { cycleCount ->

            val pixelPos = cycleCount % 40 - 1
            // start of cycle - do we have an instruction?
            val currentlyExecuting = !currentInstruction.isNullOrBlank()

            if (!currentlyExecuting) {
                // read the next instruction
                currentInstruction = instructions.first()
                instructions = instructions.drop(1)
            }

            // Is the current pixel lit or dark?
            if (abs(xRegister - pixelPos) <= 1){
                screenRow.add(lit)
            }
            else {
                screenRow.add(dark)
            }

            // end of cycle - at this point we definitely have a currentInstruction
            val splits = currentInstruction!!.split(" ")
            when (splits[0]) {
                "noop" -> currentInstruction = null
                "addx" -> {
                    if (currentlyExecuting) {
                        // this instruction ends, process it
                        xRegister += splits[1].toInt()
                        currentInstruction = null
                    } else {
                        // roll it over for next time
                    }
                }
                else -> {
                    println("Unexpected item in bagging area: $currentInstruction")
                }
            }

            if (cycleCount % 40 == 0) {
                screenRow.forEach { print(it) }
                println()
                screenRow.clear()
            }

        }
        return 0
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")


    check(part1(testInput) == 13140)

    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(part2(testInput) == 0)

    println()
    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: $part2 - took ${duration.toMillis()}ms")
}
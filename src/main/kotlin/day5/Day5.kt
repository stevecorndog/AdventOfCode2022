package day5


import howLong
import readInput
import java.time.LocalDateTime
import java.util.Stack

fun main() {

    val day = "day5"
    println("Output for $day")

    fun part1(input: List<String>) : String {

        val blankLineIndex = input.indexOf("")
        // parse the starting stacks
        val stacksLines = input.take(blankLineIndex-1).reversed()
        val instructionLines = input.takeLast(input.size-blankLineIndex-1)
        val numStacks = input[blankLineIndex-1].split(" ").last().toInt()


        // set the stacks
        val stacks = ArrayList<Stack<Char>>(numStacks)
        (0 until numStacks).forEach {
            stacks.add(Stack<Char>())
        }
        stacksLines.forEach { line ->
            (0 until numStacks).forEach { stackNo ->
                // get the crate at the appropriate position
                val position = (stackNo * 4) + 1
                if (position < line.length - 1) {
                    val crateId = line[position]
                    if (crateId != ' ') {
                        stacks[stackNo].push(crateId)
                    }
                }
            }
        }

        // now run the instructions
        instructionLines.forEach { line ->
            val splits = line.split(" ")
            val cratesToMove = splits[1].toInt()
            val fromStack = splits[3].toInt()
            val toStack = splits[5].toInt()

            (1..cratesToMove).forEach {
                val crate = stacks[fromStack-1].pop()
                stacks[toStack-1].push(crate)
            }
        }

        val resultCharArray = ArrayList<Char>()
        stacks.forEach {
            resultCharArray.add(it.peek())
        }
        val result = String(resultCharArray.toCharArray())
        return result
    }

    fun part2(input: List<String>) : String {

        val blankLineIndex = input.indexOf("")
        // parse the starting stacks
        val stacksLines = input.take(blankLineIndex-1).reversed()
        val instructionLines = input.takeLast(input.size-blankLineIndex-1)
        val numStacks = input[blankLineIndex-1].split(" ").last().toInt()

        // set the stacks
        val stacks = ArrayList<Stack<Char>>(numStacks)
        (0 until numStacks).forEach {
            stacks.add(Stack<Char>())
        }
        stacksLines.forEach { line ->
            (0 until numStacks).forEach { stackNo ->
                // get the crate at the appropriate position
                val position = (stackNo * 4) + 1
                if (position < line.length - 1) {
                    val crateId = line[position]
                    if (crateId != ' ') {
                        stacks[stackNo].push(crateId)
                    }
                }
            }
        }

        // now run the instructions
        instructionLines.forEach { line ->
            val splits = line.split(" ")
            val cratesToMove = splits[1].toInt()
            val fromStack = splits[3].toInt()
            val toStack = splits[5].toInt()

            // grab the crates onto a temporary stack, as they'll be in the
            // wrong order
            val tmpStack = Stack<Char>()
            (1..cratesToMove).forEach {
                val crate = stacks[fromStack - 1].pop()
                tmpStack.push(crate)
            }

            // then move the tmp stack onto the target stack, thus getting the intended order
            while (!tmpStack.isEmpty()) {
                stacks[toStack-1].push(tmpStack.pop())
            }
        }

        val resultCharArray = ArrayList<Char>()
        stacks.forEach {
            resultCharArray.add(it.peek())
        }
        val result = String(resultCharArray.toCharArray())
        return result
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")


    check(part1(testInput) == "CMZ")

    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(part2(testInput) == "MCD")

    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: ${part2} - took ${duration.toMillis()}ms")
}
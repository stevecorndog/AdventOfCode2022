package day1

import readInput

fun main() {

    val day = "day1"
    println("Output for $day")

    fun part1(input: List<String>) : Int {

        var elves = ArrayList<Int>()
        elves.add(0)

        input.forEach {
//            println(it)
            if (it.trim().isBlank()) {
                // new Elf
//                println("new elf")
                elves.add(0)
            } else {
                elves[elves.size - 1] += it.toInt()
//                println("Elf ${elves.size - 1} = ${elves[elves.size-1]}" )
            }
        }
//        println(elves)
        println("Max = ${elves.max()}")
        println("Min = ${elves.min()}")
        return elves.maxOrNull() ?: -1
    }

    fun part2(input: List<String>) : Int {

        var elves = ArrayList<Int>()
        elves.add(0)

        input.forEach {
//            println(it)
            if (it.trim().isBlank()) {
                // new Elf
//                println("new elf")
                elves.add(0)
            } else {
                elves[elves.size - 1] += it.toInt()
//                println("Elf ${elves.size - 1} = ${elves[elves.size-1]}" )
            }
        }

        elves.sort()
        var topElves = elves.takeLast(3)
        println(topElves)
        topElves.sum()
        println("Max = ${elves.max()}")
        println("Min = ${elves.min()}")

        return topElves.sum()
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")

    check(part1(testInput) == 24000)
    println("Part 1 answer: ${part1(input)}")

    check(part2(testInput) == 45000)
    println("Part 2 answer: ${part2(input)}")
}
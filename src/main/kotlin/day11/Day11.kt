package day11

import howLong
import readInput
import java.time.LocalDateTime

fun main() {

    val day = "day11"
    println("Output for $day")

    fun part1(input: List<String>) : Int {
        val monkeys = ArrayList<Monkey>()
        input.filterNot { it.isBlank() }.chunked(6).forEach {
            monkeys.add(Monkey(it))
        }

        val inspections = ArrayList<Int>()
        monkeys.forEach { _ -> inspections.add(0) }

        (1..20).forEach { round ->
            monkeys.forEachIndexed { index, monkey ->
                // inspect and throw each item ine at a time
                monkey.startingItems.forEach { item ->
                    // inspect the item
                    var itemWorryLevel = item
                    inspections[index]++

                    // what's the new worry level
                    val split = monkey.operation.split(' ')
                    val rhs = when (split[1]) {
                        "old" -> itemWorryLevel
                        else -> split[1].toInt()
                    }
                    when (split[0]) {
                        "+" -> {
                            itemWorryLevel += rhs
                        }

                        "*" -> {
                            itemWorryLevel *= rhs
                        }

                        else -> {
                            println("Unexpected item in bagging area: ${monkey.operation}")
                        }
                    }

                    // adjust for relief
                    itemWorryLevel /= 3

                    // now throw it
                    val recipient = when (itemWorryLevel % monkey.testValue == 0) {
                        true -> {
                            monkey.trueRecipient
                        }

                        false -> {
                            monkey.falseRecipient
                        }
                    }
                    monkeys[recipient].startingItems.add(itemWorryLevel)
                }
                // we've now thrown all the items
                monkey.startingItems.clear()
            }

        }

        val sortedByInspections = inspections.sorted().reversed()
        return sortedByInspections[0] * sortedByInspections[1]
    }

    fun part2(input: List<String>) : Long {
        val monkeys = ArrayList<Monkey>()
        input.filterNot { it.isBlank() }.chunked(6).forEach {
            monkeys.add(Monkey(it))
        }

        val inspections = ArrayList<Int>()
        var uberTest = 1
        monkeys.forEach { monkey ->
            inspections.add(0)
            uberTest *= monkey.testValue
        }

//        println("Uber test value $uberTest")

        (1..10000).forEach { round ->
            monkeys.forEachIndexed { index, monkey ->
                // inspect and throw each item ine at a time
                monkey.startingItems.forEach { item ->
                    // inspect the item
                    var itemWorryLevel = item
                    inspections[index]++

                    // what's the new worry level
                    val split = monkey.operation.split(' ')
                    val rhs = when (split[1]) {
                        "old" -> itemWorryLevel
                        else -> split[1].toInt()
                    }
                    val itemWorryLevelAsLong = when (split[0]) {
                        "+" -> {
                            (itemWorryLevel + rhs).toLong()
                        }

                        "*" -> {
                            itemWorryLevel.toLong().times(rhs)
                        }

                        else -> {
                            println("Unexpected item in bagging area: ${monkey.operation}")
                            0L
                        }
                    }

                    // adjust for relief
                    itemWorryLevel = itemWorryLevelAsLong.mod(uberTest)

                    // now throw it
                    val recipient = when (itemWorryLevel.mod(monkey.testValue) == 0) {
                        true -> {
                            monkey.trueRecipient
                        }

                        false -> {
                            monkey.falseRecipient
                        }
                    }
                    monkeys[recipient].startingItems.add(itemWorryLevel)
                }
                // we've now thrown all the items
                monkey.startingItems.clear()
            }

//            if (round % 1000 == 0 || round == 1 || round == 20) {
//                // what's the state at the end of the round?
//                println("After round $round:")
//                inspections.forEachIndexed { index, i ->
//                    println("Monkey $index inspected items $i times")
//                }
//            }
        }

        val sortedByInspections = inspections.sorted().reversed()
        return sortedByInspections[0].toLong().times(sortedByInspections[1])
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")


    check(part1(testInput) == 10605)

    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(part2(testInput) == 2713310158)

    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: $part2 - took ${duration.toMillis()}ms")
}
package day9

import howLong
import readInput
import java.lang.Math.abs
import java.time.LocalDateTime

enum class Direction() {
    U,
    D,
    L,
    R
}

fun isAdjacent(a : Pair<Int,Int>, b : Pair<Int,Int>) : Boolean = (abs(a.first - b.first) <= 1 && abs(a.second - b.second) <= 1)

fun moveTowards(head : Pair<Int,Int>, currentTail : Pair<Int,Int>) : Pair<Int,Int> {
    if (isAdjacent(head, currentTail)) return currentTail

    var newTail = currentTail
    val horizontalGap = head.first - currentTail.first
    val verticalGap = head.second - currentTail.second
    val hasDiagonalGap = abs(horizontalGap) >= 1 && abs(verticalGap) >= 1

    if (abs(horizontalGap) > 1 || hasDiagonalGap) {
        // need to move towards head horizontally
        if (horizontalGap < 0) {
            // negative gap - need to subtract one
            newTail = Pair(newTail.first-1, newTail.second)
        } else {
            // positive gap - need to add one
            newTail = Pair(newTail.first+1, newTail.second)
        }
    }

    if (abs(verticalGap) > 1 || hasDiagonalGap) {
        // need to move towards head vertically
        if (verticalGap < 0) {
            // negative gap - need to subtract one
            newTail = Pair(newTail.first, newTail.second-1)
        } else {
            // positive gap - need to add one
            newTail = Pair(newTail.first, newTail.second+1)
        }
    }

    return newTail
}

fun main() {

    val day = "day9"
    println("Output for $day")

    fun part1(input: List<String>) : Int {
        // initial state
        var h = Pair<Int,Int>(0,0)
        var t = Pair<Int,Int>(0,0)
        val tPositions = hashSetOf<Pair<Int,Int>>()
        tPositions.add(t)

        // now read and apply the moves
        input.forEach {move ->
            val moveSplit = move.split(' ')
            val direction = Direction.valueOf(moveSplit[0])
            val distance = moveSplit[1].toInt()

            (1..distance).forEach { step ->
                h = when (direction) {
                    Direction.U -> Pair(h.first, h.second+1)
                    Direction.D -> Pair(h.first, h.second-1)
                    Direction.L -> Pair(h.first-1, h.second)
                    Direction.R -> Pair(h.first+1, h.second)
                }
                val isAdjacent = isAdjacent(h, t)
                if (!isAdjacent) {
                    t = moveTowards(h, t)
                    tPositions.add(t)
                }
            }
        }
        return tPositions.size
    }

    fun part2(input: List<String>) : Int {
        // initial state
        val snake = arrayListOf<Pair<Int,Int>>(
            Pair<Int,Int>(0,0), // H
            Pair<Int,Int>(0,0), // 1
            Pair<Int,Int>(0,0), // 2
            Pair<Int,Int>(0,0), // 3
            Pair<Int,Int>(0,0), // 4
            Pair<Int,Int>(0,0), // 5
            Pair<Int,Int>(0,0), // 6
            Pair<Int,Int>(0,0), // 7
            Pair<Int,Int>(0,0), // 8
            Pair<Int,Int>(0,0)) // 9
        val tPositions = hashSetOf<Pair<Int,Int>>()
        tPositions.add(snake.last())

        // now read and apply the moves
        input.forEach {move ->
            val moveSplit = move.split(' ')
            val direction = Direction.valueOf(moveSplit[0])
            val distance = moveSplit[1].toInt()

            (1..distance).forEach { step ->
                snake[0] = when (direction) {
                    Direction.U -> Pair(snake[0].first, snake[0].second+1)
                    Direction.D -> Pair(snake[0].first, snake[0].second-1)
                    Direction.L -> Pair(snake[0].first-1, snake[0].second)
                    Direction.R -> Pair(snake[0].first+1, snake[0].second)
                }

                // now we've moved the head, need to move each part of the body in turn
                for (ii in 1 until snake.size) {
                    val isAdjacent = isAdjacent(snake[ii-1], snake[ii])
                    if (!isAdjacent) {
                        snake[ii] = moveTowards(snake[ii-1], snake[ii])
                    }
                }
                // record the potentially new tail position
                tPositions.add(snake.last())
            }
        }
        return tPositions.size
    }

    val testInput = readInput(day, "test.txt")
    val test2Input = readInput(day, "test2.txt")
    val input = readInput(day, "input.txt")

    check(part1(testInput) == 13)

    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(part2(testInput) == 1)
    check(part2(test2Input) == 36)

    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: $part2 - took ${duration.toMillis()}ms")
}
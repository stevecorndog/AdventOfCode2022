package day8

import howLong
import readInputAsIntList
import java.lang.Integer.max
import java.time.LocalDateTime

fun main() {

    val day = "day8"
    println("Output for $day")

    fun findVisibleTrees(input: List<List<Int>>): HashSet<Pair<Int, Int>> {
        // use a set to avoid double counting trees from different directions
        val visibleTrees = hashSetOf<Pair<Int, Int>>()

        // row by row, how many trees can we see
        input.forEachIndexed { row, treeRow ->
            // left to right
            var highestYet = -1
            treeRow.forEachIndexed { col, tree ->
                if (tree > highestYet) {
                    visibleTrees.add(Pair(row, col))
                    highestYet = tree
                }
            }
            // right to left
            highestYet = -1
            treeRow.reversed().forEachIndexed { index, tree ->
                val col = treeRow.size - index - 1
                if (tree > highestYet) {
                    visibleTrees.add(Pair(row, col))
                    highestYet = tree
                }
            }
        }

        // column by column, how many trees can we see
        val numCols = input.first().size
        for (col in 0 until numCols) {
            // topToBottom
            var highestYet = -1
            input.forEachIndexed { row, treeRow ->
                val treeHeight = treeRow[col]
                if (treeHeight > highestYet) {
                    visibleTrees.add(Pair(row, col))
                    highestYet = treeHeight
                }
            }
            // Bottom to Top
            highestYet = -1
            input.reversed().forEachIndexed { index, treeRow ->
                val row = input.size - index - 1
                val treeHeight = treeRow[col]
                if (treeHeight > highestYet) {
                    visibleTrees.add(Pair(row, col))
                    highestYet = treeHeight
                }
            }
        }
        return visibleTrees
    }

    fun part1(input: List<List<Int>>) : Int {
        return findVisibleTrees(input).size
    }


    fun scenicScore(input: List<List<Int>>, tree:Pair<Int,Int>) : Int {
        // anything on the edge scores 0
        if (tree.first == 0 || tree.second == 0 || tree.first == input.size -1 || tree.second == input.first().size -1)
            return 0

        var score = 1
        val treeHeight = input[tree.first][tree.second]

        // how far above can we see?
        var visibility = 0
        for (row in tree.first - 1 downTo 0) {
            visibility++
            // if the tree we see is the same height or higher, we can't see further
            if (input[row][tree.second] >= treeHeight) break
        }
        score *= visibility

        // how far left can we see?
        visibility = 0
        for (col in tree.second - 1 downTo 0) {
            visibility++
            // if the tree we see is the same height or higher, we can't see further
            if (input[tree.first][col] >= treeHeight) break
        }
        score *= visibility

        // how far right can we see?
        visibility = 0
        for (col in tree.second + 1 until input.first().size) {
            visibility++
            // if the tree we see is the same height or higher, we can't see further
            if (input[tree.first][col] >= treeHeight) break
        }
        score *= visibility

        // how far below can we see?
        visibility = 0
        for (row in tree.first + 1 until input.size) {
            visibility++
            // if the tree we see is the same height or higher, we can't see further
            if (input[row][tree.second] >= treeHeight) break
        }
        score *= visibility

        return score
    }


    fun part2(input: List<List<Int>>) : Int {
        var bestScenicScore = 0
        val visibleTrees = findVisibleTrees(input)
        visibleTrees.forEach { tree ->
            bestScenicScore = max(bestScenicScore, scenicScore(input, tree))
        }
        return bestScenicScore
    }

    val testInput = readInputAsIntList(day, "test.txt")
    val input = readInputAsIntList(day, "input.txt")


    check(part1(testInput) == 21)

    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(scenicScore(testInput, Pair(1,2)) == 4)
    check(scenicScore(testInput, Pair(3,2)) == 8)
    check(part2(testInput) == 8)

    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: $part2 - took ${duration.toMillis()}ms")
}
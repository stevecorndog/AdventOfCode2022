package day12

import howLong
import readInput
import java.time.LocalDateTime

fun main() {

    val day = "day12"
    println("Output for $day")

    fun part1(input: List<String>) : Int {
        val terrainMap = TerrainMap(input)
        terrainMap.findShortestRoutes(terrainMap.start)
        return terrainMap.getShortestRouteTo(terrainMap.finish)
    }

    fun part2(input: List<String>) : Int {
        val terrainMap = ReverseTerrainMap(input)
        terrainMap.findShortestRoutes(terrainMap.finish)
        return terrainMap.getMostScenicPath()
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")


    check(part1(testInput) == 31)


    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(part2(testInput) == 29)

    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: $part2 - took ${duration.toMillis()}ms")
}
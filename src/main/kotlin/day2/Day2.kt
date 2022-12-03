package day2

import readInput

fun main() {

    val day = "day2"
    println("Output for $day")


    fun part1(input: List<String>) : Int {
        var score = 0

        input.forEach {
//            println(it)

            val opponent = when(it[0]) {
                'A' -> Move.ROCK
                'B' -> Move.PAPER
                'C' -> Move.SCISSORS
                else -> Move.UNKNOWN
            }
            val self = when(it[2]) {
                'X' -> Move.ROCK
                'Y' -> Move.PAPER
                'Z' -> Move.SCISSORS
                else -> Move.UNKNOWN
            }
            score += self.value
            if (beats(self, opponent)) {
//                println( "self:${self.name} opponent:${opponent.name} - WIN I score 6")
                score += 6
            } else if (beats(opponent, self)){
//                println( "self:${self.name} opponent:${opponent.name} - LOSE I score 0")
            } else {
//                println( "self:${self.name} opponent:${opponent.name} - DRAW I score 3")
                score += 3
            }
        }
        return score
    }

    fun part2(input: List<String>) : Int {
        var score = 0

        input.forEach {
//            println(it)

            val opponent = when(it[0]) {
                'A' -> Move.ROCK
                'B' -> Move.PAPER
                'C' -> Move.SCISSORS
                else -> Move.UNKNOWN
            }

            val self = when (it[2]) {
                'X' -> {
                    // need to lose
                    when (opponent) {
                        Move.ROCK -> Move.SCISSORS
                        Move.PAPER -> Move.ROCK
                        Move.SCISSORS -> Move.PAPER
                        Move.UNKNOWN -> Move.UNKNOWN
                    }
                }
                'Y' -> {
                    //need to draw
                    opponent
                }
                'Z' -> {
                    // need to win
                    when (opponent) {
                        Move.ROCK -> Move.PAPER
                        Move.PAPER -> Move.SCISSORS
                        Move.SCISSORS -> Move.ROCK
                        Move.UNKNOWN -> Move.UNKNOWN
                    }
                }
                else -> { Move.UNKNOWN}
            }

            score += self.value
            if (beats(self, opponent)) {
//                println( "self:${self.name} opponent:${opponent.name} - WIN I score 6")
                score += 6
            } else if (beats(opponent, self)){
//                println( "self:${self.name} opponent:${opponent.name} - LOSE I score 0")
            } else {
//                println( "self:${self.name} opponent:${opponent.name} - DRAW I score 3")
                score += 3
            }
        }
        return score
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")

    check(part1(testInput) == 15)
    println("Part 1 answer: ${part1(input)}")

    check(part2(testInput) == 12)
    println("Part 2 answer: ${part2(input)}")
}
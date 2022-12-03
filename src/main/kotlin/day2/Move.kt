package day2

enum class Move(val value : Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3),
    UNKNOWN(-1);

}

fun beats(self : Move, opponent : Move) : Boolean {
    return when(opponent) {
        Move.ROCK -> self == Move.PAPER
        Move.PAPER -> self == Move.SCISSORS
        Move.SCISSORS -> self == Move.ROCK
        Move.UNKNOWN -> false
    }
}

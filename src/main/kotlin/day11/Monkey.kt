package day11

class Monkey(notes : List<String>) {

    val startingItems = ArrayList<Int>()
    val operation : String
    val testValue : Int
    val trueRecipient : Int
    val falseRecipient : Int

    init {
        startingItems.addAll(notes[1].drop("  Starting items: ".length).split(" ", ",").filterNot { it.isBlank() }.map{ it.toInt()})
        operation = notes[2].split('=')[1].trim().drop("old ".length)
        testValue = notes[3].trim().drop("Test: divisible by ".length).toInt()
        trueRecipient = notes[4].trim().drop("If true: throw to monkey ".length).toInt()
        falseRecipient = notes[5].trim().drop("If false: throw to monkey ".length).toInt()
    }

    override fun toString(): String {
        return "Monkey(startingItems=$startingItems, operation='$operation', testValue=$testValue, trueRecipient=$trueRecipient, falseRecipient=$falseRecipient)"
    }
}

import java.io.File
import java.lang.AssertionError
import java.time.Duration
import java.time.LocalDateTime

fun readInput(day:String, name:String) = File("src/main/kotlin/$day", name)
    .readLines()

fun readInputAsInts(day:String, name:String) = File("src/main/kotlin/$day", name)
    .readLines()
    .map {
        it.toInt()
    }

fun readInputAsIntList(day:String, name:String) = File("src/main/kotlin/$day", name)
    .readLines()
    .map {
        it.toCharArray()
            .map { digit -> digit.digitToInt() }
    }

fun check(test:Boolean) {
    if (!test) {
        throw AssertionError("Test failed")
    }
}


fun hexToBinaryString(hex: String) : String {
    var binaryString = ""
    hex.forEach {
        val i = Integer.parseInt(it.toString(), 16)
        binaryString += Integer.toBinaryString(i).padStart(4, '0')
    }
    return binaryString
}

fun howLong(start : LocalDateTime): Duration {
    return Duration.between(start, LocalDateTime.now())
}

data class Point(val x:Int, val y:Int)



package day7

import howLong
import readInput
import java.time.LocalDateTime

fun main() {

    val day = "day7"
    println("Output for $day")

    fun parseTerminalOutput(input: List<String>): Directory {
        val root = Directory("/", null)
        var currentDirectory = root

        val parser = TerminalOutputParser()
        input.drop(1).forEach { line ->
            when (val output = parser.parse(line)) {
                is DirectoryTerminalOutput -> {
                    // add a Directory to the current directory
                    val directory = Directory(output.value, currentDirectory)
                    currentDirectory.subdirectories.add(directory)
                }

                is FileTerminalOutput -> {
                    // add a file to the current directory
                    currentDirectory.fileSizes += output.size
                }

                is CommandTerminalOutput -> {
                    // cd or ls.  Nothing to do with ls
                    if (output.value.startsWith("cd")) {
                        when (val directoryName = output.value.split(' ')[1]) {
                            ".." -> {
                                // move up to parent dir
                                currentDirectory = currentDirectory.parent!!
                            }

                            else -> {
                                // change the current directory to the appropriate child directory
                                val directory = currentDirectory.subdirectories.find { it.name == directoryName }
                                if (directory == null) {
                                    println("Failed to find child directory $directoryName of directory ${currentDirectory.name}")
                                } else {
                                    currentDirectory = directory
                                }
                            }
                        }
                    }
                }

                else -> {
                    println("Unexpected terminal output type: $output")
                }
            }
        }
        return root
    }

    fun part1(input: List<String>) : Int {

        val root = parseTerminalOutput(input)

        // to calculate the result, tree walk, adding the sizes of directories <= 100000
        val directories = ArrayList<Directory>()
        root.collectDirectoriesWithFileSizeLimit(100000, directories)

        return directories.sumOf { it.size() }
    }

    fun part2(input: List<String>) : Int {
        val root = parseTerminalOutput(input)

        val fsTotal = 70000000
        val fsFree = fsTotal - root.size()
        val fsRequired = 30000000
        // which directories would free up 30000000?
        val directories = ArrayList<Directory>()
        root.collectDirectoriesWithFileSizeLimit(fsRequired, directories)

        // find the smallest dir that frees up enough space
        for (directory in directories.sortedBy { it.size() }) {
            if ((fsFree + directory.size()) >= fsRequired) return directory.size()
        }
        return 0
    }

    val testInput = readInput(day, "test.txt")
    val input = readInput(day, "input.txt")


    check(part1(testInput) == 95437)

    var startTime = LocalDateTime.now()
    val part1 = part1(input)
    var duration = howLong(startTime)
    println("Part 1 answer: $part1 - took ${duration.toMillis()}ms")

    check(part2(testInput) == 24933642)

    startTime = LocalDateTime.now()
    val part2 = part2(input)
    duration = howLong(startTime)
    println("Part 2 answer: $part2 - took ${duration.toMillis()}ms")
}
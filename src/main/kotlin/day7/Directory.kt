package day7

class TerminalOutputParser() {
    fun parse(output : String) : Any {
        if (output.startsWith("$")) {
            return CommandTerminalOutput(output.drop(2))
        }
        if (output.startsWith("dir")) {
            return DirectoryTerminalOutput(output.split(' ')[1])
        }
        return FileTerminalOutput(output.split(' ')[0].toInt(), "")
    }
}

data class CommandTerminalOutput(val value : String)
data class DirectoryTerminalOutput(val value : String)
data class FileTerminalOutput(val size : Int, val name : String)

class Directory (val name : String, val parent : Directory?) {
    val subdirectories = arrayListOf<Directory>()
    var fileSizes = 0

    fun size() : Int {
        return subdirectories.sumOf { it.size() } + fileSizes
    }

    fun collectDirectoriesWithFileSizeLimit(limit : Int, directories : ArrayList<Directory>) {
        if (size() <= limit) directories.add(this)
        subdirectories.forEach {
            it.collectDirectoriesWithFileSizeLimit(limit, directories)
        }
    }
}


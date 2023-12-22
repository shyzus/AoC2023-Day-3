import java.io.File
import java.lang.IndexOutOfBoundsException
import kotlin.system.exitProcess

File("input.txt").useLines { lines ->

    val parsedLines = ArrayList<HashMap<Int, String>>()

    lines.forEachIndexed { index, line ->
        if( index > 3) {
            return@forEachIndexed
        }

        val buffer = hashMapOf<Int, String>()

        line.forEachIndexed { idx, char ->
            if (char.isDigit()) {
                buffer[idx] = char.toString()
            } else if (!char.isDigit() && !char.isLetter() && !char.isWhitespace() && char != '.') {
                buffer[idx] = char.toString()
            }
        }

        parsedLines.add(buffer)
    }

    println(parsedLines)

    exitProcess(0)

    parsedLines.forEachIndexed { index, hashMap ->
        var top: HashMap<Int, String>? = null
        try {
            top = parsedLines[index - 1]
        } catch (exception: IndexOutOfBoundsException) {
            // do nothing
        }

        var bot: HashMap<Int, String>? = null
        try {
            bot = parsedLines[index + 1]
        } catch (exception: IndexOutOfBoundsException) {
            // do nothing
        }

        hashMap.forEach { idx, char ->

        }

    }
}

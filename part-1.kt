import java.io.File

fun main() {

    val lines = File("input.txt").readLines()

    val partNumbers = ArrayList<Int>()

    lines.forEachIndexed { idx, line ->
        val top: String
        val mid: String
        val bot: String

        when (idx) {
            0 -> {
                top = ""
                mid = line
                bot = lines[1]
            }

            lines.size - 1 -> {
                top = lines[lines.size - 2]
                mid = line
                bot = ""
            }

            else -> {
                top = lines[idx - 1]
                mid = line
                bot = lines[idx + 1]
            }
        }

        println(top)
        println(mid)
        println(bot)

        partNumbers.addAll(getPartNumbers(top, mid, bot))

        println()
    }

    println(partNumbers.sum())

}

fun getPartNumbers(top: String, mid: String, bot: String): ArrayList<Int> {

    var topSymbols: ArrayList<Pair<Int, String>>? = null

    var botSymbols: ArrayList<Pair<Int, String>>? = null


    if (top.isNotEmpty()) {
        topSymbols = getSymbols(top)
    }

    if (bot.isNotEmpty()) {
        botSymbols = getSymbols(bot)
    }

    val midSymbols = getSymbols(mid)
    val midNumbers = getNumbers(mid)

    val partNumbers = ArrayList<Int>()

    println(midNumbers)

    midNumbers.forEach { pair ->
        val midRes = midSymbols.filter { symPair ->
            symPair.first == pair.first - 1 || symPair.first == (pair.first + pair.second.length)
        }

        val topRes = topSymbols?.filter { symPair ->
            symPair.first >= (pair.first - 1) && symPair.first <= (pair.first + pair.second.length + 1)
        }

        val botRes = botSymbols?.filter { symPair ->
            symPair.first >= (pair.first - 1) && symPair.first <= (pair.first + pair.second.length + 1)
        }

        if (midRes.isNotEmpty() || topRes?.isNotEmpty() == true || botRes?.isNotEmpty() == true) {
            partNumbers.add(pair.second.toInt())
        }
    }

    println(partNumbers)

    return partNumbers

}

fun getSymbols(line: String): ArrayList<Pair<Int, String>> {
    val symbols = ArrayList<Pair<Int, String>>()

    line.forEachIndexed { index, char ->
        if (!char.isLetterOrDigit() && !char.isWhitespace() && char != '.') {
            symbols.add(Pair(index, char.toString()))
        }
    }

    return symbols
}

fun getNumbers(line: String): ArrayList<Pair<Int, String>> {
    val numbers = ArrayList<Pair<Int, String>>()

    line.forEachIndexed { index, char ->
        if (char.isDigit()) {
            numbers.add(Pair(index, char.toString()))
        }
    }

    val actualNumbers = ArrayList<Pair<Int, String>>()

    var prev: Pair<Int, String>? = null

    numbers.forEach { pair ->

        if (prev?.first == (pair.first - 1)) {
            actualNumbers[actualNumbers.indexOf(actualNumbers.last())] =
                Pair(actualNumbers.last().first,
                    (actualNumbers.last().second + pair.second)
                )
        } else {
            actualNumbers.add(pair)
        }

        prev = pair

    }

    return actualNumbers
}
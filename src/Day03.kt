fun main() {

    fun part1(input: List<String>): Int {
        val sizeOfInput = input[0].length // Newline character
        val mostCommonThreshold = input.size / 2

        val bitCount = IntArray(sizeOfInput) { index ->
            input.sumOf { it[index].digitToInt() }
        }

        val gammaRate = bitCount
            .map { if (it > mostCommonThreshold) '1' else '0' }
            .joinToString("")
            .toInt(2)

        val epsilonRate = bitCount
            .map { if (it > mostCommonThreshold) '0' else '1' }
            .joinToString("")
            .toInt(2)

        return gammaRate * epsilonRate
    }


    fun part2(input: List<String>): Int {

        fun partitionAndFilter(input: List<String>, keepLogic: (List<String>, List<String>) -> List<String>) : String {
            val sizeOfInput = input[0].length
            var values = input
            for (i in 0 until sizeOfInput) {
                val (ones, zeros) = values.partition { it[i] == '1' }
                values = keepLogic(ones, zeros) //if (ones.size >= zeros.size) ones else zeros
                if (values.size == 1) return values[0]
            }
            throw Exception("Uh oh")
        }

        fun getOxygenGeneratorRating(input: List<String>): String {
            return partitionAndFilter(input) { ones, zeros -> if (ones.size >= zeros.size) ones else zeros }
        }

        fun getCO2ScrubberRating(input: List<String>): String {
           return partitionAndFilter(input) { ones, zeros -> if (zeros.size <= ones.size) zeros else ones }
        }

        val first = getOxygenGeneratorRating(input).toInt(2)
        val second = getCO2ScrubberRating(input).toInt(2)
        return first * second
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

import kotlin.math.ceil

fun main() {

    fun part1(input: List<String>): Int {

        val bitCount = IntArray(12) { 0 }

        for (i in input) {
            val charArray = i.toCharArray()
            for (j in 0 until (charArray.size - 1)) {
                if (charArray[j] == '1') bitCount[j]++
            }
        }

        val gammaRate = bitCount.map { if (it > (input.size / 2)) '1' else '0' }.joinToString("").toInt(2)
        val epsilonRate = bitCount.map { if (it > (input.size / 2)) '0' else '1' }.joinToString("").toInt(2)

        return gammaRate * epsilonRate
    }

    fun getFirst(input: List<String>): String {
        var values = input
        for(i in 0 until input[0].length) {
            val mostCommonBit = if(values.sumOf { it[i].digitToInt() } >= (ceil(values.size.toFloat() / 2))) '1' else '0'
            println("Most common bit in position i: $mostCommonBit")
            println("Values before filter: $values")
            values = values.filter { it[i] == mostCommonBit }
            println("Values after filter: $values")
            if (values.size == 1) return values[0]
        }
        throw Exception("Uh oh")
    }

    fun getSecond(input: List<String>): String {
        var values = input
        for(i in 0 until input[0].length) {
            val leastCommonBit = if(values.sumOf { it[i].digitToInt() } >= (ceil(values.size.toFloat() / 2))) '0' else '1'
            values = values.filter { it[i] == leastCommonBit }
            if (values.size == 1) return values[0]
        }
        throw Exception("Uh oh")
    }

    fun part2(input: List<String>): Int {
       val first = getFirst(input).toInt(2)
        val second = getSecond(input).toInt(2)
        return first * second
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    //check(part1(testInput) == 198)
    println(getFirst(testInput))
    check(getFirst(testInput) == "10111")
    println(getSecond(testInput))
    check(getSecond(testInput) == "01010")
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

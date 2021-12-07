import kotlin.math.abs

fun main() {

    fun partOneFuelCost(position: Int, map: Map<Int, Int>): Int {
        return map.keys.sumOf {
            val numberOfCrabsAtPosition = map[it] ?: 0
            val moveAmount = abs(it - position)
            moveAmount * numberOfCrabsAtPosition }
    }

    fun partTwoFuelCost(position: Int, map: Map<Int, Int>): Int {
        fun sumOfNaturalNumbers(i: Int) = i * (i + 1) / 2

        return map.keys.sumOf {
            val numberOfCrabsAtPosition = map[it] ?: 0
            val moveAmount = abs(it - position)
            sumOfNaturalNumbers(moveAmount) * numberOfCrabsAtPosition
        }
    }

    fun calculate(input: List<String>, calc: (Int, Map<Int, Int>) -> Int): Int {
        val map = input[0].split(",")
            .map { it.toInt() }
            .groupingBy { it }
            .eachCount()

        val minPosition = map.keys.minOf { it }
        val maxPosition = map.keys.maxOf { it }
        return IntRange(minPosition, maxPosition)
            .map { calc(it, map) }
            .minOf { it }
    }


    fun part1(input: List<String>): Int {
        return calculate(input, ::partOneFuelCost)
    }

    fun part2(input: List<String>): Int {
        return calculate(input, ::partTwoFuelCost)
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
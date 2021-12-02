fun main() {
    fun count(input: List<Int>): Int {
        var count = 0
        for(i in 1 until input.size) {
            if (input[i-1] < input[i]) count++
        }
        return count
    }

    fun part1(input: List<String>): Int {
        return count(input.map { it.toInt() })
    }

    fun part2(input: List<String>): Int {
        val windows = input.map{ it.toInt() }.windowed(3, 1).map { it.sum() }
        return count(windows)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

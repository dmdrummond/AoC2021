fun main() {

    fun part1(input: List<String>): Int {
        var depth = 0
        var position = 0
        for (i in input) {
            val (direction, amountString) = i.split(" ")
            val amount = amountString.toInt()
            when (direction) {
                "up" -> depth -= amount
                "down" -> depth += amount
                "forward" -> position += amount
            }
        }

        return position * depth
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var position = 0
        var aim = 0
        for (i in input) {
            val (direction, amountString) = i.split(" ")
            val amount = amountString.toInt()
            when (direction) {
                "up" -> aim -= amount
                "down" -> aim += amount
                "forward" -> {
                    position += amount
                    depth += amount * aim
                }
            }
        }

        return position * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

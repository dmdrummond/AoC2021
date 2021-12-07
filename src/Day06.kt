
fun main() {

    fun part1(input: List<String>): Int {
       var lanternFish = input[0].split(",").map { it.toInt() }.toMutableList()

        for (i in 0 until 80) {
            var numberToAdd = 0
            lanternFish = lanternFish.map {
                if (it == 0) {
                    numberToAdd++
                    6
                } else {
                    it - 1
                }
            }.toMutableList()
            val newFish = List (numberToAdd) { 8 }
            lanternFish.addAll(newFish)
        }
        return lanternFish.size
    }

    fun part2(input: List<String>): Long {
        val lanternFishList = input[0].split(",").map { it.toInt() }

        var  lanternFish: MutableMap<Int, Long> = mutableMapOf()
        IntRange(0, 8).forEach { n ->
            lanternFish[n] = lanternFishList.count { it == n }.toLong()
        }

        repeat(256) {
            val updatedMap: MutableMap<Int, Long> = mutableMapOf()
            IntRange(0, 8).forEach {
                updatedMap[it] =
                    lanternFish.getOrDefault((it + 1) % 9, 0) + if (it == 6) lanternFish.getOrDefault(0, 0) else 0
            }
            lanternFish = updatedMap
        }
        return lanternFish.values.sum()
    }

    val testInput = readInput("Day06_test")
    val part1Test = part1(testInput)
    check(part1Test == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
fun main() {

    fun parseInput(input: List<String>): Pair<List<List<String>>, List<List<String>>> {
        val inputs: MutableList<List<String>> = mutableListOf()
        val outputValues: MutableList<List<String>> = mutableListOf()
        input.forEach { string ->
            val (one, two) = string.split("|")
            inputs.add(one.trim().split(" ").map { it.toList().sorted().joinToString("") })
            outputValues.add(two.trim().split(" ").map { it.toList().sorted().joinToString("") })
        }
        return Pair(inputs, outputValues)
    }

    fun part1(input: List<String>): Int {
        val (_, outputValues) = parseInput(input)
        return outputValues.flatten().count { it.length in listOf(2, 4, 3, 7) }
    }

    fun order(input: List<String>): List<String> {
        val one = input.first { it.length == 2 }
        val four = input.first { it.length == 4 }
        val seven = input.first { it.length == 3 }
        val eight = input.first { it.length == 7 }

        val ofLengthFive = input.filter { it.length == 5 }.toMutableList()
        val ofLengthSix = input.filter { it.length == 6 }.toMutableList()

        val charCounts = input.joinToString("").groupingBy { it }.eachCount()
        val b = charCounts.entries.first { it.value == 9 }.key
        val d = seven.first { it !in one.toCharArray() }
        val e = charCounts.entries.first { it.value == 6 }.key
        val g = charCounts.entries.first { it.value == 4 }.key

        val nine = ofLengthSix.first {
            !(b in it && d in it && e in it && g in it)
        }
        ofLengthSix.remove(nine)
        val zero = ofLengthSix.first { it.contains(one[0]) && it.contains(one[1]) }
        ofLengthSix.remove(zero)
        val six = ofLengthSix.first()
        val five = ofLengthFive.first { it.contains(e) }
        ofLengthFive.remove(five)
        val three = ofLengthFive.first { it.contains(one[0]) && it.contains(one[1]) }
        ofLengthFive.remove(three)
        val two = ofLengthFive.first()
        return listOf(zero, one, two, three, four, five, six, seven, eight, nine)
    }

    fun part2(input: List<String>): Int {
        val (observedValues, outputValues) = parseInput(input)

        val orderedValues = observedValues.map { order(it) }
        return outputValues.mapIndexed { index, strings ->
            strings.joinToString("") {
                orderedValues[index].indexOf(it).toString()
            }.toInt() }.sum()
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
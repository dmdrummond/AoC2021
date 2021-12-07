import java.awt.Point
import kotlin.math.min
import kotlin.math.max
import kotlin.math.sign

fun main() {

    fun createPoints(input: String, processDiagonals: Boolean): List<Point> {
        val arrowStart = input.indexOf("-")
        val (x1Str, y1Str) = input.substring(0, arrowStart - 1).split(",")
        val (x2Str, y2Str) = input.substring(arrowStart + 3).split(",")

        val (x1, x2, y1, y2) = listOf(x1Str, x2Str, y1Str, y2Str).map { it.toInt() }
        return when {
            x1 == x2 -> {
                //vertical line
                val first = min(y1, y2)
                val last = max(y1, y2)
                IntRange(first, last).map { Point(x1, it) }.toList()
            }
            y1 == y2 -> {
                //horizontal line
                val first = min(x1, x2)
                val last = max(x1, x2)
                IntRange(first, last).map { Point(it, y1) }.toList()
            }
            processDiagonals -> {
                val signX = sign((x2 - x1).toDouble()).toInt()
                val signY = sign((y2 - y1).toDouble()).toInt()

                List(((x2-x1) * signX) + 1) {
                    Point(x1 + (it * signX), y1 + (it * signY))
                }
            }
            else -> emptyList()
        }
    }

    fun process(input: List<String>, processDiagonals: Boolean): Int {
        return input
            .map { createPoints(it, processDiagonals) }
            .flatten()
            .groupingBy { it.toString() }
            .eachCount()
            .filterValues { it >= 2 }
            .count()
    }

    fun part1(input: List<String>): Int {
        return process(input, false)
    }

    fun part2(input: List<String>): Int {
       return process(input, true)
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
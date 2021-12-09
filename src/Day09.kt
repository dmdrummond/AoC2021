fun main() {

    fun findLowPoints(heightMap: List<List<Int>>): List<Pair<Int, Int>> {
        val lowPoints: MutableList<Pair<Int, Int>> = mutableListOf()
        heightMap.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, value ->
                val left = if(columnIndex == 0) Int.MAX_VALUE else row[columnIndex - 1]
                val right = if(columnIndex == row.size - 1) Int.MAX_VALUE else row[columnIndex + 1]
                val top = if(rowIndex == 0) Int.MAX_VALUE else heightMap[rowIndex - 1][columnIndex]
                val bottom = if(rowIndex == heightMap.size - 1) Int.MAX_VALUE else heightMap[rowIndex + 1][columnIndex]
                if (value < left && value < right && value < top && value < bottom) lowPoints.add(Pair(rowIndex, columnIndex))
            }
        }
        return lowPoints
    }

    fun part1(input: List<String>): Int {
        val heightMap = input.map { it.toList().map { char -> char.digitToInt() } }
        return findLowPoints(heightMap).map { heightMap[it.first][it.second] }.sumOf { it + 1 }
    }

    fun touchingPointsInBasin(heightMap: List<List<Int>>, point: Pair<Int, Int>): List<Pair<Int, Int>> {
        val points: MutableList<Pair<Int, Int>> = mutableListOf()
        if (point.first > 0 && heightMap[point.first - 1][point.second] < 9) points.add(Pair(point.first - 1, point.second))
        if (point.first < heightMap.size -1 && heightMap[point.first + 1][point.second] < 9) points.add(Pair(point.first +1, point.second))
        if (point.second > 0 && heightMap[point.first][point.second - 1] < 9) points.add(Pair(point.first, point.second - 1))
        if (point.second < heightMap[point.first].size -1 && heightMap[point.first][point.second + 1] < 9) points.add(Pair(point.first, point.second + 1))
        return points
    }

    fun findBasinSize(heightMap: List<List<Int>>, lowPoint: Pair<Int, Int>): Int {
        val pointsInBasin: MutableSet<Pair<Int, Int>> = mutableSetOf(lowPoint)

        var pointsAdded = true
        while (pointsAdded) {
            val pointsToAdd: MutableSet<Pair<Int, Int>> = mutableSetOf()
            pointsAdded = false
            pointsInBasin.forEach {
                val newPoints = touchingPointsInBasin(heightMap, it)
                newPoints.forEach { newPoint ->
                    if (!pointsInBasin.contains(newPoint)) {
                        pointsToAdd.add(newPoint)
                    }
                }
            }
            if (pointsToAdd.isNotEmpty()) {
                pointsAdded = true
                pointsInBasin.addAll(pointsToAdd)
            }
        }
        return pointsInBasin.size
    }



    fun part2(input: List<String>): Int {
        val heightMap = input.map { it.toList().map { char -> char.digitToInt() } }
        return findLowPoints(heightMap)
            .map { findBasinSize(heightMap, it) }
            .sorted()
            .reversed()
            .take(3)
            .reduce { acc, i -> acc * i }
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
fun main() {

    fun part1(input: List<String>): Int {
        val numbersCalled = input[0].split(",").map { it.toInt() }
        val boards: MutableList<BingoBoard> = mutableListOf()

        val numBoards = (input.size - 1) / 6

        var winningBoard = -1

        for (i in 0 until numBoards) {
            boards.add(BingoBoard.from(
                input[(i * 6) + 2],
                input[(i * 6) + 3],
                input[(i * 6) + 4],
                input[(i * 6) + 5],
                input[(i * 6) + 6],
            ))
        }
        var numberCalledIndex = 0

        while (winningBoard < 0) {
            val numberCalled = numbersCalled[numberCalledIndex]
            for(i in boards.indices) {
                boards[i].mark(numberCalled)
                if (boards[i].hasWon) {
                    winningBoard = i
                    break
                }
            }
            numberCalledIndex++
        }
        return boards[winningBoard].total * numbersCalled[numberCalledIndex - 1]

    }

    fun part2(input: List<String>): Int {
        val numbersCalled = input[0].split(",").map { it.toInt() }
        var boards: MutableList<BingoBoard> = mutableListOf()

        val numBoards = (input.size - 1) / 6

        for (i in 0 until numBoards) {
            boards.add(BingoBoard.from(
                input[(i * 6) + 2],
                input[(i * 6) + 3],
                input[(i * 6) + 4],
                input[(i * 6) + 5],
                input[(i * 6) + 6],
            ))
        }
        var numberCalledIndex = 0

        while (true) {
            val numberCalled = numbersCalled[numberCalledIndex]
            for(i in boards.indices) {
                boards[i].mark(numberCalled)
                if (boards[i].hasWon) {
                    if (boards.size == 1) {
                        return boards[i].total * numberCalled
                    }
                }
            }
            boards = boards.filter { !it.hasWon }.toMutableList()
            numberCalledIndex++
        }
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    //check(part2(testInput) == 0)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

class BingoBoard (
    board: List<List<Int>>
) {
    val values = IntArray(25) { i ->
        board[i / 5][i % 5]
    }
    val markedOff = BooleanArray(25) { false }

    fun mark(value: Int) {
        val index = values.indexOf(value)
        if (index >= 0) markedOff[index] = true
    }

    val hasWon: Boolean
    get() {
        return (markedOff[0] && markedOff[5] && markedOff[10] && markedOff[15] && markedOff[20])
                || (markedOff[1] && markedOff[6] && markedOff[11] && markedOff[16] && markedOff[21])
                || (markedOff[2] && markedOff[7] && markedOff[12] && markedOff[17] && markedOff[22])
                || (markedOff[3] && markedOff[8] && markedOff[13] && markedOff[18] && markedOff[23])
                || (markedOff[4] && markedOff[9] && markedOff[14] && markedOff[19] && markedOff[24])

                || (markedOff[0] && markedOff[1] && markedOff[2] && markedOff[3] && markedOff[4])
                || (markedOff[5] && markedOff[6] && markedOff[7] && markedOff[8] && markedOff[9])
                || (markedOff[10] && markedOff[11] && markedOff[12] && markedOff[13] && markedOff[14])
                || (markedOff[15] && markedOff[16] && markedOff[17] && markedOff[18] && markedOff[19])
                || (markedOff[20] && markedOff[21] && markedOff[22] && markedOff[23] && markedOff[24])
    }

    val total: Int
    get() {
        return values.mapIndexed { i, value -> if (markedOff[i]) 0 else value }.sum()
    }

    companion object {
        val whitespaceRegex = "\\s+".toRegex()
    fun from(row0: String, row1: String, row2: String, row3: String, row4: String): BingoBoard {
        return BingoBoard(listOf(
            row0.trim().split(whitespaceRegex).map { it.toInt() },
            row1.trim().split(whitespaceRegex).map { it.toInt() },
            row2.trim().split(whitespaceRegex).map { it.toInt() },
            row3.trim().split(whitespaceRegex).map { it.toInt() },
            row4.trim().split(whitespaceRegex).map { it.toInt() }
            ))

    }
}
}
package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle

@Puzzle("day07/input.txt")
object Day07: AdventChallenge {

    private const val START = 'S'
    private const val SPLITTER = '^'

    override fun part1(inputString: String): Any {
        val manifold = inputString.lines().map { row ->
            row.toCharArray().toMutableList()
        }
        val start = manifold[0].indexOf(START)
        var splitCounter = 0

        var currentRow = mutableSetOf(start)
        for(y in 1..<manifold.size) {
            val nextRow = mutableSetOf<Int>()
            for(x in currentRow) {
                val pos = manifold[y][x]
                if(pos == SPLITTER) {
                    nextRow.add(x - 1)
                    nextRow.add(x + 1)
                    splitCounter++
                } else {
                    nextRow.add(x)
                }
            }
            currentRow = nextRow
        }
        return splitCounter
    }

    private data class Position(val x: Int, val y: Int)

    override fun part2(inputString: String): Any {

        val manifold = inputString.lines().map { row ->
            row.toCharArray().toMutableList()
        }
        val start = manifold[0].indexOf(START)
        val counterMap: MutableMap<Position, Long> = mutableMapOf()

        return countBeamsFromSplitter(start, 1, manifold, counterMap)
    }

    private fun countBeamsFromSplitter(x: Int, y: Int, manifold: List<List<Char>>, memory: MutableMap<Position, Long>): Long {
        if(manifold.size == y) {
            return 1
        }

        val pos = manifold[y][x]
        if(pos == SPLITTER) {
            val splitter = Position(x, y)
            var count = memory[splitter]
            if(count == null) {
                count = countBeamsFromSplitter(splitter.x - 1, splitter.y + 1, manifold, memory) +
                        countBeamsFromSplitter(splitter.x + 1, splitter.y + 1, manifold, memory)
                memory[splitter] = count
            }
            return count
        } else {
            return countBeamsFromSplitter(x, y + 1, manifold, memory)
        }
    }
}
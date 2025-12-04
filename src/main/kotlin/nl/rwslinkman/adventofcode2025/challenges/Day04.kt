package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle

@Puzzle("day04/input.txt")
object Day04: AdventChallenge {

    private const val PAPER_ROLL = '@'
    private const val FLOOR = '.'
    private val directions = listOf(
        0 to -1,    // left
        -1 to -1,   // left-top
        -1 to 0,    // top
        -1 to 1,    // right-top
        0 to 1,     // right
        1 to 1,     // right-bottom
        1 to 0,     // bottom
        1 to -1     // left-bottom
    )

    override fun part1(inputString: String): Any {
        val map = inputString.lines().map { row ->
            row.toList()
        }

        val validPositions = mutableSetOf<Pair<Int,Int>>()
        map.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                if(cell == PAPER_ROLL) {
                    val neighbours = directions.count { direction ->
                        val other: Char? = try {
                            map[y+direction.first][x+direction.second]
                        } catch (_: Exception) { null }
                        other == PAPER_ROLL
                    }

                    if(neighbours < 4) {
                        validPositions.add(Pair(x, y))
                    }
                }
            }
        }
        return validPositions.size
    }

    override fun part2(inputString: String): Any {
        val map = inputString.lines().map { row ->
            row.toMutableList()
        }.toMutableList()

        val validPositions = mutableSetOf<Pair<Int,Int>>()
        validPositions.add(Pair(-1, -1))
        var totalCounter = 0
        while(validPositions.isNotEmpty()) {
            validPositions.clear()

            map.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    if(cell == PAPER_ROLL) {
                        val neighbours = directions.count { direction ->
                            val other: Char? = try {
                                map[y+direction.first][x+direction.second]
                            } catch (_: Exception) { null }
                            other == PAPER_ROLL
                        }

                        if(neighbours < 4) {
                            validPositions.add(Pair(x, y))
                        }
                    }
                }
            }

            if(validPositions.isEmpty()) {
                break
            } else {
                validPositions.forEach { pos ->
                    map[pos.second][pos.first] = FLOOR
                    totalCounter++
                }
            }
        }
        return totalCounter
    }
}
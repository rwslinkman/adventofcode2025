package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle

@Puzzle("day01/input.txt")
object Day01: AdventChallenge {

    private const val LEFT = 'L'
    private const val RIGHT = 'R'

    override fun part1(inputString: String): Any {
        val instructions = inputString.lines().map { line ->
            line[0] to line.substring(1).toInt()
        }

        var dialPosition = 50
        var zeroCounter = 0
        instructions.forEach { (k, v) ->
            when(k) {
                LEFT -> {
                    dialPosition -= (v % 100)
                    if(dialPosition < 0) {
                        dialPosition += 100
                    }
                }
                RIGHT -> {
                    dialPosition += (v % 100)
                    dialPosition %= 100
                }
            }
            if(dialPosition == 0) zeroCounter++
        }
        return zeroCounter
    }

    override fun part2(inputString: String): Any {
        val instructions = inputString.lines().map { line ->
            line[0] to line.substring(1).toInt()
        }

        var dialPosition = 50
        var zeroCounter = 0
        instructions.forEach { (k, v) ->
            when(k) {
                LEFT -> {
                    repeat(v) {
                        dialPosition -= 1
                        dialPosition %= 100
                        if(dialPosition == 0) {
                            zeroCounter++
                        }
                    }
                }
                RIGHT -> {
                    repeat(v) {
                        dialPosition += 1
                        dialPosition %= 100
                        if(dialPosition == 0) {
                            zeroCounter++
                        }
                    }
                }
            }
        }
        return zeroCounter
    }
}
package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle

@Puzzle("day03/input.txt")
object Day03: AdventChallenge {

    override fun part1(inputString: String): Any {
        val jolts = inputString.lines().map { bank ->

            var indexMax1 = 0
            (0..<(bank.length-1)).forEach { i ->
                if(bank[i].digitToInt() > bank[indexMax1].digitToInt()) {
                    indexMax1 = i
                }
            }
            var indexMax2 = indexMax1 + 1
            ((indexMax1+1)..<bank.length).forEach { j ->
                if(bank[j].digitToInt() > bank[indexMax2].digitToInt()) {
                    indexMax2 = j
                }
            }
            bank[indexMax1].digitToInt() * 10 + bank[indexMax2].digitToInt()
        }
        return jolts.sum()
    }

    override fun part2(inputString: String): Any {
        val batteriesNeeded = 12

        val jolts = inputString.lines().map { bank ->

            var best = 0L
            var indexMax = -1
            repeat(batteriesNeeded) { b ->
                indexMax++
                ((indexMax+1)..<(bank.length - batteriesNeeded + b + 1)).forEach {
                    if(bank[it].digitToInt() > bank[indexMax].digitToInt()) {
                        indexMax = it
                    }
                }
                best = best*10 + bank[indexMax].digitToInt()
            }
            best
        }
        return jolts.sum()
    }
}
package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle

@Puzzle("day05/input.txt")
object Day05: AdventChallenge {

    override fun part1(inputString: String): Any {
        val freshRanges = mutableListOf<LongRange>()
        val ingredients = mutableListOf<Long>()
        inputString.lines().forEach { line ->
            if(line.contains("-")) {
                val parts = line.split("-")
                val range = parts[0].toLong()..parts[1].toLong()
                freshRanges.add(range)
            } else if(line != "") {
                ingredients.add(line.toLong())
            }
        }
        return ingredients.count {
            freshRanges.any { range -> range.contains(it) }
        }
    }

    override fun part2(inputString: String): Any {
        val freshRanges = inputString.lines().mapNotNull{ line ->
            if(line.contains("-")) {
                val parts = line.split("-")
                val range = parts[0].toLong()..parts[1].toLong()
                range
            } else null
        }

        val sorted = freshRanges.sortedBy { it.first }
        var currentStart = sorted[0].first
        var currentEnd = sorted[0].last
        var total = 0L

        for (r in sorted.drop(1)) {
            if (r.first > currentEnd + 1) {
                total += (currentEnd - currentStart + 1)
                currentStart = r.first
                currentEnd = r.last
            } else {
                currentEnd = maxOf(currentEnd, r.last)
            }
        }
        total += (currentEnd - currentStart + 1)
        return total
    }
}
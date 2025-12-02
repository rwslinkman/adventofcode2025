package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle

@Puzzle("day02/input.txt")
object Day02: AdventChallenge {

    override fun part1(inputString: String): Any {
        val idRanges = inputString.split(",").map {
            val parts = it.split("-")
            parts[0].toLong()..parts[1].toLong()
        }

        val invalidIds = mutableListOf<Long>()
        idRanges.forEach { idRange ->
            for(id in idRange) {
                val strId = id.toString()
                if(strId.length % 2 != 0) {
                    continue
                }

                val firstHalf = strId.take(strId.length / 2)
                val secondHalf = strId.substring(strId.length / 2)
                if(firstHalf == secondHalf) {
                    invalidIds.add(id)
                }
            }
        }
        return invalidIds.sum()
    }

    override fun part2(inputString: String): Any {
        val idRanges = inputString.split(",").map {
            val parts = it.split("-")
            parts[0].toLong()..parts[1].toLong()
        }

        val invalidIds = mutableListOf<Long>()
        idRanges.forEach { idRange ->
            for (id in idRange) {
                val strId = id.toString()
                for (idx in 1..<(strId.length)) {
                    val part = strId.take(idx)
                    val remainder = strId.replace(part, "")
                    if(remainder.isEmpty()) {
                        invalidIds.add(id)
                        break
                    }
                }
            }
        }
        return invalidIds.sum()
    }
}
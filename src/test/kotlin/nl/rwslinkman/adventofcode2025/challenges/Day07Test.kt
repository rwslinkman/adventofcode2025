package nl.rwslinkman.adventofcode2025.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day07Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            ".......S.......\n" +
            "...............\n" +
            ".......^.......\n" +
            "...............\n" +
            "......^.^......\n" +
            "...............\n" +
            ".....^.^.^.....\n" +
            "...............\n" +
            "....^.^...^....\n" +
            "...............\n" +
            "...^.^...^.^...\n" +
            "...............\n" +
            "..^...^.....^..\n" +
            "...............\n" +
            ".^.^.^.^.^...^.\n" +
            "..............."

        val result = Day07.part1(exampleData)

        assertEquals(21, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            ".......S.......\n" +
            "...............\n" +
            ".......^.......\n" +
            "...............\n" +
            "......^.^......\n" +
            "...............\n" +
            ".....^.^.^.....\n" +
            "...............\n" +
            "....^.^...^....\n" +
            "...............\n" +
            "...^.^...^.^...\n" +
            "...............\n" +
            "..^...^.....^..\n" +
            "...............\n" +
            ".^.^.^.^.^...^.\n" +
            "..............."

        val result = Day07.part2(exampleData)

        assertEquals(40L, result)
    }
}
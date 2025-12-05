package nl.rwslinkman.adventofcode2025.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun `example part 1`() {
        val exampleData = "3-5\n" +
                "10-14\n" +
                "16-20\n" +
                "12-18\n" +
                "\n" +
                "1\n" +
                "5\n" +
                "8\n" +
                "11\n" +
                "17\n" +
                "32"

        val result = Day05.part1(exampleData)

        assertEquals(3, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData = "3-5\n" +
                "10-14\n" +
                "16-20\n" +
                "12-18\n" +
                "\n" +
                "1\n" +
                "5\n" +
                "8\n" +
                "11\n" +
                "17\n" +
                "32"

        val result = Day05.part2(exampleData)

        assertEquals(14L, result)
    }
}
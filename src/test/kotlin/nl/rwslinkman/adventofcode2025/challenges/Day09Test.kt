package nl.rwslinkman.adventofcode2025.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day09Test {

    @Test
    fun `example part 1`() {
        val exampleData = "7,1\n" +
                "11,1\n" +
                "11,7\n" +
                "9,7\n" +
                "9,5\n" +
                "2,5\n" +
                "2,3\n" +
                "7,3"

        val result = Day09.part1(exampleData)

        assertEquals(50L, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData = "7,1\n" +
                "11,1\n" +
                "11,7\n" +
                "9,7\n" +
                "9,5\n" +
                "2,5\n" +
                "2,3\n" +
                "7,3"

        val result = Day09.part2(exampleData)

        assertEquals(24L, result)
    }
}
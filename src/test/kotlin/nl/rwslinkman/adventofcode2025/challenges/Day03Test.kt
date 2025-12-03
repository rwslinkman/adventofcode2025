package nl.rwslinkman.adventofcode2025.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Test {

    @Test
    fun `example part 1`() {
        val exampleData = "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111"

        val result = Day03.part1(exampleData)

        assertEquals(357, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData = "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111"

        val result = Day03.part2(exampleData)

        assertEquals(3121910778619, result)
    }
}
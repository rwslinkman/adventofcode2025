package nl.rwslinkman.adventofcode2025.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun `example part 1`() {
        val exampleData = "L68\n" +
                "L30\n" +
                "R48\n" +
                "L5\n" +
                "R60\n" +
                "L55\n" +
                "L1\n" +
                "L99\n" +
                "R14\n" +
                "L82"

        val result = Day01.part1(exampleData)

        assertEquals(3, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData = "L68\n" +
                "L30\n" +
                "R48\n" +
                "L5\n" +
                "R60\n" +
                "L55\n" +
                "L1\n" +
                "L99\n" +
                "R14\n" +
                "L82"

        val result = Day01.part2(exampleData)

        assertEquals(6, result)
    }

    @Test
    fun `second example part 2`() {
        val exampleData = "R1000"
        val result = Day01.part2(exampleData)

        assertEquals(10, result)
    }

    @Test
    fun `third example part 2`() {
        val exampleData = "L1000"
        val result = Day01.part2(exampleData)

        assertEquals(10, result)
    }
}
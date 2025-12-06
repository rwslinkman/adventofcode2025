package nl.rwslinkman.adventofcode2025.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day06Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "123 328  51 64 \n" +
            " 45 64  387 23 \n" +
            "  6 98  215 314\n" +
            "*   +   *   +  "

        val result = Day06.part1(exampleData)

        assertEquals(4277556L, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "123 328  51 64 \n" +
            " 45 64  387 23 \n" +
            "  6 98  215 314\n" +
            "*   +   *   +  "

        val result = Day06.part2(exampleData)

        assertEquals(3263827L, result)
    }
}
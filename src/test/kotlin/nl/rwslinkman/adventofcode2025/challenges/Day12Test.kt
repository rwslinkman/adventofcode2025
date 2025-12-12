package nl.rwslinkman.adventofcode2025.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class Day12Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "0:\n" +
            "###\n" +
            "##.\n" +
            "##.\n" +
            "\n" +
            "1:\n" +
            "###\n" +
            "##.\n" +
            ".##\n" +
            "\n" +
            "2:\n" +
            ".##\n" +
            "###\n" +
            "##.\n" +
            "\n" +
            "3:\n" +
            "##.\n" +
            "###\n" +
            "##.\n" +
            "\n" +
            "4:\n" +
            "###\n" +
            "#..\n" +
            "###\n" +
            "\n" +
            "5:\n" +
            "###\n" +
            ".#.\n" +
            "###\n" +
            "\n" +
            "4x4: 0 0 0 0 2 0\n" +
            "12x5: 1 0 1 0 2 2\n" +
            "12x5: 1 0 1 0 3 2"

        val result = Day12.part1(exampleData)

        assertEquals(2, result)
    }
}
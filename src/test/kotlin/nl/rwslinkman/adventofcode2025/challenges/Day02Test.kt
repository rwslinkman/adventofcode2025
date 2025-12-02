package nl.rwslinkman.adventofcode2025.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    fun `example part 1`() {
        val exampleData = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                "824824821-824824827,2121212118-2121212124"

        val result = Day02.part1(exampleData)

        assertEquals(1227775554L, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                "824824821-824824827,2121212118-2121212124"

        val result = Day02.part2(exampleData)

        assertEquals(4174379265L, result)
    }
}
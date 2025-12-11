package nl.rwslinkman.adventofcode2025.challenges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class Day11Test {

    @Test
    fun `example part 1`() {
        val exampleData =
            "aaa: you hhh\n" +
            "you: bbb ccc\n" +
            "bbb: ddd eee\n" +
            "ccc: ddd eee fff\n" +
            "ddd: ggg\n" +
            "eee: out\n" +
            "fff: out\n" +
            "ggg: out\n" +
            "hhh: ccc fff iii\n" +
            "iii: out"

        val result = Day11.part1(exampleData)

        assertEquals(5L, result)
    }

    @Test
    fun `example part 2`() {
        val exampleData =
            "svr: aaa bbb\n" +
            "aaa: fft\n" +
            "fft: ccc\n" +
            "bbb: tty\n" +
            "tty: ccc\n" +
            "ccc: ddd eee\n" +
            "ddd: hub\n" +
            "hub: fff\n" +
            "eee: dac\n" +
            "dac: fff\n" +
            "fff: ggg hhh\n" +
            "ggg: out\n" +
            "hhh: out"
        val result = Day11.part2(exampleData)

        assertEquals(2L, result)
    }
}
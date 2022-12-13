package com.github.malukenho.aoc2022

import Util.readInput
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class Day9Test {
    @Test
    fun part1() {
        assertEquals(Day9(SAMPLE_INPUT).part1(), 13)
        assertEquals(Day9(readInput(9).lines()).part1(), 6314)
    }

    @Test
    fun part2() {
        assertEquals(Day9(SAMPLE_INPUT).part2(), 1)
        assertEquals(Day9(readInput(9).lines()).part2(), 2504)
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2",
        )
    }
}

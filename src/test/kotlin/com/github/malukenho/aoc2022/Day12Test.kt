package com.github.malukenho.aoc2022

import Util.readInput
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class Day12Test {
    @Test
    fun part1() {
        assertEquals(31, Day12(SAMPLE_INPUT).part1())
        assertEquals(391, Day12(readInput(12)).part1())
    }

    @Test
    fun part2() {
        assertEquals(29, Day12(SAMPLE_INPUT).part2())
        assertEquals(386, Day12(readInput(12)).part2())
    }

    companion object {
        private const val SAMPLE_INPUT =
            "Sabqponm\n" +
            "abcryxxl\n" +
            "accszExk\n" +
            "acctuvwj\n" +
            "abdefghi\n"
    }
}

package com.github.malukenho.aoc2022

import Util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals


class Day8Test {
    @Test
    fun part1() {
        assertEquals(Day08(SAMPLE_INPUT).part1(), 21)
        assertEquals(Day08(readInput(8).lines()).part1(), 1711)
    }

    @Test
    fun part2() {
        assertEquals(Day08(SAMPLE_INPUT).part2(), 8)
        assertEquals(Day08(readInput(8).lines()).part2(), 301392)
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390",
        )
    }
}

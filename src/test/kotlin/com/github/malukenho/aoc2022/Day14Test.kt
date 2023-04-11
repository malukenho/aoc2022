package com.github.malukenho.aoc2022

import Util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    @Test
    fun part1() {
        assertEquals(24, Day14(SAMPLE_INPUT).part1())
        assertEquals(1330, Day14(readInput(14)).part1())
    }

    companion object {
        private const val SAMPLE_INPUT =
            "498,4 -> 498,6 -> 496,6\n" +
            "503,4 -> 502,4 -> 502,9 -> 494,9"
    }
}

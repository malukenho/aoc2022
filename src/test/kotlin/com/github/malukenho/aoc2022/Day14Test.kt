package com.github.malukenho.aoc2022

import Util.readInput
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class Day14Test {

    @Test
    fun part1() {
        assertEquals(Day14(SAMPLE_INPUT).part1(), 24)
//        assertEquals(Day14(readInput(14)).part1(), 5659)
    }

    companion object {
        private const val SAMPLE_INPUT =
            "498,4 -> 498,6 -> 496,6\n" +
            "503,4 -> 502,4 -> 502,9 -> 494,9"
    }
}

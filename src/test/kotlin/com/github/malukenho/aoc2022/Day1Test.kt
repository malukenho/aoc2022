package com.github.malukenho.aoc2022

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class Day1Test {
    @Test
    fun part1() {
        assertEquals(Day1(SAMPLE_INPUT).part1(), 24000)
    }

    @Test
    fun part2() {
        assertEquals(Day1(SAMPLE_INPUT).part2(), 45000)
    }

    companion object {
        private val SAMPLE_INPUT =
            "1000\n" +
            "2000\n" +
            "3000\n" +
            "\n" +
            "4000\n" +
            "\n" +
            "5000\n" +
            "6000\n" +
            "\n" +
            "7000\n" +
            "8000\n" +
            "9000\n" +
            "\n" +
            "10000"
    }
}

package com.github.malukenho.aoc2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun part1() {
        assertEquals(Day01(SAMPLE_INPUT).part1(), 24000)
    }

    @Test
    fun part2() {
        assertEquals(Day01(SAMPLE_INPUT).part2(), 45000)
    }

    companion object {
        private const val SAMPLE_INPUT =
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

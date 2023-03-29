package com.github.malukenho.aoc2022

import Util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals


class Day13Test {

    @Test
    fun part1() {
        assertEquals(Day13(SAMPLE_INPUT).part1(), 13)
        assertEquals(Day13(readInput(13)).part1(), 5659)
    }

    @Test
    fun part2() {
        assertEquals(Day13(SAMPLE_INPUT).part2(), 140)
        assertEquals(Day13(readInput(13)).part2(), 22110)
    }

    companion object {
        private const val SAMPLE_INPUT =
            "[1,1,3,1,1]\n" +
            "[1,1,5,1,1]\n" +
            "\n" +
            "[[1],[2,3,4]]\n" +
            "[[1],4]\n" +
            "\n" +
            "[9]\n" +
            "[[8,7,6]]\n" +
            "\n" +
            "[[4,4],4,4]\n" +
            "[[4,4],4,4,4]\n" +
            "\n" +
            "[7,7,7,7]\n" +
            "[7,7,7]\n" +
            "\n" +
            "[]\n" +
            "[3]\n" +
            "\n" +
            "[[[]]]\n" +
            "[[]]\n" +
            "\n" +
            "[1,[2,[3,[4,[5,6,7]]]],8,9]\n" +
            "[1,[2,[3,[4,[5,6,0]]]],8,9]"
    }
}

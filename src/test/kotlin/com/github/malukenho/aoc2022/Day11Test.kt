package com.github.malukenho.aoc2022

import Util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals


class Day11Test {
    @Test
    fun part1() {
        assertEquals(Day11(SAMPLE_INPUT).part1(20), 10605L)
        assertEquals(Day11(readInput(11)).part1(20), 50172L)
    }

    @Test
    fun part2() {
        assertEquals(Day11(SAMPLE_INPUT).part2(10_000), 2713310158L)
        assertEquals(Day11(readInput(11)).part2(10_000), 11614682178L)
    }

    companion object {
        private const val SAMPLE_INPUT =
            "Monkey 0:\n" +
            "  Starting items: 79, 98\n" +
            "  Operation: new = old * 19\n" +
            "  Test: divisible by 23\n" +
            "    If true: throw to monkey 2\n" +
            "    If false: throw to monkey 3\n" +
            "\n" +
            "Monkey 1:\n" +
            "  Starting items: 54, 65, 75, 74\n" +
            "  Operation: new = old + 6\n" +
            "  Test: divisible by 19\n" +
            "    If true: throw to monkey 2\n" +
            "    If false: throw to monkey 0\n" +
            "\n" +
            "Monkey 2:\n" +
            "  Starting items: 79, 60, 97\n" +
            "  Operation: new = old * old\n" +
            "  Test: divisible by 13\n" +
            "    If true: throw to monkey 1\n" +
            "    If false: throw to monkey 3\n" +
            "\n" +
            "Monkey 3:\n" +
            "  Starting items: 74\n" +
            "  Operation: new = old + 3\n" +
            "  Test: divisible by 17\n" +
            "    If true: throw to monkey 0\n" +
            "    If false: throw to monkey 1"
    }
}

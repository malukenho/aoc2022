package com.github.malukenho.aoc2022

import Util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun part1() {
        assertEquals(Day10(SAMPLE_INPUT).part1(20), 420)
        assertEquals(Day10(SAMPLE_INPUT).part1(60), 1140)
        assertEquals(Day10(SAMPLE_INPUT).part1(100), 1800)
        assertEquals(Day10(SAMPLE_INPUT).part1(140), 2940)
        assertEquals(Day10(SAMPLE_INPUT).part1(180), 2880)
        assertEquals(Day10(SAMPLE_INPUT).part1(220), 3960)


        val day10 = Day10(readInput(10).lines())
        val result =
            day10.part1(20) + day10.part1(60) + day10.part1(100) + day10.part1(140) + day10.part1(180) + day10.part1(220)

        assertEquals(result, 16880)
    }

    @Test
    fun part2() {
        assertEquals(
           Day10(SAMPLE_INPUT).part2(),
           "##..##..##..##..##..##..##..##..##..##..\n" +
           "###...###...###...###...###...###...###.\n" +
           "####....####....####....####....####....\n" +
           "#####.....#####.....#####.....#####.....\n" +
           "######......######......######......####\n" +
           "#######.......#######.......#######.....\n")


        assertEquals(
            Day10(readInput(10).lines()).part2(),
            "###..#..#..##..####..##....##.###..###..\n" +
            "#..#.#.#..#..#....#.#..#....#.#..#.#..#.\n" +
            "#..#.##...#..#...#..#..#....#.###..#..#.\n" +
            "###..#.#..####..#...####....#.#..#.###..\n" +
            "#.#..#.#..#..#.#....#..#.#..#.#..#.#.#..\n" +
            "#..#.#..#.#..#.####.#..#..##..###..#..#.\n"
        )
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
            "addx 15",
            "addx -11",
            "addx 6",
            "addx -3",
            "addx 5",
            "addx -1",
            "addx -8",
            "addx 13",
            "addx 4",
            "noop",
            "addx -1",
            "addx 5",
            "addx -1",
            "addx 5",
            "addx -1",
            "addx 5",
            "addx -1",
            "addx 5",
            "addx -1",
            "addx -35",
            "addx 1",
            "addx 24",
            "addx -19",
            "addx 1",
            "addx 16",
            "addx -11",
            "noop",
            "noop",
            "addx 21",
            "addx -15",
            "noop",
            "noop",
            "addx -3",
            "addx 9",
            "addx 1",
            "addx -3",
            "addx 8",
            "addx 1",
            "addx 5",
            "noop",
            "noop",
            "noop",
            "noop",
            "noop",
            "addx -36",
            "noop",
            "addx 1",
            "addx 7",
            "noop",
            "noop",
            "noop",
            "addx 2",
            "addx 6",
            "noop",
            "noop",
            "noop",
            "noop",
            "noop",
            "addx 1",
            "noop",
            "noop",
            "addx 7",
            "addx 1",
            "noop",
            "addx -13",
            "addx 13",
            "addx 7",
            "noop",
            "addx 1",
            "addx -33",
            "noop",
            "noop",
            "noop",
            "addx 2",
            "noop",
            "noop",
            "noop",
            "addx 8",
            "noop",
            "addx -1",
            "addx 2",
            "addx 1",
            "noop",
            "addx 17",
            "addx -9",
            "addx 1",
            "addx 1",
            "addx -3",
            "addx 11",
            "noop",
            "noop",
            "addx 1",
            "noop",
            "addx 1",
            "noop",
            "noop",
            "addx -13",
            "addx -19",
            "addx 1",
            "addx 3",
            "addx 26",
            "addx -30",
            "addx 12",
            "addx -1",
            "addx 3",
            "addx 1",
            "noop",
            "noop",
            "noop",
            "addx -9",
            "addx 18",
            "addx 1",
            "addx 2",
            "noop",
            "noop",
            "addx 9",
            "noop",
            "noop",
            "noop",
            "addx -1",
            "addx 2",
            "addx -37",
            "addx 1",
            "addx 3",
            "noop",
            "addx 15",
            "addx -21",
            "addx 22",
            "addx -6",
            "addx 1",
            "noop",
            "addx 2",
            "addx 1",
            "noop",
            "addx -10",
            "noop",
            "noop",
            "addx 20",
            "addx 1",
            "addx 2",
            "addx 2",
            "addx -6",
            "addx -11",
            "noop",
            "noop",
            "noop",
        )
    }
}

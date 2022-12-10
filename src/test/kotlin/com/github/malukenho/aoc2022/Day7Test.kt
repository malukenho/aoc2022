package com.github.malukenho.aoc2022

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class Day7Test {
    @Test
    fun part1() {
        assertEquals(Day7(SAMPLE_INPUT).part1(), 95437)
    }

    @Test
    fun part2() {
        assertEquals(Day7(SAMPLE_INPUT).part2(), 23352670)
    }

    companion object {
        private val SAMPLE_INPUT = listOf(
            "$ cd /",
            "$ ls",
            "dir a",
            "14848514 b.txt",
            "8504156 c.dat",
            "dir d",
            "$ cd a",
            "$ ls",
            "dir e",
            "29116 f",
            "2557 g",
            "62596 h.lst",
            "$ cd e",
            "$ ls",
            "584 i",
            "$ cd ..",
            "$ cd ..",
            "$ cd d",
            "$ ls",
            "4060174 j",
            "8033020 d.log",
            "5626152 d.ext",
            "7214296 k"
        )
    }
}

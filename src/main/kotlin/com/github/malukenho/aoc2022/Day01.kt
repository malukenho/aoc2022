package com.github.malukenho.aoc2022

class Day01(text: String) {
    private val elves: List<Int> =
        text.split("\n\n").map { elf -> elf.lines().sumOf { it.toInt() } }.sortedDescending()

    fun part1() = elves.first()
    fun part2() = elves.take(3).sum()
}
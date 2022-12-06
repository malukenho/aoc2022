package DayOne

import Util.readInput

fun main() {
    val elves: List<Int> = readInput(1)
        .split("\n\n")
        .map { elf -> elf.lines().sumOf { it.toInt() } }
        .sortedDescending()

    println("List<Int>.first()       = " + elves.first())
    println("List<Int>.take(3).sum() = " + elves.take(3).sum())
}
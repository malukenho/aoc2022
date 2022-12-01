package DayOne

import java.io.File

fun main() {
    val elves: List<Int> = File("src/main/kotlin/DayOne/input.txt")
        .readText()
        .split("\n\n")
        .map { elf -> elf.lines().sumOf { it.toInt() } }

    println("Lit<Int>.maxOf { it }      = " + elves.max())
    println("Elves.take(3).sumOf { it } = " + elves.sortedDescending().take(3).sum())
}
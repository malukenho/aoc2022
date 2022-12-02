package DayOne

import java.io.File

fun main() {
    // @todo create an utility class
    val elves: List<Int> = File("src/main/kotlin/DayOne/input.txt")
        .readText()
        .split("\n\n")
        .map { elf -> elf.lines().sumOf { it.toInt() } }

    println("List<Int>.max()         = " + elves.max())
    println("List<Int>.take(3).sum() = " + elves.sortedDescending().take(3).sum())
}
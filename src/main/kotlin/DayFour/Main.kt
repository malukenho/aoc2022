package DayFour

import Util.readExample
import Util.readInput
import kotlin.ranges.IntRange

private fun String.splitOnComma() = this.split(',')
private fun String.firstSection() = this.split('-').first().toInt()
private fun String.lastSection() = this.split('-').last().toInt()

private fun String.expandRange(): IntRange = IntRange(this.firstSection(), this.lastSection())

private fun List<IntRange>.hasOverlappingSections() =
    this.first().subtract(this.last()).isEmpty() || this.last().subtract(this.first()).isEmpty()

private fun List<IntRange>.hasEncompassingSection() =
    this.first().subtract(this.last()).size != this.first().count()

fun main() {
    fun part1(input: String): Int {
        return input
            .lines()
            .map { it.splitOnComma() }
            .map { elves -> elves.map { it.expandRange() } }
            .count { elves -> elves.hasOverlappingSections() }
    }

    check(2 == part1(readExample(4)))
    check(413 == part1(readInput(4)))

    fun part2(input: String): Int {
        return input
            .lines()
            .map { it.splitOnComma() }
            .map { elves -> elves.map { it.expandRange() } }
            .count { elves -> elves.hasEncompassingSection() }
    }

    check(4 == part2(readExample(4)))
    check(806 == part2(readInput(4)))
}
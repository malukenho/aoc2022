package DayFive

import Util.readExample
import Util.readInput

fun main() {
    fun String.splitGridFromInstruction(): List<String> = this.split("\n\n")

    fun String.trimBox(): String = this.trim().trimStart('[').trimEnd(']')

    fun MutableList<MutableList<String>>.render(): String = this.joinToString("") { it.last() }

    fun MutableList<MutableList<String>>.locate(index: String): MutableList<String> = this[index.toInt() - 1]

    fun String.breakUpInstruction(): List<List<String>> = this.lines()
        .map { it.split(Regex("\\D+")) }
        .map { list -> list.filter { it.isNotBlank() } }

    fun parseGrid(grid: String): MutableList<MutableList<String>> {
        val normalizedGrip = mutableListOf<MutableList<String>>()

        grid.lines().dropLast(1).map { it.chunked(4) }
            .forEach { i ->
                i.forEachIndexed { column, item ->
                    if (normalizedGrip.getOrNull(column).isNullOrEmpty()) {
                        normalizedGrip.add(column, mutableListOf())
                    }
                    normalizedGrip[column].add(item.trimBox())
                }
            }

        return normalizedGrip.map { list -> list.filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
            .map { it.asReversed() }.toMutableList()
    }

    fun part1(input: String): String {
        val (rawGridData, rawInstructionsData) = input.splitGridFromInstruction()

        val grid = parseGrid(rawGridData)
        val movements = rawInstructionsData.breakUpInstruction()

        movements.forEach { movement ->
            val (quantity, from, to) = movement
            repeat(quantity.toInt()) { grid.locate(to).add(grid.locate(from).removeLast()) }
        }

        return grid.render()
    }

    check("CMZ" == part1(readExample(5)))
    check("GRTSWNJHH" == part1(readInput(5)))

    fun part2(input: String): String {
        val (rawGridData, rawInstructionsData) = input.splitGridFromInstruction()

        val grid = parseGrid(rawGridData)
        val movements = rawInstructionsData.breakUpInstruction()

        movements.forEach { movement ->
            val (quantity, from, to) = movement

            grid.locate(to).addAll(grid.locate(from).takeLast(quantity.toInt()))
            grid[from.toInt() - 1] = grid.locate(from).dropLast(quantity.toInt()).toMutableList()
        }

        return grid.render()
    }

    check("MCD" == part2(readExample(5)))
    check("QLFQDBBHM" == part2(readInput(5)))
}
package DayFive

import Util.readExample
import Util.readInput

fun main() {
    fun String.splitGridFromInstruction(): List<String> = this.split("\n\n")
    fun String.trimBox(): String = this.trim().trimStart('[').trimEnd(']')

    fun part1(input: String): String {
        val (grid, instructions) = input.splitGridFromInstruction()

        val items = grid.lines().dropLast(1).map { it.chunked(4).toMutableList() }.toMutableList()

        val normalizedGrip = mutableListOf<MutableList<String>>()

        items.forEach { i ->
            i.forEachIndexed { column, item ->
                if (normalizedGrip.getOrNull(column).isNullOrEmpty()) {
                    normalizedGrip.add(column, mutableListOf())
                }
                normalizedGrip.get(column).add(item.trimBox())
            }
        }

        val finalGrid = normalizedGrip.map { a -> a.filter { it.isNotEmpty() }.toMutableList() }
            .toMutableList()
            .map { it.asReversed() }.toMutableList()

        val movements = instructions.lines()
            .map { it.split(Regex("\\D+")) }
            .map { it.filter { it.isNotBlank() } }

        movements.forEach { movement ->
            val (quantity, from, to) = movement

            IntRange(1, quantity.toInt()).forEach { _ ->
                finalGrid[to.toInt() - 1].add(finalGrid[from.toInt() - 1].last())
                finalGrid[from.toInt() - 1] = finalGrid[from.toInt() - 1].dropLast(1).toMutableList()
            }
        }

        return finalGrid.map { it.last() }.reduce { acc, s -> acc + s }
    }

    check("CMZ" == part1(readExample(5)))
    check("GRTSWNJHH" == part1(readInput(5)))
}
package com.github.malukenho.aoc2022

import kotlin.math.max

fun List<List<Int>>.look(rowIndex: Int, columnIndex: Int): Int =
    this.toMutableList().getOrElse(rowIndex) { emptyList() }.getOrElse(columnIndex) { 10 }

class Day8(list: List<String>) {
    private val grid = list.map {
        it.toCharArray().map { x -> x.digitToInt() }
    }

    private fun lookAround(rowIndex: Int, columnIndex: Int): Int {
        val currentValue = grid[rowIndex][columnIndex]

        var rightVisible = true
        var leftVisible = true
        var upVisible = true
        var downVisible = true
        var comulateUp = 0
        var comulateDown = 0
        var comulateLeft = 0
        var comulateRight = 0

        fun echo(msg: String) { if (rowIndex == 2 && columnIndex == 3) { print(msg) } }

        repeat(rowIndex) { index ->
//            echo((rowIndex - index - 1).toString() + "-------" + rowIndex + "\n")
            if (grid.look(rowIndex - index - 1, columnIndex) >= currentValue) {
                comulateUp++
                upVisible = false
                return@repeat
            }
            if (upVisible) comulateUp++
        }
//        echo("up: $comulateUp ")

        repeat(columnIndex - 1) { index ->
            if (grid.look(rowIndex, columnIndex - index - 1) >= currentValue) {
                comulateLeft++
                leftVisible = false
                return@repeat
            }
            if (leftVisible) comulateLeft++
        }
//        echo("left: $comulateLeft ")

        repeat(grid.size -1- rowIndex) { index ->
//            echo((rowIndex + index + 1).toString() + "-------" + rowIndex + "\n")
            if (grid.look(rowIndex + index + 1, columnIndex) >= currentValue) {
                comulateDown++
                downVisible = false
                return@repeat
            }
            if (downVisible) comulateDown++
        }
//        echo("down: $comulateDown ")

        /**
         *                1
         *      ->   0 1 [2] 3
         *                3
         *                4
         */

        repeat(grid[0].size -1- columnIndex) { index ->
            if (grid.look(rowIndex, columnIndex + index) >= currentValue) {
                comulateRight++
                rightVisible = false
                return@repeat
            }

            if (rightVisible) comulateRight++
        }
//        echo("right: $comulateRight \n")
//        echo ("$comulateUp * $comulateLeft * $comulateDown * $comulateRight = " + (comulateUp * comulateLeft * comulateDown * comulateRight) + "\n")
        return comulateUp * comulateLeft * comulateDown * comulateRight
    }

    // TODO(): may I use Monads here?
//    fun part1(): Int {
//        val onlyVisibles = grid.mapIndexed { rowIndex, row ->
//            return@mapIndexed row.mapIndexed { columnIndex, column ->
//                if (rowIndex == 0 || columnIndex == 0 || rowIndex == grid.size - 1 || columnIndex == grid[0].size - 1) {
//                    return@mapIndexed column
//                }
//
//                return@mapIndexed if (lookAround(rowIndex, columnIndex)) {
//                    column
//                } else null
//            }
//        }
//            .map { x -> x.filterNotNull() }
//            .flatten()
//            .count()
//
//        return onlyVisibles
//    }

    fun part2(): Int {
        val onlyVisibles = grid.mapIndexed { rowIndex, row ->
            return@mapIndexed row.mapIndexed { columnIndex, column ->

                fun echo(msg: String) { if (rowIndex == 2 && columnIndex == 3) { print(msg) } }

                if (rowIndex == 0 || columnIndex == 0 || rowIndex == (grid.size - 1) || columnIndex == (grid[0].size - 1)) {
                    return@mapIndexed 0
                }

//                echo("x($rowIndex, $columnIndex) = $column => " + lookAround(rowIndex, columnIndex) + "\n")
                return@mapIndexed lookAround(rowIndex, columnIndex)
            }
        }
            .flatten()
            .max()
//            .also { println(it) }

        return onlyVisibles
    }
}

package com.github.malukenho.aoc2022

import kotlin.math.max

class Day8(list: List<String>) {
    private val grid = list.map {
        it.toCharArray().map { x -> x.digitToInt() }
    }

    private fun List<List<Int>>.look(rowIndex: Int, columnIndex: Int): Int =
        this.toMutableList().getOrElse(rowIndex) { emptyList() }.getOrElse(columnIndex) { 10 }
    fun List<List<Int>>.lookUp(rowIndex: Int, columnIndex: Int): Int = this.look(rowIndex - 1, columnIndex)
    fun List<List<Int>>.lookDown(rowIndex: Int, columnIndex: Int): Int = this.look(rowIndex + 1, columnIndex)
    fun List<List<Int>>.lookLeft(rowIndex: Int, columnIndex: Int): Int = this.look(rowIndex, columnIndex - 1)
    fun List<List<Int>>.lookRight(rowIndex: Int, columnIndex: Int): Int = this.look(rowIndex, columnIndex + 1)

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

        repeat(rowIndex) { index ->
            if (grid.look(rowIndex - index - 1, columnIndex) >= currentValue) {
                comulateUp++
                upVisible = false
                return@repeat
            }
            if (upVisible) comulateUp++
        }

        repeat(columnIndex - 1) { index ->
            if (grid.look(rowIndex, columnIndex - index - 1) >= currentValue) {
                comulateLeft++
                leftVisible = false
                return@repeat
            }
            if (leftVisible) comulateLeft++
        }

        repeat(grid.size -1- rowIndex) { index ->
            if (grid.look(rowIndex + index + 1, columnIndex) >= currentValue) {
                comulateDown++
                downVisible = false
                return@repeat
            }
            if (downVisible) comulateDown++
        }

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

        return comulateUp * comulateLeft * comulateDown * comulateRight
    }

    private fun lookAroundPart1(rowIndex: Int, columnIndex: Int): Boolean {
        val currentValue = grid[rowIndex][columnIndex];

        var rightVisible = true;
        var leftVisible = true;
        var upVisible = true;
        var downVisible = true;

        repeat(max(grid[0].size - columnIndex, 1)) { index ->
            if (grid.lookRight(rowIndex, columnIndex + index) >= currentValue) {
                rightVisible = false
                return@repeat
            }
        }

        repeat(max(columnIndex, 1)) { index ->
            if (grid.lookLeft(rowIndex, columnIndex - index) >= currentValue) {
                leftVisible = false
                return@repeat
            }
        }

        repeat(max(grid.size - rowIndex, 1)) { index ->
            if (grid.lookDown(rowIndex + index, columnIndex) >= currentValue) {
                downVisible = false
                return@repeat
            }
        }

        repeat(rowIndex) { index ->
            if (grid.lookUp(index + 1, columnIndex) >= currentValue) {
                upVisible = false
                return@repeat
            }
        }

        return leftVisible || rightVisible || downVisible || upVisible
    }

    // TODO(): may I use Monads here?
    fun part1(): Int {
        val onlyVisibles = grid.mapIndexed { rowIndex, row ->
            return@mapIndexed row.mapIndexed { columnIndex, column ->
                if (rowIndex == 0 || columnIndex == 0 || rowIndex == grid.size - 1 || columnIndex == grid[0].size - 1) {
                    return@mapIndexed column
                }

                return@mapIndexed if (lookAroundPart1(rowIndex, columnIndex)) {
                    column
                } else null
            }
        }
            .map { x -> x.filterNotNull() }
            .flatten()
            .count()

        return onlyVisibles
    }

    fun part2(): Int {
        val onlyVisibles = grid.mapIndexed { rowIndex, row ->
            return@mapIndexed row.mapIndexed { columnIndex, column ->
                if (rowIndex == 0 || columnIndex == 0 || rowIndex == (grid.size - 1) || columnIndex == (grid[0].size - 1)) {
                    return@mapIndexed 0
                }

                return@mapIndexed lookAround(rowIndex, columnIndex)
            }
        }
            .flatten()
            .max()

        return onlyVisibles
    }
}
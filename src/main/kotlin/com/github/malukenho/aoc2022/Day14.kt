package com.github.malukenho.aoc2022

typealias Rocks = Pair<Int, Int>
typealias CoordinateList = MutableList<MutableList<Rocks>>

typealias Cave = MutableList<MutableList<Pair<Int, String>>>

fun Cave.getXY(x: Int, y: Int) = this.getOrNull(x)?.getOrNull(y)

fun CoordinateList.max() = this.flatMap { map -> map.map { it.first } }.max()
fun CoordinateList.min() = this.flatMap { map -> map.map { it.first } }.min()
fun CoordinateList.rowsRange() = 0..this.flatMap { map -> map.map { it.second } }.max()

class Day14(val input: String) {

    // Read coordinates per line
    private val routes: CoordinateList = input.split("\n").map { it ->
        it.split(" -> ").map { that ->
            val path = that.split(",")
            Pair(path.first().toInt(), path.last().toInt())
        }.toMutableList()
    }.toMutableList()

    private val columns: IntRange = routes.min()..routes.max()

    private fun MutableList<MutableList<Pair<Int, String>>>.show() = this.forEach {
        println(it.joinToString("") { that -> that.second })
    }

    fun part1(): Int { // @TODO("Which name better reflects the problem proposed domain?")

        val cave: Cave = routes.rowsRange().map {
            columns.map { column -> Pair(column, ".") }.toMutableList()
        }.toMutableList()

        // Draw rocks on cave
        routes.forEach { coordinates ->
            coordinates.windowed(2, 1, false).forEach {
                val first = it.first()
                val second = it.last()

                val moveDown = first.first == second.first
                val moveSide = !moveDown

                if (moveDown) { // 498 4..6
                    movingDown@ for (i in first.second..second.second) {
                        cave.get(i).set(first.first - columns.min(), Pair(first.first, "#"))
                    }
                }

                if (moveSide) {
                    val range = if (first.first > second.first) {
                        second.first..first.first
                    } else {
                        first.first..second.first
                    }

                    movingSideways@ for (i in range) {
                        cave.get(first.second).set(i - columns.min(), Pair(i, "#"))
                    }
                }
            }
        }

        val sandSource = 500
        var landed = 0
        var fallingX = 0
        val initial = sandSource - columns.min()
        var fallingY = sandSource - columns.min()

        val voidStarsAt = routes.rowsRange().last
        while (true) {
            val down = cave.getXY(fallingX+1, fallingY)?.second ?: break
            val downLeft = cave.getXY(fallingX+1, fallingY-1)?.second ?: break
            val downRight = cave.getXY(fallingX+1, fallingY+1)?.second ?: break

            if (fallingX > voidStarsAt) {
                break
            }

            if (down == ".") {
                fallingX++
                continue
            }

            if (downLeft == ".") {
                fallingY--
                continue
            }

            if (downRight == ".") {
                fallingY++
                continue
            }

            cave.get(fallingX).set(fallingY, Pair(sandSource, "o"))

            landed++
            fallingX = 0
            fallingY = initial
            continue
        }

        //cave.show()

        return landed
    }
}
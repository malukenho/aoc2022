package com.github.malukenho.aoc2022

class Day14(private val input: String) {

    private fun MutableList<MutableList<Pair<Int, String>>>.getXY(x: Int, y: Int) = this.getOrNull(y)?.getOrNull(x)

    private fun MutableList<MutableList<Pair<Int, Int>>>.max() = this.flatMap { map -> map.map { it.first } }.max()
    private fun MutableList<MutableList<Pair<Int, Int>>>.min() = this.flatMap { map -> map.map { it.first } }.min()
    private fun MutableList<MutableList<Pair<Int, Int>>>.rowsRange() = 0..this.flatMap { map -> map.map { it.second } }.max()

    // Read coordinates per line
    private val routes  = input.split("\n").map { it ->
        it.split(" -> ").map { that ->
            val path = that.split(",")
            Pair(path.first().toInt(), path.last().toInt())
        }.toMutableList()
    }.toMutableList()

    private val columns: IntRange = routes.min()..routes.max()

    private val voidStartsAt = routes.maxOf { it.maxOf { a -> a.second } }

    private fun MutableList<MutableList<Pair<Int, String>>>.show() = this.forEach {
        println(it.joinToString("") { that -> that.second })
    }

    fun part1(): Int { // @TODO("Which name better reflects the problem proposed domain?")

        val cave  = routes.rowsRange().map {
            columns.map { column -> Pair(column, ".") }.toMutableList()
        }.toMutableList()

        // Draw rocks on cave
        routes.forEach { coordinates ->
            coordinates.windowed(2, 1, true).forEach {
                val first = it.first()
                val second = it.last()

                val moveDown = first.first == second.first // x
                val moveSide = !moveDown

                if (moveDown) { // 498 4..6
                    val range = if (first.second > second.second) {
                        second.second..first.second
                    } else {
                        first.second..second.second
                    }

                    movingDown@ for (i in range) {
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
        var yDelta = 0
        val initial = sandSource - columns.min()
        var xDelta = initial

        while (true) {
            val down = cave.getXY(xDelta, yDelta + 1)?.second
            val downLeft = cave.getXY(xDelta-1, yDelta+1)?.second
            val downRight = cave.getXY(xDelta+1, yDelta+1)?.second

            if (yDelta >= voidStartsAt) {
                break
            }

            if (down == null || downLeft == null || downRight == null) {
                yDelta++
                continue
            }

            if (down == ".") {
                yDelta++
                continue
            }

            if (downLeft == ".") {
                xDelta--
                continue
            }

            if (downRight == ".") {
                xDelta++
                continue
            }

            cave.get(yDelta).set(xDelta, Pair(sandSource, "o"))

            landed += 1
            yDelta = 0
            xDelta = initial
        }

        return landed
    }
}
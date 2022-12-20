package com.github.malukenho.aoc2022

class Day12(val input: String) { // Trying to solve it with a BFS

    fun part1(): Int {
        var grid = input.trim().split("\n").map { xs -> xs.chunked(1).toMutableList() }.toMutableList()

        var startRow = 0
        var startColumn = 0
        var endRow = 0
        var endColumn = 0
        grid.forEachIndexed { xsi, xs ->
            xs.forEachIndexed { xzi, xz ->
                if (xz == "S") {
                    startRow = xsi
                    startColumn = xzi
                    grid[xsi][xzi] = "a"
                }
                if (xz == "E") {
                    endRow = xsi
                    endColumn = xzi
                    grid[xsi][xzi] = "z"
                }
            }
        }

        // Add values to the queue
        val deque = ArrayDeque<Triple<Int, Int, Int>>()
        deque.add(Triple(0, startRow, startColumn))

        // We need a visited set as well
        val visited = mutableSetOf<Pair<Int, Int>>()
        visited.add(Pair(startRow, startColumn))

        var result = 0

        while (deque.isNotEmpty()) {
            val (d, r, c) = deque.removeFirst()
            listOf(
                Pair(r + 1, c), Pair(r - 1, c), Pair(r, c + 1), Pair(r, c - 1)
            ).forEach { t ->
                val nr = t.first
                val nc = t.second

                if (nr < 0 || nc < 0 || nr >= grid.size || nc >= grid[0].size) return@forEach
                if (visited.contains(t)) return@forEach
                if ((grid[nr][nc].toCharArray()[0].toInt() - grid[r][c].toCharArray()[0].toInt()) > 1) {
                    return@forEach
                }
                if (nr == endRow && nc == endColumn) {
                    result = d + 1
                    return@forEach
                }
                visited.add(t)
                deque.add(Triple(d + 1, nr, nc))
            }
        }

        return result
    }
}

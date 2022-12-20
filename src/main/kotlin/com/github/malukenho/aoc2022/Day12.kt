package com.github.malukenho.aoc2022

class Day12(val input: String) { // Trying to solve it with a BFS
    private val maze = input.trim().split("\n")
        .map { xs -> xs.chunked(1).toMutableList() }
        .toMutableList()

    fun part1(): Int {
        val grid = maze

        var start = Pair(0, 0)
        var end = Pair(0, 0)
        grid.forEachIndexed { xsi, xs ->
            xs.forEachIndexed { xzi, xz ->
                if (xz == "S") {
                    start = Pair(xsi, xzi)
                    grid[xsi][xzi] = "a" // I've changed this one just to avoid create a special case for "S"
                }
                if (xz == "E") {
                    end = Pair(xsi, xzi)
                    grid[xsi][xzi] = "z" // Same here
                }
            }
        }

        // Add values to the queue
        val deque = ArrayDeque<Triple<Int, Int, Int>>()
        deque.add(Triple(0, start.first, start.second))

        // We need a visited set as well
        val visited = mutableSetOf<Pair<Int, Int>>()
        visited.add(Pair(start.first, start.second))

        var result = 0

        while (deque.isNotEmpty()) {
            val (distance, r, c) = deque.removeFirst()
            listOf(
                Pair(r + 1, c), Pair(r - 1, c), Pair(r, c + 1), Pair(r, c - 1)
            ).forEach { t ->
                val nr = t.first
                val nc = t.second

                if (nr < 0 || nc < 0 || nr >= grid.size || nc >= grid[0].size) return@forEach
                if (visited.contains(t)) return@forEach
                if ((grid[nr][nc].toCharArray()[0].code - grid[r][c].toCharArray()[0].code) > 1) {
                    return@forEach
                }
                if (nr == end.first && nc == end.second) {
                    result = distance + 1
                    return@forEach
                }
                visited.add(t)
                deque.add(Triple(distance + 1, nr, nc))
            }
        }

        return result
    }
}

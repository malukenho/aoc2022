package com.github.malukenho.aoc2022

class Day9(list: List<String>) {

    operator fun Pair<Int, Int>.plus(o: Pair<Int, Int>) = Pair(this.first + o.first, this.second + o.second)

    private val visited: MutableSet<Pair<Int, Int>> = mutableSetOf()
    private val rope = MutableList(10) { Pair(0, 0) }

    private val loopUp = mapOf(
        "D" to Pair(0,  1),
        "U" to Pair(0, -1),
        "L" to Pair(-1, 0),
        "R" to Pair(1, -0),
    )
    data class Step(val direction: Pair<Int, Int>, val quantity: Int)

    private val instructions = list.map {
        val (direction, quantity) = it.split(" ")
        Step(loopUp[direction]!!, quantity.toInt())
    }

    fun part1(): Int {
        for (instruction in instructions) {
            for (move in 1..instruction.quantity) {
                rope[0] = rope[0] + instruction.direction
                for (i in 0..rope.size-2) {
                    val dx = rope[i].first - rope[i+1].first
                    val dy = rope[i].second - rope[i+1].second
                    if (Math.abs(dx) >= 2 || Math.abs(dy) >= 2)
                        rope[i+1] = rope[i+1] + Pair(dx.coerceIn(-1..1), dy.coerceIn(-1..1))
                }
                visited.add(rope[1])
            }
        }

        return visited.size
    }
}

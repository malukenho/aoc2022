package com.github.malukenho.aoc2022

class Day10(private val list: List<String>) {
    private val cycles = mutableListOf<Int>()

    fun part1(cycleTick: Int): Int {
        // The initially signal is 1
        cycles.add(1)
        list.map {
            if (it == "noop") {
                cycles.add(0)
            } else {
                cycles.add(0)
                cycles.add(it.split(" ").last().toInt())
            }
        }

        return cycles.chunked(cycleTick).first().sumOf { it } * cycleTick
    }

    fun part2(): String {
        list.map {
            if (it == "noop") cycles.add(0) else {
                cycles.add(0); cycles.add(it.split(" ").last().toInt())
            }
        }

        fun Int.range() = IntRange(this - 1, this + 1)
        var position = 1
        var stream = ""

        cycles.chunked(40).map {
            it.forEachIndexed { index, x ->
                stream += if (index in position.range()) "#" else "."
                if ((index + 1) % 40 == 0) stream += "\n"
                position += x
            }
        }

        return stream
    }
}

package com.github.malukenho.aoc2022

class Day10(private val list: List<String>) {

    private val cycles: List<Int> = buildList {
        // The initially signal is 1
        add(1)
        list.map {
            if (it == "noop") add(0) else {
                add(0); add(it.split(" ").last().toInt())
            }
        }
    }

    fun part1(cycleTick: Int): Int {
        return cycles.chunked(cycleTick).first().sumOf { it } * cycleTick
    }

    fun part2(): String {
        fun Int.range() = IntRange(this - 1, this + 1)
        var position = 1
        var stream = ""

        cycles.drop(1).chunked(40).map {
            it.forEachIndexed { index, x ->
                stream += if (index in position.range()) "#" else "."
                if ((index + 1) % 40 == 0) stream += "\n"
                position += x
            }
        }

        return stream
    }
}

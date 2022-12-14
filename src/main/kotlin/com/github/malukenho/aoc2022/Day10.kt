package com.github.malukenho.aoc2022

class Day10(private val list: List<String>) {
    private val cycles = mutableListOf<Int>()

    fun part1(cycleTick: Int): Int {
        // Initial is 1
        cycles.add(1)
        list.map {
            if (it == "noop") {
                cycles.add(0)
            } else {
                cycles.add(0)
                cycles.add(it.split(" ").last().toInt())
            }
        }

        return cycles.chunked(cycleTick ).first().sumOf { it } * cycleTick
    }
    // clock circuit
    // The clock circuit ticks at a constant rate; each tick is called a "cycle".
    // signal
    // signal strength (the cycle number multiplied by the value of the X register)
}

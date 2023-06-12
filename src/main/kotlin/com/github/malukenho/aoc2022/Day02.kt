package com.github.malukenho.aoc2022

import Util.readInput

class Day02 {
    enum class Played(val played: Int) {
        A(1), X(1),
        B(2), Y(2),
        C(3), Z(3)
    }

    fun part1(input: String): Int {
        return input
            .lines()
            .map { it.split(" ") }
            .map { match -> match.map { Played.valueOf(it).played } }
            .map {
                val opponent = it[0]
                val me = it[1]
                when {
                    (me == 3 && opponent == 1) -> me // lost
                    (opponent == 3 && me == 1) -> me + 6 // won
                    (me < opponent) -> me // lost
                    (me > opponent) -> me + 6// won
                    else -> me + 3 // draw
                }
            }
            .sum()
    }
}
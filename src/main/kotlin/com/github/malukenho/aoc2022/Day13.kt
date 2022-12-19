package com.github.malukenho.aoc2022

class Day13(val input: String) {

    // I had to use an Interator here to maintain the current "pointer"
    // state during the recursive calls otherwise it was getting
    // impossible to solve deep level list
    tailrec fun parser(input: Iterator<String>): List<Any> {
        fun go(c: Iterator<String>, acc: MutableList<Any>): MutableList<Any> {
            if (!c.hasNext()) return acc
            val input = c.next()
            if (input == "]") return acc
            if (input == "[") acc.add(go(c, mutableListOf()))
            if (input.toCharArray()[0].isDigit()) acc.add(input.toInt());
            return go(c, acc)
        }
        return go(input, mutableListOf())
    }

    fun part1(): Int = 10
}
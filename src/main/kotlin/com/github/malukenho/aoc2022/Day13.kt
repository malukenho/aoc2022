package com.github.malukenho.aoc2022

class Day13(val input: String) {

    private val lists = input.split("\n")
        .filter { it.isNotEmpty() }
        .map { it.parseLists() }
        .toMutableList()

    private fun String.parseLists(): Any {
        val stack: MutableList<MutableList<Any>> = mutableListOf(mutableListOf())
        this.replace("]", ",}").replace("[", "{,").replace(",,", ",").split(",").forEach {
            when (it) {
                "{" -> { val m: MutableList<Any> = mutableListOf(); stack.last().add(m); stack.add(m) }
                "}" -> stack.removeLast()
                else -> stack.last().add(it.toInt())
            }
        }
        return stack[0][0]
    }

    fun cmp(a: Any, b: Any): Int {
        if (a is Int && b is Int)
            return when { a < b -> -1; a > b -> 1; else -> 0 }
        val aList = if (a is MutableList<*>) a else mutableListOf(a)
        val bList = if (b is MutableList<*>) b else mutableListOf(b)
        for ((u, v) in aList zip bList)
            if (cmp(u!!, v!!) != 0)
                return cmp(u, v)
        return cmp(aList.size, bList.size)
    }

    fun part1(): Int {
        return lists
            .asSequence()
            .chunked(2)
            .withIndex()
            .filter { (_, pair) -> cmp(pair[0], pair[1]) <= 0 }
            .map { (i, _) -> i+1 }
            .sum()
    }

    fun part2(): Int {
        val distress = listOf(listOf(listOf(2)), listOf(listOf(6)))
        return lists
            .also { it.addAll(distress) }
            .sortedWith(::cmp)
            .withIndex()
            .filter { it.value in distress }
            .fold(1) { acc, (i, _) -> (i+1) * acc }
    }
}
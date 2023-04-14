package com.github.malukenho.aoc2022

import Util.readInput

private fun Char.toScore(): Int = if (this.isLowerCase()) this - '`' else this - '&'
infix fun String.intersect(other: String) = this.toSet() intersect other.toSet()

class Rucksack(val items: String) {
    private val boundary = (items.length / 2) - 1
    private val firstCompartment = items.substring(IntRange(0, boundary))
    private val secondCompartment = items.substring(boundary + 1)
    private val repeatedItem = firstCompartment intersect secondCompartment
    val value = repeatedItem.single().toScore()
}

class Group(private val elves: List<Rucksack>) {
    private val tokenItem = elves.map { rucksack ->
        rucksack.items.toCharArray()
            .filter { elves[0].items.contains(it) && elves[1].items.contains(it) && elves[2].items.contains(it) }
    }
        .flatten()
        .distinct()
        .single()

    val groupToken = tokenItem.toScore()
}

fun main() {
    val rucksacks = readInput(3)
        .lines()
        .map { Rucksack(it) }

    check(7872 == rucksacks.sumOf { it.value })

    val groupToken = rucksacks.chunked(3)
        .map { Group(it) }
        .sumOf { it.groupToken }

    check(2497 == groupToken)
}
package DayThree

import java.io.File

class Rucksack(val items: String) {
    private val boundary = (items.length / 2) - 1
    private val firstCompartment = items.substring(IntRange(0, boundary))
    private val secondCompartment = items.substring(boundary + 1)
    private val repeatedItem =
        firstCompartment.toCharArray().filter { secondCompartment.contains(it.toString()) }.distinct().first()
    val value = if (repeatedItem.isLowerCase()) repeatedItem.code - 96 else repeatedItem.code - 38
}

class Group(private val elves: List<Rucksack>) {
    private val tokenItem = elves.map { rucksack ->
        rucksack.items.toCharArray()
            .filter { elves[0].items.contains(it) && elves[1].items.contains(it) && elves[2].items.contains(it) }
    }
        .flatten()
        .distinct()
        .first()

    val groupToken = if (tokenItem.isLowerCase()) tokenItem.code - 96 else tokenItem.code - 38
}

fun main() {
    val rucksacks = File("src/main/kotlin/DayThree/input.txt").readText()
        .lines()
        .map { Rucksack(it) }

    check(7872 == rucksacks.sumOf { it.value })

    val groupToken = rucksacks.chunked(3)
        .map { Group(it) }
        .sumOf { it.groupToken }

    check(2497 == groupToken)
}
package com.github.malukenho.aoc2022

class Day11(input: String) {

    data class Item(var worryLevel: Long)
    data class Test(val divisibleBy: Long, val ifTrue: Int, val ifFalse: Int)
    data class Monkey(var monkeyBussiness: Long, val id: Int, var items: List<Item>, val operation: String, val test: Test)

    private val monkeys = input.split("\n\n").map {
        var (monkeyID, ifTrue, ifFalse) = listOf(0, 0, 0)
        var items: List<Item> = listOf()
        var operation = ""
        var divisibleBy = 0L
        it.lines().forEach { line -> when {
            line.startsWith("  Operation: ") -> operation = line.replace("  Operation: ", "")
            line.startsWith("  Test: divisible by ") -> divisibleBy = line.replace("  Test: divisible by ", "").toLong()
            line.startsWith("    If true: throw to monkey ") -> ifTrue = line.replace("    If true: throw to monkey ", "").toInt()
            line.startsWith("    If false: throw to monkey ") -> ifFalse = line.replace("    If false: throw to monkey ", "").toInt()
            line.startsWith("Monkey ") -> monkeyID = line.split(' ').last().trim(':').toInt()
            line.startsWith("  Starting items: ") -> items = line.replace("  Starting items: ", "").split(", ").map { Item(it.toLong()) }
        }}

        Monkey(0, monkeyID, items, operation, Test(divisibleBy, ifTrue, ifFalse))
    }

    private fun calculateMonkeyBusinessLevel(round: Int, transformer: (Long) -> Long): Long {
        val copy = monkeys.toMutableList()
        repeat(round) {
            copy.forEachIndexed { id, monkey ->
                monkey.items.forEach { item ->
                    monkey.monkeyBussiness++
                    var (operator, operation, operand) = monkey.operation.split(" ").drop(2)

                    operator = if (operator == "old") item.worryLevel.toString() else operator
                    operand = if (operand == "old") item.worryLevel.toString() else operand

                    item.worryLevel = when (operation) {
                        "+"  -> operand.toLong().plus(operator.toLong())
                        "*"  -> operand.toLong().times(operator.toLong())
                        else -> error("$operation not supported")
                    }

                    item.worryLevel %= copy.map { it.test.divisibleBy }.reduce(Long::times)
                    item.worryLevel = transformer(item.worryLevel)
                    monkey.items = monkey.items.drop(1)
                    copy[if (item.worryLevel % monkey.test.divisibleBy == 0L) monkey.test.ifTrue else monkey.test.ifFalse].items += item
                }
            }
        }
        val mostActive = copy.map { x -> x.monkeyBussiness }.sortedDescending().take(2)

        return mostActive[0] * mostActive[1]
    }

    fun part1(round: Int): Long = calculateMonkeyBusinessLevel(round) { x -> x / 3L }

    fun part2(round: Int): Long = calculateMonkeyBusinessLevel(round) { x -> x }
}
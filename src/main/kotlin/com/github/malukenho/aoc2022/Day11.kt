package com.github.malukenho.aoc2022

class Day11(private val input: String) {


    data class Item(var worryLevel: Long)
    data class Test(val divisibleBy: Long, val ifTrue: Int, val ifFalse: Int)
    data class Monkey(
        var monkeyBussiness: Long,
        val id: Int,
        var items: List<Item>,
        val operation: String,
        val test: Test,
    )

    private val monkeys = input.split("\n\n").map {
        var monkeyID = 0
        var items: List<Item> = listOf()
        var operation = ""
        var divisibleBy = 0L
        var ifTrue = 0
        var ifFalse = 0
        it.lines().forEach { line ->
            if (line.startsWith("Monkey ")) {
                monkeyID = line.split(' ').last().trim(':').toInt()
            }
            if (line.startsWith("  Starting items: ")) {
                items = line.replace("  Starting items: ", "")
                    .split(", ")
                    .map { Item(it.toLong()) }
            }
            if (line.startsWith("  Operation: ")) {
                operation = line.replace("  Operation: ", "")
            }
            if (line.startsWith("  Test: divisible by ")) {
                divisibleBy = line.replace("  Test: divisible by ", "").toLong()
            }
            if (line.startsWith("    If true: throw to monkey ")) {
                ifTrue = line.replace("    If true: throw to monkey ", "").toInt()
            }
            if (line.startsWith("    If false: throw to monkey ")) {
                ifFalse = line.replace("    If false: throw to monkey ", "").toInt()
            }
        }

        Monkey(0, monkeyID, items, operation, Test(divisibleBy, ifTrue, ifFalse))
    }

    fun compute(round: Int, transformer: (Long) -> Long): List<Monkey> {
        var copy = monkeys.toMutableList()
        repeat(round) {
            copy.forEachIndexed { id, monkey ->
                monkey.items.forEach { item ->

                    monkey.monkeyBussiness++

                    // Inspect
                    var (operator, operation, operand) = monkey.operation.split(" ").drop(2)

                    if (operator == "old") {
                        operator = item.worryLevel.toString()
                    }
                    if (operand == "old") {
                        operand = item.worryLevel.toString()
                    }
                    item.worryLevel = when (operation) {
                        "+" -> operand.toLong().plus(operator.toLong())
                        "*" -> operand.toLong().times(operator.toLong())
                        else -> error("$operation not supported")
                    }


                    item.worryLevel %= copy.map { it.test.divisibleBy.toLong() }.reduce(Long::times)
                    item.worryLevel = transformer(item.worryLevel)
                    monkey.items = monkey.items.drop(1)
                    copy[if (item.worryLevel % monkey.test.divisibleBy == 0L) monkey.test.ifTrue else monkey.test.ifFalse].items += item
                }
            }
        }
        return copy
    }

    fun part1(round: Int): Long {
        val result = compute(round) { x -> x / 3L }

        val mostActive = result.map { x -> x.monkeyBussiness }.sortedDescending().take(2)

        return mostActive[0] * mostActive[1]
    }

    fun part2(round: Int): Long {

        val result = compute(round) { x -> x }

        val mostActive = result.map { x -> x.monkeyBussiness }.sortedDescending().take(2)

        return mostActive[0] * mostActive[1]
    }
}

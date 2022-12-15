package com.github.malukenho.aoc2022

class Day11(private val input: String) {

    data class Item(var worryLevel: Int)
    data class Interest(val divisibleBy: Int, val ifTrue: Int, val ifFalse: Int)
    data class Monkey(
        var timesInspectedItem: Int = 0,
        val id: Int,
        var items: List<Item>,
        val operation: String,
        val interest: Interest
    )

    fun part1(round: Int): Int {
        val monkeys = input.split("\n\n").map {
            var monkeyID = 0
            var items: List<Item> = listOf()
            var operation = ""
            var divisibleBy = 0
            var ifTrue = 0
            var ifFalse = 0
            it.lines().forEach { line ->
                if (line.startsWith("Monkey ")) {
                    monkeyID = line.split(' ').last().trim(':').toInt()
                }
                if (line.startsWith("  Starting items: ")) {
                    items = line.replace("  Starting items: ", "")
                        .split(", ")
                        .map { Item(it.toInt()) }
                }
                if (line.startsWith("  Operation: ")) {
                    operation = line.replace("  Operation: ", "")
                }
                if (line.startsWith("  Test: divisible by ")) {
                    divisibleBy = line.replace("  Test: divisible by ", "").toInt()
                }
                if (line.startsWith("    If true: throw to monkey ")) {
                    ifTrue = line.replace("    If true: throw to monkey ", "").toInt()
                }
                if (line.startsWith("    If false: throw to monkey ")) {
                    ifFalse = line.replace("    If false: throw to monkey ", "").toInt()
                }
            }

            Monkey(0, monkeyID, items, operation, Interest(divisibleBy, ifTrue, ifFalse))
        }

        repeat(round) {
            monkeys.forEachIndexed { id, monkey ->
                println("Monkey ${id}:")
                monkey.items.forEach { item ->
                    println("  Monkey inspects an item with a worry level of ${item.worryLevel}.")

                    monkey.timesInspectedItem++

                    // Inspect
                    var (operator, operation, operand) = monkey.operation.split(" ").drop(2)

                    if (operator == "old") {
                        operator = item.worryLevel.toString()
                    }
                    if (operand == "old") {
                        operand = item.worryLevel.toString()
                    }
                    item.worryLevel = when (operation) {
                        "+" -> operand.toInt().plus(operator.toInt())
                        "*" -> operand.toInt().times(operator.toInt())
                        else -> error("$operation not supported")
                    }
                    val x = if (operation == "*") "is multiplied" else "increases"
                    println("    Worry level $x by $operand to ${item.worryLevel}.")

                    item.worryLevel /= 3
                    if (item.worryLevel % monkey.interest.divisibleBy == 0) {
                        println("    Monkey gets bored with item. Worry level is divided by 3 to ${item.worryLevel}.")
                        println("    Current worry level is divisible by ${monkey.interest.divisibleBy}.")
                        println("    Item with worry level ${item.worryLevel} is thrown to monkey ${monkey.interest.ifTrue}.")
                        monkey.items = monkey.items.drop(1)
                        monkeys[monkey.interest.ifTrue].items += item
                    } else {
                        println("    Monkey gets bored with item. Worry level is divided by 3 to ${item.worryLevel}.")
                        println("    Current worry level is not divisible by ${monkey.interest.divisibleBy}.")
                        println("    Item with worry level ${item.worryLevel} is thrown to monkey ${monkey.interest.ifFalse}.")
                        monkey.items = monkey.items.drop(1)
                        monkeys[monkey.interest.ifFalse].items += item
                    }
                }
            }
        }

        val mostActive = monkeys.map { x -> x.timesInspectedItem }.sortedDescending().take(2)

        return mostActive[0] * mostActive[1]
    }
}

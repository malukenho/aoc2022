package com.github.malukenho.aoc2022

import Util.readExample
import Util.readInput

class Day7(val list: List<String>) {
    var sizes : MutableMap<String, Int> = mutableMapOf()

    init {
        var structure = mutableMapOf<String, Int>()
        structure.put("", 0)
        var pwd = "/"

        fun shell(command: String): Unit {

            if (command.startsWith("ls")) return
            if (command.startsWith("dir")) return

            if (command.startsWith("cd")) {
                val dir = command.replace("cd ", "")

                pwd = if (dir == "..") {
                    "/" + pwd.substringBeforeLast('/', "")
                } else {
                    "/" + "$pwd/$dir".trim('/').trimStart('/')
                }

                return
            }

            if (command.toCharArray(0)[0].isDigit()) {

                val (size, name) = command.split(" ")

                var dir = pwd
                while (true) {
                    structure.put(dir, structure.getOrElse(dir) {0} + size.toInt())
                    if (dir.isEmpty()) break
                    dir = dir.substringBeforeLast("/", "")
                }
                return
            }
        }

        list.forEach { command ->
            shell(command.trimStart('$').trim())
        }

        sizes = structure
    }

    fun part1(): Int = sizes.values.sumOf { if (it <= 100000) it else 0 }

    fun part2(): Int {
        val total = sizes.getValue("")
        return sizes.values.asSequence().filter { 70000000 - (total - it) >= 30000000}.min()
    }
}
fun main() {

    val example = Day7(readExample(7).lines())

    check(94853 == example.sizes.get("/a")) { "Wrong /a size." }
    check(584 == example.sizes.get("/a/e")) { "Wrong /e size." }
    check(24933642 == example.sizes.get("/d")) { "Wrong /d size." }
    check(48381165 == example.sizes.get("")) { "Wrong total size." }

    val solution = Day7(readInput(7).lines())

    check(1582412 == solution.part1())
    check(3696336 == solution.part2())
}
package com.github.malukenho.aoc2022

sealed class Day7(list: List<String>) {
    private var sizes : MutableMap<String, Int> = mutableMapOf()

    init {
        var structure = mutableMapOf<String, Int>()
        structure.put("", 0)
        var pwd = "/"

        fun shell(command: String) {

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
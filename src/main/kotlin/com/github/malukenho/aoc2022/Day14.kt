package com.github.malukenho.aoc2022

//val p: Process<ForIO, String> =
//    Await<ForIO, BufferedReader, String>(
//        IO { BufferedReader(FileReader(fileName))}
//    ) { ei1: Either<Throwable, BufferedReader> ->
//        when (ei1) {
//            is Right -> processNext(ei1)
//            is Left -> Halt(ei1.value)
//        }
//
//    }

typealias Rocks = Pair<Int, Int>
typealias CoordinateList = MutableList<MutableList<Rocks>>

typealias Cave = MutableList<MutableList<Pair<Int, String>>>

fun Cave.getXY(x: Int, y: Int) = this.getOrNull(x)?.getOrNull(y)

fun CoordinateList.max() = this.flatMap { map -> map.map { it.first } }.max()
fun CoordinateList.min() = this.flatMap { map -> map.map { it.first } }.min()

class Day14(val input: String) {

    // Read coordinates per line
    private val routes: CoordinateList = input.split("\n").map { it ->
        it.split(" -> ").map { that ->
            val path = that.split(",")
            Pair(path.first().toInt(), path.last().toInt())
        }.toMutableList()
    }.toMutableList()

    private fun MutableList<MutableList<Pair<Int, String>>>.show() = this.forEach {
        println(it.joinToString("") { that -> that.second })
    }

    fun part1(): Int {
        // @TODO("Which name better reflects the problem proposed domain?")
        val columns: IntRange = routes.min()..routes.max()

        var cave: Cave = columns.map {
            columns.map { column -> Pair(column, ".") }.toMutableList()
        }.toMutableList()

        cave.show()
        routes.forEach { coordinates ->
            coordinates.windowed(2, 1, false).forEach {
                val first = it.first()
                val second = it.last()

                val moveDown = first.first == second.first
                val moveSide = !moveDown

                if (moveDown) { // 498 4..6
                    movingDown@ for (i in first.second..second.second) {
                        cave.get(i).set(first.first - columns.min(), Pair(first.first, "#"))
                    }
                }

                if (moveSide) {
                    val range = if (first.first > second.first) {
                        second.first..first.first
                    } else {
                        first.first..second.first
                    }

                    movingSideways@ for (i in range) {
                        cave.get(first.second).set(i - columns.min(), Pair(i, "#"))
                    }
                }
            }
        }

        // Sand is starting to drop from the cave
        //     It drops at location 500

        val source = 500
        var iterations = 0
        fun dropSand(cave: Cave, fallingX: Int, fallingY: Int, initial: Int): Cave {

            val currentChar = cave.getXY(fallingX, fallingY)?.second

            if (currentChar == null) {
                println("reset")
                return dropSand(cave, 0, initial, initial)
            }

            if (0 == fallingX && initial == fallingY && currentChar == "o") {
                println("oxe")
                return cave
            }

            if (currentChar == ".") {
                println("down we  go")
                return dropSand(cave, fallingX+1, fallingY, initial)
            }

            // Sand is already there
            if (currentChar == "o" || currentChar == "#") {
                val downLeftChar = cave.getXY(fallingX, fallingY - 1)?.second
                val downRightChar = cave.getXY(fallingX, fallingY + 1)?.second

                if (downLeftChar == null && iterations > 0) {
                    return cave
                }

                if (downLeftChar == ".") {
                    return dropSand(cave, fallingX+1, fallingY - 1, initial)
                }

                if (downRightChar == ".") {
                    return dropSand(cave, fallingX+1, fallingY + 1, initial)
                }

                if (downLeftChar == "o" && downRightChar == "o") {
                    println("rest - $currentChar")
                    cave.get(fallingX - 1).set(fallingY, Pair(source, "o"))

                    iterations++
                    cave.show()
                    return dropSand(cave, 0, initial, initial)
                }

                if (downLeftChar == "#" && downRightChar == "#") {
                    println("rest - $currentChar")
                    cave.get(fallingX - 1).set(fallingY, Pair(source, "o"))

                    iterations++
                    cave.show()
                    return dropSand(cave, 0, initial, initial)
                }

                // look left
                if (downLeftChar == "o" && cave.getXY(fallingX-1, fallingY - 1)?.second == ".") {
                    println("rest - downLeft o")
                    return dropSand(cave, fallingX, fallingY-1, initial)
                }

                if (downLeftChar == "#" && cave.getXY(fallingX+1, fallingY - 1)?.second == ".") {
                   println("rest - downLeft #")
                   return dropSand(cave, fallingX, fallingY-1, initial)
                }

                // look right
                if (downRightChar == "o" && cave.getXY(fallingX, fallingY + 1)?.second == ".") {
                    cave.get(fallingX - 1).set(fallingY, Pair(source, "o"))

                    iterations++
                    println("rest - downRight o")
                    cave.show()
                    return dropSand(cave, 0, initial, initial)
                }

                if (downRightChar == "#" && cave.getXY(fallingX, fallingY + 1)?.second == ".") {
                    cave.get(fallingX).set(fallingY + 1, Pair(source, "o"))

                    iterations++
                    println("rest - downRight #")
                    cave.show()
                    return dropSand(cave, 0, initial, initial)
                }
            }

            if (currentChar == "#" || currentChar == "o") {
                if (cave.getXY(fallingX, fallingY - 1)?.second == ".") {
                    println("skip left")
                    return dropSand(cave, fallingX, fallingY - 1, initial)
                }

                if (cave.getXY(fallingX, fallingY + 1)?.second == ".") {
                    println("skip right")
                    return dropSand(cave, fallingX, fallingY + 1, initial)
                }

                println("rest - normal $currentChar")
                cave.get(fallingX - 1).set(fallingY, Pair(source, "o"))

                iterations++
                cave.show()
                return dropSand(cave, 0, initial, initial)
            }

            return cave
        }

        cave = dropSand(cave, 0, source - columns.min(), source - columns.min())

        println("Final")
        cave.show()

        return iterations
    }
}
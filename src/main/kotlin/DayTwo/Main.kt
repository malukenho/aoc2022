package DayTwo

import java.io.File

enum class Played(val played: Int) {
    A(1), X(1),
    B(2), Y(2),
    C(3), Z(3)
}

fun main() {
//    val elves = "B X\nC Y\nA Z\nA X\nB Y\nC Z\nC X\nA Y\nB Z"
    val elves = File("src/main/kotlin/DayTwo/input-1.txt").readText()
        .lines()
        .map { it.split(" ") }
        .map { match -> match.map { Played.valueOf(it).played } }
        .map {
            val opponent = it[0]
            val me = it[1]
            when {
                (me == 3 && opponent == 1) -> me // lost
                (opponent == 3 && me == 1) -> me + 6 // won
                (me < opponent) -> me // lost
                (me > opponent) -> me + 6// won
                else -> me + 3 // draw
            }
        }

    println("Part 1:" + elves.sum())
    check(elves.sum() == 10595) { "${elves.sum()} !== 10595" }
}
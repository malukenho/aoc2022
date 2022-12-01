package DayOne

import java.io.File

data class Elf(val calories: Int)

fun main() {
    val file = File("src/main/kotlin/DayOne", "input.txt")

    // @todo I had to enable "-ea" on JVM to make assertions work at run time.
    //       Is there a way to make it work all the time? A config file per project?
    assert(file.isFile)

    val elfs: MutableCollection<Elf> = mutableListOf()
    var calories = 0

    file.forEachLine {
        if (it == "") {
            elfs.add(Elf(calories))

            calories = 0

            return@forEachLine
        }
        calories += it.toInt()
    }

    val elfsSortedByCalories = elfs.toSortedSet(compareBy { it.calories })

    println("MutableCollection<Elf>.size = " + elfs.size)
    println("SortedSet<Elf>.last() = " + elfsSortedByCalories.last())
}
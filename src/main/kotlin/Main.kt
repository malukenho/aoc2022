interface Monoid<T> {
    val unit: T
    val combine: (T, T) -> T
}

fun main() {

     fun <A, B, C> (A.(B) -> C).swap(): (B.(A) -> C) = { a: A -> //1
         a.this@swap(this)
     }

    fun <T> Monoid<T>.commutate(): Monoid<T> = object: Monoid<T> {
        override val unit: T
            get() = this@commutate.unit
        override val combine: T.(T) -> T
            get() = this@commutate.combine.swap()
    }

     fun concat(l: List<String>): String = if (l.isEmpty()) {
         ""
     } else {
         l.first() + concat(l.dropWhile { it == l.first() })
     }

    concat(listOf("He", "l", "lo", " Wor", "ld!")).also(::println)

    fun countRooms = TODO("Create an algo to count rooms in a maze.")

    val image = arrayOf(
        "..▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓...........".split("").toMutableList(),
        "..▓......................▓...▓▓▓▓▓...".split("").toMutableList(),
        "..▓..........▓▓▓▓▓▓▓▓....▓▓▓▓▓...▓...".split("").toMutableList(),
        "..▓..........▓......▓............▓...".split("").toMutableList(),
        "..▓..........▓▓▓▓▓▓▓▓.........▓▓▓▓...".split("").toMutableList(),
        "..▓▓▓▓▓▓......................▓......".split("").toMutableList(),
        ".......▓..▓▓▓▓▓.....▓▓▓▓▓▓▓▓▓▓▓......".split("").toMutableList(),
        ".......▓▓▓▓...▓▓▓▓▓▓▓................".split("").toMutableList(),
    )

    fun floodFill(image: Array<MutableList<String>>, coords: Pair<Int, Int>, fill: String): Array<MutableList<String>> {
        val (x, y) = coords

        if (image.getOrNull(x)?.getOrNull(y) == null || image[x][y] == fill || image[x][y] == "▓") {
            return image
        }

        image[x][y] = fill;

        floodFill(image, x to (y - 1), fill)
        floodFill(image, x to (y + 1), fill)
        floodFill(image, (x + 1) to y, fill)
        floodFill(image, (x - 1) to y, fill)

        return image
    }

    val filledImage = floodFill(image, 0 to 29, "░")

    println(filledImage.joinToString("\n") { it.joinToString("") })
}
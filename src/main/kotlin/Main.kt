interface Monoid<T> {
    val unit: T
    val combine: (T, T) -> T
}

fun main() {

    maze()

    fun <A, B, C> (A.(B) -> C).swap(): (B.(A) -> C) = { a: A -> //1
        a.this@swap(this)
    }

    fun <T> Monoid<T>.commutate(): Monoid<T> = object : Monoid<T> {
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

    fun countRooms(): Nothing = TODO("Create an algo to count rooms in a maze.")

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

        image[x][y] = fill

        floodFill(image, x to (y - 1), fill)
        floodFill(image, x to (y + 1), fill)
        floodFill(image, (x + 1) to y, fill)
        floodFill(image, (x - 1) to y, fill)

        return image
    }

    val filledImage = floodFill(image, 0 to 29, "░")

    println(filledImage.joinToString("\n") { it.joinToString("") })
}

fun maze() {
    val map = """
▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
▓S▓                 ▓       ▓ ▓   ▓     ▓         ▓     ▓   ▓         ▓
▓ ▓▓▓▓▓ ▓▓▓▓▓▓▓▓▓ ▓ ▓▓▓ ▓▓▓ ▓ ▓ ▓ ▓ ▓▓▓ ▓ ▓ ▓▓▓▓▓ ▓ ▓▓▓ ▓ ▓ ▓▓▓▓▓ ▓ ▓▓▓
▓ ▓   ▓     ▓     ▓     ▓   ▓ ▓ ▓   ▓ ▓   ▓ ▓       ▓ ▓ ▓ ▓     ▓ ▓   ▓
▓ ▓ ▓ ▓▓▓▓▓ ▓ ▓▓▓▓▓▓▓▓▓▓▓ ▓▓▓ ▓ ▓▓▓▓▓ ▓▓▓▓▓ ▓▓▓▓▓▓▓▓▓ ▓ ▓ ▓▓▓▓▓ ▓▓▓ ▓ ▓
▓   ▓     ▓ ▓ ▓     ▓   ▓   ▓   ▓         ▓       ▓   ▓   ▓   ▓   ▓ ▓ ▓
▓▓▓▓▓▓▓▓▓ ▓ ▓ ▓ ▓▓▓▓▓ ▓ ▓▓▓ ▓ ▓▓▓▓▓▓▓▓▓▓▓ ▓▓▓▓▓▓▓ ▓ ▓ ▓▓▓▓▓ ▓▓▓▓▓ ▓▓▓ ▓
▓       ▓ ▓ ▓ ▓     ▓ ▓     ▓ ▓   ▓   ▓   ▓     ▓ ▓ ▓   ▓         ▓   ▓
▓ ▓ ▓▓▓▓▓ ▓ ▓ ▓▓▓ ▓ ▓ ▓▓▓▓▓▓▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓ ▓▓▓▓▓ ▓▓▓ ▓▓▓ ▓▓▓▓▓▓▓▓▓ ▓ ▓
▓ ▓ ▓   ▓ ▓ ▓   ▓ ▓ ▓     ▓     ▓   ▓   ▓   ▓   ▓   ▓     ▓         ▓ ▓
▓▓▓ ▓ ▓ ▓ ▓ ▓▓▓ ▓ ▓ ▓▓▓▓▓ ▓▓▓▓▓▓▓ ▓▓▓▓▓▓▓▓▓▓▓ ▓ ▓▓▓ ▓ ▓▓▓▓▓ ▓▓▓▓▓ ▓▓▓ ▓
▓   ▓ ▓   ▓ ▓   ▓ ▓     ▓   ▓     ▓       ▓   ▓     ▓ ▓     ▓     ▓   ▓
▓ ▓▓▓ ▓▓▓▓▓▓▓ ▓▓▓▓▓ ▓▓▓ ▓▓▓ ▓▓▓▓▓▓▓ ▓▓▓▓▓ ▓ ▓▓▓▓▓▓▓▓▓ ▓▓▓ ▓▓▓ ▓▓▓▓▓ ▓▓▓
▓   ▓         ▓     ▓     ▓       ▓   ▓ ▓   ▓ ▓     ▓   ▓ ▓   ▓ ▓   ▓ ▓
▓▓▓ ▓▓▓▓▓▓▓▓▓▓▓ ▓ ▓▓▓▓▓▓▓ ▓▓▓▓▓▓▓ ▓▓▓ ▓ ▓▓▓▓▓ ▓ ▓ ▓▓▓▓▓ ▓ ▓ ▓▓▓ ▓ ▓▓▓ ▓
▓   ▓   ▓       ▓ ▓     ▓   ▓   ▓     ▓       ▓ ▓ ▓     ▓ ▓ ▓   ▓ ▓   ▓
▓ ▓▓▓ ▓ ▓ ▓▓▓▓▓▓▓ ▓ ▓▓▓ ▓▓▓▓▓ ▓ ▓▓▓▓▓▓▓ ▓▓▓ ▓▓▓ ▓ ▓ ▓▓▓▓▓▓▓ ▓ ▓ ▓ ▓▓▓ ▓
▓     ▓         ▓     ▓       ▓           ▓     ▓           ▓ ▓      E▓
▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
        """.split("\n")

    val maze = map.map { s -> s.split("") }

    // Get the height and width of the maze:
    val height = maze.size

    fun printMaze(maze: List<List<String>>) {
        maze.forEach { row ->
            row.forEach { column ->
                print(column)
            }
            println()
        }
    }

    fun findStart(maze: List<List<String>>): Pair<Int, Int> {
        for ((y, row) in maze.withIndex()) {
            for ((x, char) in row.withIndex()) {
                if ("S" == char) {
                    return Pair(y, x)
                }
            }
        }

        error("Could not find the START")
    }

    fun solveMaze(
        maze: List<List<String>>,
        x: Int,
        y: Int,
        height: Int,
        visited: Set<Pair<Int, Int>>
    ): Pair<Boolean, List<List<String>>> {
        if (maze[y][x] == "E") {
            return true to maze // Found the exit, return true.
        }

        if (maze[y][x] == "▓") {
            return false to maze
        }

        // Mark the path in the maze
        val walkInMaze = maze.mapIndexed { rowY, row ->
            row.mapIndexed { columnX, column ->
                if (y == rowY && x == columnX) "." else column
            }
        }

        val updatedVisited = visited + Pair(y, x)

//        printMaze(walkInMaze) // Uncomment to view each forward step.

        val adjacentPoints = listOf(
            y + 1 to x,
            y - 1 to x,
            y to x + 1,
            y to x - 1
        )

        for (p in adjacentPoints) {
            val s = maze.getOrNull(p.first)?.getOrNull(p.second)
            if ((s != null) && !visited.contains(p)) {
                val r = solveMaze(walkInMaze, p.second, p.first, height, updatedVisited)
                if (r.first) {
                    return r
                }
            }
        }

        return false to maze // BASE CASE
    }

    val startPoint = findStart(maze)

    val x = solveMaze(maze = maze, startPoint.second, startPoint.first, height, mutableSetOf())

    printMaze(x.second)
}
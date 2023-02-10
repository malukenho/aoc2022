fun main() {
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
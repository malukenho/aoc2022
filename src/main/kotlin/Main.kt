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

    fun paint(image: Array<MutableList<String>>, x: Int, y: Int, symbol: String): Array<MutableList<String>> {
        if (image.getOrNull(x)?.getOrNull(y) == null || image[x][y] == symbol || image[x][y] == "▓") {
            return image
        }

        image[x][y] = symbol;

        var imagem = paint(image, x, y - 1, symbol)
        imagem = paint(imagem, x, y + 1, symbol)
        imagem = paint(imagem, x + 1, y, symbol)
        imagem = paint(imagem, x - 1, y, symbol)

        return imagem
    }

    paint(image, 3, 16, "░").joinToString("\n") { it.joinToString("") }.also(::println)

    println("\n\n")

//    paint(image, 6, 3, "░").joinToString("\n") { it.joinToString("") }.also(::println)
//
//    println("\n\n")
//
//    paint(image, 1, 7, "░").joinToString("\n") { it.joinToString("") }.also(::println)
}
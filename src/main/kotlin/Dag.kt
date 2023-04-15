fun main() {

    val toOrder = listOf(9,2,6,3,5,2,1,0)
    val ordered = toOrder.toMutableList()

    repeat(toOrder.size - 1) {
        for (k in ordered.indices) {
            val next = ordered.getOrNull(k + 1) ?: continue
            val current = ordered[k]

            if (current > next) {
                ordered[k] = next
                ordered[k + 1] = current
            }
        }
    }

    println(ordered.toList())
    data class Node(val data: String, val children: MutableList<Node> = mutableListOf())

    val root = Node("Alice")
    val node2 = Node("Bob")
    val node3 = Node("Caroline")
    val node4 = Node("Darya")
    val node5 = Node("Eve")
    val node6 = Node("Fred")
    val node7 = Node("Gonzalo")
    val node8 = Node("Hadassah")

    root.children.add(node2)
    root.children.add(node3)

    node2.children.add(node4)

    node3.children.add(node5)
    node3.children.add(node6)

    node5.children.add(node7)
    node5.children.add(node8)

    fun traverse(node: Node) {
        node.children.forEach(::traverse)
        println(node.data)
    }

    fun find8LetterName(node: Node): String? {
        if (node.data.length == 8) {
            return node.data
        }
        node.children.forEach { e ->
            if (find8LetterName(e) is String) {
                return e.data
            }
        }

        return null
    }

    fun getDepth(node: Node): Int {
        if (node.children.size == 0) {
            return 0;
        }

        var depth: Int = 0
        node.children.forEach { e ->
            val v = getDepth(e)
            if (v > depth) depth = v
        }

        return 1 + depth
    }

//    println(getDepth(root))

//    println(find8LetterName(root))
}
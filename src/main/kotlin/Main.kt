fun main() {

    val totalDisks = 6
    val tower = mutableListOf(
        mutableListOf(1, 2, 3, 4, 5, 6), // Reuse [totalDisks] here
        mutableListOf(),
        mutableListOf()
    )

    fun moveOneDisk(tower: MutableList<MutableList<Int>>, start: Int, end: Int) {
        tower[end].add(tower[start].removeFirst())
    }


}
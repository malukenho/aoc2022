fun main() {

    fun factorial(i: Int): Int {
        tailrec fun go(n: Int, acc: Int): Int =
            if (n <= 0) acc
            else go(n -1, n * acc)
        return go(i, 1)
    }

    factorial(30).also(::println)

    fun countDownAndUp(number: Int) {
        println(number)
        if (number == 0) {
            println("Reached the base case.")
            return
        } else {
            countDownAndUp(number - 1)
            println("$number returning")
            return
        }
    }

    countDownAndUp(3)

//    fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> =
//        foldRight(xs, List.empty()) { a: A, xa: List<B> ->
//            Const(f(a), xa)
//        }
}
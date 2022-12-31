fun main() {

    fun factorial(i: Int): Int {
        tailrec fun go(n: Int, acc: Int): Int =
            if (n <= 0) acc
            else go(n -1, n * acc)
        return go(i, 1)
    }

    factorial(30).also(::println)

    fun countDownAndUp(number: Int) {
        if (number == 0) return
        else { countDownAndUp(number - 1); return }
    }

    countDownAndUp(3)

//    fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> =
//        foldRight(xs, List.empty()) { a: A, xa: List<B> ->
//            Const(f(a), xa)
//        }
}
package DaySix

import Util.readInput
import java.lang.RuntimeException
import kotlin.math.max

fun main() {

    fun solutionWithSteps(step: Int, input: String): Int {
        input.chunked(1).forEachIndexed { index, _ ->
            if (index < step) return@forEachIndexed

            if (input.subSequence(max(0, index - step), max(index, step))
                    .chunked(1)
                    .distinct().size == step
            ) {
                return index
            }
        }

        throw RuntimeException("Couldnt find the number")
    }

    check(7 == solutionWithSteps(4, "mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    check(5 == solutionWithSteps(4, "bvwbjplbgvbhsrlpgdmjqwftvncz"))
    check(6 == solutionWithSteps(4, "nppdvjthqldpwncqszvftbrmjlhg"))
    check(10 == solutionWithSteps(4, "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
    check(11 == solutionWithSteps(4, "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    check(1623 == solutionWithSteps(4, readInput(6)))

    check(19 == solutionWithSteps(14, "mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    check(23 == solutionWithSteps(14, "bvwbjplbgvbhsrlpgdmjqwftvncz"))
    check(23 == solutionWithSteps(14, "nppdvjthqldpwncqszvftbrmjlhg"))
    check(29 == solutionWithSteps(14, "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
    check(26 == solutionWithSteps(14, "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    check(3774 == solutionWithSteps(14, readInput(6)))
}
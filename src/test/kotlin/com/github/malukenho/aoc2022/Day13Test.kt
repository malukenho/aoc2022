package com.github.malukenho.aoc2022

import Util.readInput
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class Day13Test {
    private fun String.toIterator() =
        this.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex()).map { it.trim() }.filter { it.isNotBlank() }
            .filter { it != "," }.iterator()

    @Test
    fun compare() {
        assertEquals(callCompare("[1,1,3,1,1]", "[1,1,5,1,1]"), Side.RIGHT_ORDER)
        assertEquals(callCompare("[[1],[2,3,4]]", "[[1],4]"), Side.RIGHT_ORDER)
        assertEquals(callCompare("[9]", "[[8,7,6]]"), Side.WRONG_ORDER)
        assertEquals(callCompare("[[4,4],4,4]", "[[4,4],4,4,4]"), Side.RIGHT_ORDER)
        assertEquals(callCompare("[7,7,7,7]", "[7,7,7]"), Side.WRONG_ORDER)
        assertEquals(callCompare("[]", "[3]"), Side.RIGHT_ORDER)
        assertEquals(callCompare("[[[]]]", "[[]]"), Side.WRONG_ORDER)
        assertEquals(
            callCompare("[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]"), Side.WRONG_ORDER
        )
    }

    private fun callCompare(left: String, right: String): Side {
        val s = Day13(SAMPLE_INPUT)
        return s.compare(s.parser(right.toIterator()), s.parser(left.toIterator()))
    }

    @Test
    fun part1() {
        assertEquals(Day13(SAMPLE_INPUT).part1(), 13)
        assertEquals(Day13(readInput(13)).part1(), 0)
    }

    companion object {
        private const val SAMPLE_INPUT =
            "[1,1,3,1,1]\n" +
            "[1,1,5,1,1]\n" +
            "\n" +
            "[[1],[2,3,4]]\n" +
            "[[1],4]\n" +
            "\n" +
            "[9]\n" +
            "[[8,7,6]]\n" +
            "\n" +
            "[[4,4],4,4]\n" +
            "[[4,4],4,4,4]\n" +
            "\n" +
            "[7,7,7,7]\n" +
            "[7,7,7]\n" +
            "\n" +
            "[]\n" +
            "[3]\n" +
            "\n" +
            "[[[]]]\n" +
            "[[]]\n" +
            "\n" +
            "[1,[2,[3,[4,[5,6,7]]]],8,9]\n" +
            "[1,[2,[3,[4,[5,6,0]]]],8,9]"
    }
}

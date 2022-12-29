package com.github.malukenho.aoc2022

import Util.readInput
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class Day13Test {
    private fun String.toIterator() =
            this.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex())
                .filter { it.isNotBlank() }
                .filter { it != "," }
                .iterator()

    @Test
    fun parser() {
        assertEquals(Day13(SAMPLE_INPUT).parser("[[1],4]".toIterator()).toString(), "[[[1], 4]]")
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

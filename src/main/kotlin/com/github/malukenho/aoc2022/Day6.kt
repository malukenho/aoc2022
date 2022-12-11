package com.github.malukenho.aoc2022

import Util.readInput
import java.lang.RuntimeException
import kotlin.math.max

fun main() {
    fun String.rangeIsMarker(index: Int, step: Int): Boolean = step == this.subSequence(max(0, index - step), max(index, step))
        .chunked(1)
        .distinct()
        .count()

    fun findMarkerWithStep(step: Int, input: String): Int {
        input.chunked(1).forEachIndexed { index, _ ->
            if (index < step) return@forEachIndexed
            if (input.rangeIsMarker(index, step)) return index
        }

        throw RuntimeException("Couldnt find the number")
    }

    check(1623 == findMarkerWithStep(4, readInput(6)))
    check(3774 == findMarkerWithStep(14, readInput(6)))
}
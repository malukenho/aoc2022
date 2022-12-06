package Util

import java.io.File

fun readInput(day: Int): String = File("input/input-$day.txt").readText()
fun readExample(day: Int): String = File("input/example-$day.txt").readText()

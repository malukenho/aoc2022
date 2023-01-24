package com.github.malukenho.aoc2022

enum class Side { RIGHT_ORDER, WRONG_ORDER }

class Day13(val input: String) {

    private fun String.toIterator() =
        this.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex()).map { it.trim() }.filter { it.isNotBlank() }
            .filter { it != "," }.iterator()

    // I had to use an Interator here to maintain the current "pointer"
    // state during the recursive calls otherwise it was getting
    // impossible to solve deep level list
    // TODO: We can parse it as a JSON !!!!!!!!!!!!!!!!!!!!!!!
    tailrec fun parser(input: Iterator<String>): List<Any> {
        fun go(c: Iterator<String>, acc: MutableList<Any>): MutableList<Any> {
            if (!c.hasNext()) return acc
            val s = c.next()
            if (s == "]") return acc
            if (s == "[") acc.add(go(c, mutableListOf()))
            if (s.toCharArray().first().isDigit()) acc.add(s.toInt())
            return go(c, acc)
        }
        return go(input, mutableListOf())
    }

    fun compare(right: List<*>, left: List<*>): Side {
        fun go(index: Int, right: List<*>, left: List<*>): Side {
            val rightV = right.getOrNull(index)
            val leftV = left.getOrNull(index)

            // Draw null
            if (rightV == null) return if (leftV != null) Side.WRONG_ORDER else Side.RIGHT_ORDER
            if (leftV == null) return Side.RIGHT_ORDER
            if (rightV is List<*> && leftV is List<*>) return go(0, rightV, leftV)
            if (rightV is Int && leftV is List<*>) return go(index, listOf(rightV), leftV)
            if (rightV is List<*> && leftV is Int) return go(index, rightV, listOf(leftV))

            return if (leftV as Int > rightV as Int) Side.WRONG_ORDER else go(index+1, right, left)
        }

        return go(0, right, left)
    }

    fun part1(): Int {
        val pairs = input.split("\n\n").map { it.split("\n") }

        var counter = 0
        pairs.forEachIndexed { index, xs ->
            val (left, right) = xs
            if (compare(parser(right.toIterator()), parser(left.toIterator())) == Side.RIGHT_ORDER) {
                counter += index + 1
            }
        }

        return counter
    }
}
package com.github.malukenho.aoc2022

enum class Side { RIGHT_ORDER, WRONG_ORDER }

class Day13(val input: String) {

    private fun String.toIterator() =
        this.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex())
            .map { it.trim() }
            .filter { it.isNotBlank() }
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

    fun part1(left: String, right: String): Side {
        val rightSide = parser(right.toIterator())[0] as List<*>
        val leftSide = parser(left.toIterator())[0] as List<*>

        println("\n- Compare  $left vs $right")

        var index = 0
        while (true) {
            var rightV = rightSide.getOrNull(index)
            var leftV = leftSide.getOrNull(index)

            if (rightV == null) {
                return if (leftV != null) {
                    Side.WRONG_ORDER
                } else {
                    Side.RIGHT_ORDER
                }
            }

            var rightLoop = 0
            while (rightV is List<*>) {
                rightLoop++
                println("    - Compare $leftV vs $rightV")
                rightV = rightV.getOrNull(0)
            }

            var leftLoop = 0
            while (leftV is List<*>) {
                leftLoop++
                println("    - Compare $leftV vs $rightV")
                leftV = leftV.getOrNull(0)
            }

            if (rightV == null && leftLoop > rightLoop) {
                return Side.WRONG_ORDER
            }

            if (rightV is Int && leftV is Int) {
                println("    - Compare $leftV vs $rightV")
                if (leftV > rightV) {
                    println("        - Left side is GREATER, so inputs are in the WRONG order")
                    return Side.WRONG_ORDER
                }
            }

            index++
        }

        @Suppress("UNREACHABLE_CODE") return Side.RIGHT_ORDER
    }
}
package DaySeven

import Util.readExample
import Util.readInput
import java.lang.RuntimeException
import java.nio.file.Files
import java.util.regex.Pattern.LITERAL
import javax.print.attribute.standard.RequestingUserName
import kotlin.math.max

sealed class Option<out A> {
    companion object
}

data class Some<out A>(val get: A) : Option<A>()
object None : Option<Nothing>()

// functors
fun <A, B> Option<A>.map(f: (A) -> B): Option<B> =
    when (this) {
        is Some -> Option.unit(f(this.get))
        is None -> None
    }

fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> =
    this.map { a -> f(a) }.getOrElse { None }

fun <A> Option.Companion.unit(a: A): Option<A> = Some(a)

fun <A> Option<A>.getOrElse(f: () -> A): A =
    when (this) {
        is Some -> this.get
        is None -> f()
    }

abstract class Filesystem(open val path: String, open val size: Int)
data class Dir(override val path: String, val files: List<Filesystem>, override val size: Int): Filesystem(path, size)
data class File(override val path: String, override val size: Int): Filesystem(path, size)

fun main() {
    fun part1(input: String): MutableMap<String, Int> {

        var structure = mutableMapOf<String, Int>()
        structure.put("", 0)
        var pwd = "/"

        fun shell(command: String): String {
            if (command.startsWith("cd")) {
                val dir = command.replace("cd ", "")

                // TODO("Create structure for directories")
                pwd = if (dir == "..") {
                    "/" + pwd.substringBeforeLast('/', "")
                } else {
                   "/" + "$pwd/$dir".trim('/').trimStart('/')
                }

                return ""
            }

            if (command.startsWith("dir")) {
//                structure.add(Dir(command.split(" ")[1], emptyList(), 0))
                return ""
            }
            if (command.toCharArray(0)[0].isDigit()) {

                val (size, name) = command.split(" ")
//                structure.put("$pwd/$name", size.toInt()))

                var dir = pwd
                while (true) {
                    structure.put(dir, structure.getOrElse(dir) {0} + size.toInt())
                    if (dir.isEmpty()) break
                    dir = dir.substringBeforeLast("/", "")
                }
                return ""
            }

            if (command.startsWith("ls")) {
                return pwd
            }

            error("Command not handled. $command")
        }

        input.lines().forEach { command ->
            shell(command.trimStart('$').trim())
        }


        return structure
    }

    val structure: MutableMap<String, Int> = part1(readExample(7));
//    structure.also(::println)

    check(94853 == structure.get("/a")) { "Wrong /a size." }
    check(584 == structure.get("/a/e")) { "Wrong /e size." }
    check(24933642 == structure.get("/d")) { "Wrong /d size." }
    check(48381165 == structure.get("")) { "Wrong total size." }

    val part1: MutableMap<String, Int> = part1(readInput(7));
    check(1582412 == part1.values.sumOf { if (it <= 100000) it else 0 })

//    check(94853 == structure.filter { it.path.startsWith("/a") }.also(::println).sumOf { it.size }) { "Wrong /a size." }
//    check(584 == structure.filter { it.path.startsWith("/a/e") }.also(::println).sumOf { it.size }) { "Wrong /e size." }
//    check(24933642 == structure.filter { it.path.startsWith("/d") }.also(::println).sumOf { it.size }) { "Wrong /d size." }
//    check(48381165 == structure.sumOf { it.size }.also(::println)) { "Wrong total size." }
}
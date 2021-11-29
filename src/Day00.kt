import java.io.File

fun main() {
    val input1 = File("day01.txt").readText()
    val input2 = File("day01.txt").readLines()
    val parts = input1.split("-").map { it.toInt() }
    val ints = input2.map { it.toInt() }

    var regexp = """(\d)-(\d)-(\d)""".toRegex()
    val input3 = "1-2-3"
    val (a,b,c) = regexp.find(input3)?.destructured ?: error("bad input")

    input3.substringBefore("-")
    input3.substringAfter("-")
    input3.substringBeforeLast("-")

}
import java.io.File
import java.math.BigInteger

fun main() {
    val input = File(
       // "day07_.txt"
       "day07.txt"
    ).readLines().map{it.split(",")}
    val a = input[0].map{it.toInt()}.toIntArray().toMutableList()
    val partA =  (a.minOrNull()?.rangeTo(a.maxOrNull()!!)!!).map { pos ->
        a.map { Math.abs(it - pos)}.sum()
    }.minOrNull()
    println("partA:   $partA")
    val partB =  (a.minOrNull()?.rangeTo(a.maxOrNull()!!)!!).map { pos ->
            a.map { (Math.abs(it - pos).toLong() * (Math.abs(it - pos) + 1)) / 2 }.sum()
        }.minOrNull()
    println("partB:   $partB")
}

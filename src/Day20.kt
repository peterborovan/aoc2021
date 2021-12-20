import java.io.File
import java.lang.Math.pow

val iterations = 50
val iterations1 = 2*iterations
var xlow = -iterations1
var xhigh = 100+iterations1
var ylow = -iterations1
var yhigh = 100+iterations1
fun main() {
    val inp = File(
        "day20.txt"
   //"day20_.txt"
    ).readLines()
    val firstLine = inp.takeWhile {it.isNotEmpty()}[0]
    val grid = inp.dropWhile {it.isNotEmpty()}.drop(1)

    var gr = mutableSetOf<Pair<Int,Int>>()
    for(r in 0 until grid.size)
        for(c in 0 until grid[r].length)
            if (grid[r][c] == '#')
                gr.add(Pair(r,c))

    repeat(iterations) {
        if (it % 2 == 0)
            println("$it ${gr.size}")
        val newgr = mutableSetOf<Pair<Int, Int>>()
        (xlow..xhigh).forEach { r ->
            (ylow..yhigh).forEach { c ->
                if (firstLine[(-1..1).flatMap { dr -> (-1..1).map { dc->if (Pair(r+dr, c+dc) in gr) (1 shl 3*(-dr+1)+(-dc+1)) else 0} }.sum()] == '#')
                    newgr.add(Pair(r, c))
            }
        }
        gr = newgr
        xlow++; xhigh--; ylow++ ;yhigh--
    }
    println("${gr.size}")
}

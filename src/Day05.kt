import java.io.File

fun main() {
    val input =
        //File("day05_.txt")
        File("day05.txt")
        .readLines().map { it.replace("->", ",")}.map {it.split(",")}
    println("partA ${countAtLeast2(true, input)}")
    println("partB ${countAtLeast2(false, input)}")
}

private fun countAtLeast2(partA : Boolean, input: List<List<String>>) : Int {
    val size = 1000
    val board = Array(size) { Array(size) { 0 } }
    for (l in input) {
        val (xx1, yy1, x2, y2) = l.map { it.trim() }.map { it.toInt() }
        var x1 = xx1
        var y1 = yy1
        val dx = if (x1 == x2) 0 else if (x1 < x2) 1 else -1
        val dy = if (y1 == y2) 0 else if (y1 < y2) 1 else -1
        if (partA && dx * dy != 0) continue
        repeat(1 + Math.max(Math.max(x1, x2) - Math.min(x1, x2),Math.max(y1, y2) - Math.min(y1, y2))) {
            board[x1][y1]++
            x1 += dx
            y1 += dy
        }
    }
    return board.sumOf({ it.count { it > 1 } })
}
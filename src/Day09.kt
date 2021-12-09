import java.io.File

val nexts = listOf(     Pair(-1,0),    Pair(0,-1),    Pair(0,1),    Pair(1,0))
var visited = mutableListOf<Pair<Int,Int>>()

fun bazen(r: Int, c : Int, a : List<String>) : Int {
    if (r >= 0 && c >= 0 && r < a.size && c < a[r].length && Pair(r,c) !in visited && a[r][c] != '9') {
            visited.add(Pair(r,c))
            return 1+nexts.sumOf { (dr, dc) -> bazen(r+dr, c+dc, a) }
    } else return 0
}

fun main() {
    val input: List<String> = File(
       // "day09_.txt"
       "day09.txt"
    ).readLines()
    val partA =
        (0 until input.size).sumOf { r->
            (0 until input[r].length).sumOf { c ->
                val lower = nexts.all { (dr, dc) ->
                    try {
                        val next = input[r + dr][c + dc]
                        input[r][c] < next
                    } catch (e: Exception) {
                        true
                    }
                }
                if (lower) input[r][c].digitToInt()+1 else 0
            }
        }
    println("partA: $partA")

    val bs = mutableListOf<Int>()
    for (r in 0 until input.size)
        for (c in 0 until input[r].length)
            if (Pair(r,c) !in visited && input[r][c] != '9') {
                bs.add(bazen(r,c,input))
                bazen(r,c,input)
            }
    val ss = bs.toList().sorted()
    (ss.size-3).let {
        println("partB : ${ss[it] * ss[it+1] * ss[it+2]} ")
    }
}

import java.io.File

fun main() {
    val inp: List<List<Int>> = File(
       //   "day15_.txt"
        "day15.txt"
    ).readLines().map { it.toCharArray().map { it.digitToInt()} }
    val size = inp.size
    val a: Array<Array<Int>> = Array (size) { Array(size) { Int.MAX_VALUE}}
    println("partA: ${cesta(a, inp)}")
    //----
    val aa: Array<Array<Int>> = Array (5*size) { Array(5*size) { Int.MAX_VALUE}}
    val ninp = (0 until 5*size).map { i ->
                    (0 until 5*size).map { j ->
                        (inp[i % size][j % size] + (i / size) + (j / size)-1)%9+1
                    }
                }
    println("partB: ${cesta(aa, ninp)}")
}

fun cesta(a: Array<Array<Int>>, inp : List<List<Int>>) : Int {
    val vis = listOf(Pair(0,0))
    val queue = mutableMapOf(0 to listOf(Pair(0,0)))
    while (queue.isNotEmpty()) {
        val minCost = queue.keys.minOrNull()!!
        val minCells = queue[minCost]
        for ((r,c) in minCells!!)
            if (r == a.size-1 && c == a[0].size -1)
                return minCost
            else
                for ((rr,cc) in listOf(Pair(r-1,c), Pair(r+1,c), Pair(r,c-1), Pair(r,c+1) ))
                    if (rr >= 0 && rr < a.size && cc >= 0 && cc < a[0].size && Pair(rr,cc) !in vis) {
                        val newCost = minCost + inp[rr][cc]
                        if (a[rr][cc] > newCost) {
                            a[rr][cc] = newCost
                            queue.putIfAbsent(newCost, listOf())
                            queue.computeIfPresent(newCost, {_,v -> v.plus(Pair(rr,cc))})
                        }
                    }
        queue.remove(minCost)
    }
    return -1
}

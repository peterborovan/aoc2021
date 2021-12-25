import java.io.File

val east = '>'
val south = 'v'
fun main() {
    var inp = File(
      "day25.txt"
//        "day25_.txt"
       // "day25__.txt"
    ).readLines().map{ it.toCharArray()}.toTypedArray()
    var step = 0
    do {
        step++
        val (b, moved1) = move(1, inp)
        val (c, moved2) = move(0, b)
        inp = c
    } while (moved1 || moved2)
    println("partA: $step")
}

private fun move(phase: Int, inp: Array<CharArray>) : Pair<Array<CharArray>,Boolean> {
    val M = inp.size
    val N = inp[0].size
    val b = Array(M) { CharArray(N) { '.' } }
    var moved = false
    val sign = if (phase > 0) east else south
    (0 until M).forEach { i->
        (0 until N).forEach { j->
            val di = (i + (1-phase) ) % M
            val dj = (j + phase) % N
            if (inp[i][j] == sign && inp[di][dj] == '.') {
                    b[di][dj] = sign
                    moved = true
            } else if (inp[i][j] in "$east$south") b[i][j] = inp[i][j]
        }
    }
    return Pair(b, moved)
}
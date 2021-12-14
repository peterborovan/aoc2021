import java.io.File

fun main() {
    val input = File(
        //"day14_.txt"
        "day14.txt"
    ).readLines()

    val start = input.takeWhile { it.isNotEmpty() }[0]
    //println(start)
    val map = mutableMapOf<String, String>()
    val rules = input.dropWhile { it.isNotEmpty() }.drop(1).map { l ->
        val x = l.split(" -> ")
        map[x[0]] = x[1]
    }
    //println(map)

    var d = mutableMapOf<String, Long>()
    for (i in 0 until start.length - 1) {
        val s = "${start[i]}${start[i + 1]}"
        d.putIfAbsent(s,0)
        d[s] = d[s]!! + 1
    }
    val times = 40
    repeat(times) {
        val dd = mutableMapOf<String, Long>()
        for ((pair, count) in d) {
            if (map[pair] != null) {
                val rhs = map[pair]
                val pair1 = "${pair[0]}$rhs"
                val pair2 = "$rhs${pair[1]}"
                dd.putIfAbsent(pair1,0)
                dd[pair1] = dd[pair1]!! +count
                dd.putIfAbsent(pair2,0)
                dd[pair2] = dd[pair2]!! +count
            }
        }
        //println("dd=$dd")
        d = dd
    }

    var max = -9999999999999999L
    var min = 99999999999999999L
    for (ch in 'A' .. 'Z') {
        var c = 0L
        for (k in d.keys) {
            if (k[0] == ch) {
                c+= d[k]!!
            }
        }
        if (c > 0) {
            if (c > max) max = c
            if (c < min) min = c
            //println("$ch $c")
        }
    }
    //println("min $min")
    //println("max $max")
    println("partB: ${max-min+1}")
}

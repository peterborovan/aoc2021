import java.io.File

fun main() {
    val input = File(
        //  "day14_.txt"
        "day14.txt"
    ).readLines()

    var firstLine = input.takeWhile {it.isNotEmpty()}[0]
    //println(start)
    val map = mutableMapOf<String,String>()
    input.dropWhile {it.isNotEmpty()}.drop(1).forEach{ l ->
        val x = l.split(" -> ")
        map[x[0]] = x[1]
    }
    //println(map)
    var start = firstLine
    repeat (10) {
      var res = ""
      for(i in 0 until start.length-1) {
          val x = "${start[i]}${start[i + 1]}"
          if (map[x] != null)
              res += start[i] + map[x]!!
          else
              res += "${start[i]}"
      }
      res += start[start.length-1]
      start = res
    }
    var maxa = Int.MIN_VALUE
    var mina = Int.MAX_VALUE
    for (ch in 'A' .. 'Z') {
        val c = start.count{it == ch}
        if (c > 0) {
            if (c > maxa) maxa = c
            if (c < mina) mina = c
        }
    }
    println("partA: ${maxa-mina}")
    //---------------------------
    var d = mutableMapOf<String, Long>()
    for (i in 0 until firstLine.length - 1) {
        val s = "${firstLine[i]}${firstLine[i + 1]}"
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

    var max = Long.MIN_VALUE
    var min = Long.MAX_VALUE
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
        }
    }
    //println("min $min")
    //println("max $max")
    println("partB: ${max-min+1}")
}

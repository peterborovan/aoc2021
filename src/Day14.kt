import java.io.File

fun main() {
    val input = File(
        //  "day14_.txt"
        "day14.txt"
    ).readLines()

    val firstLine = input.takeWhile {it.isNotEmpty()}[0]
    val map = mutableMapOf<String,String>()
    input.dropWhile {it.isNotEmpty()}.drop(1).forEach{ l ->
        val x = l.split(" -> ")
        map[x[0]] = x[1]
    }
    var start = firstLine
    repeat (10) {
      var res = ""
      for(i in 0 until start.length-1) {
          val x = "${start[i]}${start[i + 1]}"
          res += start[i] + if (map[x] != null) map[x]!! else ""
      }
      res += start[start.length-1]
      start = res
    }
    val x = start.groupBy { it  }.values.map { it.size }
    println("partA: ${ x.maxOrNull()!! - x.minOrNull()!!}")
    //---------------------------
    var d = mutableMapOf<String, Long>()
    for (i in 0 until firstLine.length - 1)
        d.compute("${firstLine[i]}${firstLine[i + 1]}", { _, v -> (v?:0)+1})
    repeat(40) {
        val dd = mutableMapOf<String, Long>()
        for ((pair, count) in d) {
            if (map[pair] != null) {
                val pair1 = "${pair[0]}${map[pair]}"
                val pair2 = "${map[pair]}${pair[1]}"
                dd.compute(pair1, { _, v -> (v?:0)+count})
                dd.compute(pair2, { _, v -> (v?:0)+count})
            }
        }
        d = dd
    }
    val xx = ('A'..'Z').map { ch -> d.map { (k, v) -> if (k[0] == ch) v else 0L }.sum() }.filter { it > 0 }
    println("partB: ${ 1+(xx.maxOrNull()!! - xx.minOrNull()!!)}")
}

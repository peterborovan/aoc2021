import java.io.File

fun main() {
    //val input = File("day04_.txt").readLines()
    val input = File("day04.txt").readLines()
    val numbers = input[0].split(",").map {it.toInt()}
    var boxes = mutableListOf<MutableList<MutableList<Int>>>()
    var box = mutableListOf<MutableList<Int>>()
    var winners = mutableListOf<Int>()
    var i = 1
    while (i < input.size) {
        val l = input[i]
        i++
        if (l.length < 2) {
            if (box.size > 1) boxes.add(box)
            box = mutableListOf()
            continue
        }
        box.add(listOf(0,3,6,9,12).map { l.substring(it, it+2).trim().toInt()}.toMutableList())
    }
    if (box.size > 1) boxes.add(box)

    for (n in numbers) {
        for(b in 0 until boxes.size)
            for (l in 0 until boxes[b].size)
                boxes[b][l] = boxes[b][l].map{ if (it == n) -n else it}.toMutableList()

        for(b in 0 until boxes.size) {
            if (b in winners) continue
            for (l in 0 until boxes[b].size)
                if (boxes[b][l].all { it <= 0})
                    winners.add(b)
        }
        for(b in 0 until boxes.size) {
            if (b in winners) continue
            for (r in 0 until boxes[b].size)
                  if ((0..4).map {boxes[b][it][r]}.all { it <= 0})
                      winners.add(b)
        }
        if (winners.size > 0) {
            val winner = winners.last()
            winners.add(winner)
            val unmarked  = boxes[winner].map { it.filter {it >= 0}.sum()}.sum()
            println("$unmarked $n")
            println("partA(first)/partB(last): ${unmarked*n}")
            if (winners.toSet().size == boxes.size)
                break
        }
    }
}
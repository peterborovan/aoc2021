import java.io.File

fun main() {
    val input = File(
       //  "day13_.txt"
       "day13.txt"
        ).readLines()
    var points = input.takeWhile {it.isNotEmpty()}.map { l ->
        val (x,y) = l.split(",").map {it.toInt()}
        Pair(x,y)
    }
    val folds = input.dropWhile {it.isNotEmpty()}.drop(1).map { l ->
        (if (l.contains("x")) 1 else -1) * l.substring(l.indexOf("=")+1).toInt()
    }
    for (f in folds) {
        points = points.map { (x,y) -> if (f > 0) Pair(if (x >f) f-(x-f) else x,y) else Pair(x,if (y >-f) -f-(y+f) else y) }
        println("partA: ${points.toSet().count()}")
        // break
    }
    println("partB:")
    for (i in 0..10) {
        for (j in 0..80)
            print(if (Pair(j,i) in points) "#" else ".")
        println()
    }
}

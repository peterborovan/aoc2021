import java.io.File

fun main() {
    val input = File(
        //"day11__.txt"
       // "day11_.txt"
       "day11.txt"
    ).readLines().map { it.toCharArray().map { it.digitToInt()}}
    var partA = 0
    val len = input.size
    val b = Array(len) { Array(len) { 0 } }
    for (r in 0 until len)  for (c in 0 until len) b[r][c] = input[r][c]
    // partA repeat(100) {
    repeat(1000) {
        for (r in 0 until len) for (c in 0 until len) b[r][c]++
        val flashed = mutableListOf<Pair<Int,Int>>()
        do {
            var any_flash = false
            for (r in 0 until len)
                for (c in 0 until len)
                    if (b[r][c] > 9) {
                        any_flash = true
                        for ((dr, dc) in listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1))
                            try {
                            if (Pair(r+dr, c+dc) !in flashed)
                                b[r + dr][c + dc]++
                            } catch (e: Exception) { }
                        flashed.add(Pair(r,c))
                        b[r][c] = 0
                    }
        } while (any_flash)
        partA += flashed.size
        if (flashed.size == len*len) {
            println("partB: ${it+1}")
            return
        }
    }
    println("partA: $partA")
}

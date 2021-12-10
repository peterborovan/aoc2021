import java.io.File


fun main() {
    val input: List<String> = File(
       //"day10_.txt"
       "day10.txt"
    ).readLines()
    val map = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )
    val map1 = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )
    val map2 = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )
    var partA = 0
    val partB = mutableListOf<Long>()
    for (l in input) {
        val stack = mutableListOf<Char>()
        var corrupted = false
        for (ch in l)
            if (ch in map.keys)
                stack.add(map[ch]!!)
            else
                if (ch != stack.last()) {
                    partA += map1[ch]!!
                    corrupted = true
                    break
                } else
                    stack.removeLast()
        if (!corrupted) {
            var incomplete = 0L
            while(stack.size > 0) {
                incomplete = 5*incomplete + map2[stack.last()]!!
                stack.removeLast()
            }
            partB.add(incomplete)
        }
    }
    println("partA: $partA")
    println("partB: ${partB.toList().sorted()[partB.size/2]}")
}

import java.io.File

val segments = "abcdefg"

fun main() {
    val input = File(
       // "day08_.txt"
       "day08.txt"
    ).readLines()
    val partA = input.sumOf{ l->
        val codes = l.split(" | ")[1].split(" ").map { it.trim()}
        listOf(2,4,3,7).sumOf{ len -> codes.count { it.length == len} }
    }
    println("partA: $partA")
    //                    0         1       2       3       4       5       6           7       8       9
    val cifry = listOf("abcefg", "cf", "acdeg", "acdfg", "bcdf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg")
    val partB = input.sumOf { l ->
        val (lhs, rhs) = l.split(" | ")
        perms(segments, "")
            .filter { lhs.translate(it).toSet() == cifry.toSet() }
            .sumOf {
                rhs.translate(it).map { cifry.indexOf(it) }.map { it.toString() }.joinToString(separator = "").toInt()
            }
    }
    println("partB : $partB")
}

private fun String.translate(perm : String): List<String> {
    return this.split(" ").map {
        it.map { segments[perm.indexOf(it)] }.sorted().joinToString(separator = "")
    }
}

fun perms(str: String, ans: String) : List<String> {
    if (str.length == 0) {
        return listOf(ans)
    }
    val res = mutableListOf<String>()
    for (i in 0 until str.length) {
        val ch = str[i]
        val ros = str.substring(0, i) +
                str.substring(i + 1)
        res.addAll(perms(ros, ans + ch))
    }
    return res.toList()
}

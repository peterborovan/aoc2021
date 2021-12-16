import java.io.File

fun String.bitsToInt() : Long {
    var i = 0L
    this.forEach {
        i = 2*i+it.digitToInt()
    }
    return i
}

fun toBits(inp : String) :String {
    val aa = inp.map {
        when (it) {
            '0' -> "0000"
            '1' -> "0001"
            '2' -> "0010"
            '3' -> "0011"
            '4' -> "0100"
            '5' -> "0101"
            '6' -> "0110"
            '7' -> "0111"
            '8' -> "1000"
            '9' -> "1001"
            'A' -> "1010"
            'B' -> "1011"
            'C' -> "1100"
            'D' -> "1101"
            'E' -> "1110"
            'F' -> "1111"
            else -> "error"
        }
    }
    var s = ""
    aa.forEach { s += it }
    return s
}

var partA = 0L

fun main() {
    val inp = File(
        //   "day16_.txt"
        "day16.txt"
    ).readLines()
    val (res, _) = parser(toBits(inp[0]))
    println("partA = $partA")
    println("partB = $res")
}


fun parser(s : String) : Pair<Long,String> {
    var pc = 0
    val version = s.substring(pc,pc+3)

    partA += version.bitsToInt()
    pc += 3
    val type = s.substring(pc, pc+3)
    pc += 3
    if (type == "100") { // 4
        var literalValue = ""
        while (true) {
            val s5 = s.substring(pc, pc + 5)
            val a = s5.substring(1)
            literalValue += a
            pc += 5
            if (s5[0] == '0')
                break
        }
        return Pair(literalValue.bitsToInt(), s.substring(pc))
    } else {  // operator
        val i = s.substring(pc, pc + 1)
        pc += 1
        val values = listOf<Long>().toMutableList()
        var ss = s.substring(pc)
        if (i == "0") {
            var totalLengthBits = ss.substring(0, 15).bitsToInt()
            pc += 15
            ss = s.substring(pc)
            while (totalLengthBits > 0) {
                val (uuvalue, uu) = parser(ss)
                values.add(uuvalue)
                totalLengthBits -= ss.length - uu.length
                ss = uu
            }
        } else {
            val numberOfSubpackets = ss.substring(0, 11).bitsToInt().toInt()
            pc += 11
            ss = s.substring(pc)
            repeat(numberOfSubpackets) {
                val (uuvalue, uu) = parser(ss)
                values.add(uuvalue)
                ss = uu
            }
        }
        val eval =
            when (type.bitsToInt().toInt()) {
                0 -> values.sum()
                1 -> values.product()
                2 -> values.minOrNull()!!
                3 -> values.maxOrNull()!!
                5 -> if (values[0] > values[1]) 1 else 0
                6 -> if (values[0] < values[1]) 1L else 0L
                7 -> if (values[0] == values[1]) 1L else 0L
                else -> Long.MAX_VALUE
            }
        return Pair(eval,ss)
    }
}

fun List<Long>.product():Long  {
    var res = 1L
    this.forEach { res *= it}
    return res
}
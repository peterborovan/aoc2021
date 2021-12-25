import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    val inp = File(
       "day24.txt"
    //    "day24_.txt"
    ).readLines().filter{it.isNotEmpty()}.map { it.split(" ")}
//        for (d in '1'..'9') {
//        var vstup = ""+d
//        var pc = 0
//        val vars = "xyzw"
//        val vals = mutableMapOf<String,Long>()
//        vals["x"] = 0L
//        vals["y"] = 0L
//        vals["z"] = 0L
//        vals["w"] = 0L
//        for (ins in inp) {
//            if (ins.isNullOrEmpty()) continue
//            //println("$ins $vals")
//            val arg1 = ins[1]
//            when (ins[0]) {
//                "inp" -> {
//                    vals[arg1] = vstup[0].digitToInt().toLong()
//                    vstup = vstup.substring(1)
//                }
//                else -> {
//                    val arg2 = ins[2]
//                    val var2 = if (arg2 in vars) vals[arg2]!! else arg2.toInt().toLong()
//                    when(ins[0]) {
//                        "add" -> vals[arg1] = vals[arg1]!! + var2
//                        "mul" -> vals[arg1] = vals[arg1]!! * var2
//                        "div" ->
//                            if (var2 == 0L) println("DIV BY ZERO $ins")
//                            else vals[arg1] = vals[arg1]!! / var2
//                        "mod" -> if (vals[arg1]!! < 0L || var2 <= 0L) println("MOD BY ZERO $ins")
//                                 else vals[arg1] = vals[arg1]!! % var2
//                        "eql" -> vals[arg1] = if (vals[arg1]!! == var2) 1 else 0
//                    }
//                }
//
//            }
//        }
    label@
    for(d1 in 9 downTo 1)
        for(d2 in 9 downTo 1)
            for(d3 in 9 downTo 1)
                for(d4 in 9 downTo 1)
                    for(d5 in 9 downTo 1)
                        for(d6 in 9 downTo 1)
                            for(d7 in 9 downTo 1) {
                                input = "$d1$d2$d3$d4$d5$d6$d7"
                                z = 0
                                val r1 = one(1, 13, 10)  //1
                                if (r1 == null) continue
                                val r2 = one(1, 11, 16)  //2
                                if (r2 == null) continue
                                val r3 = one(1, 11, 0)  //3
                                if (r3 == null) continue
                                val r4 = one(1, 10, 13)  //4
                                if (r4 == null) continue
                                val r5 = one(26, -14, 7)  //5
                                if (r5 == null) continue
                                val r6 = one(26,-4, 11)  //6
                                if (r6 == null) continue
                                val r7 = one(1, 11, 11)  //7
                                if (r7 == null) continue
                                val r8 = one(26, -3, 10)  //8
                                if (r8 == null) continue
                                val r9 = one(1, 12, 16)  //9
                                if (r9 == null) continue
                                val r10 = one(26, -12, 8)  //10
                                if (r10 == null) continue
                                val r11 = one(1, 13, 15)  //11
                                if (r11 == null) continue
                                val r12 = one(26, -12, 2)  //12
                                if (r12 == null) continue
                                val r13 = one(26, -15, 5)  //13
                                if (r13 == null) continue
                                val r14 = one(26, -12, 10)  //14
                                if (r14 == null) continue
                                println("partA: $r1$r2$r3$r4$r5$r6$r7$r8$r9$r10$r11$r12$r13$r14")
                                break@label
                            }

    for(d1 in 1..9)
        for(d2 in 1..9)
            for(d3 in 1..9)
                for(d4 in 1..9)
                    for(d5 in 1..9)
                        for(d6 in 1..9)
                            for(d7 in 1..9) {
                                input = "$d1$d2$d3$d4$d5$d6$d7"
                                z = 0
                                val r1 = one(1, 13, 10)  //1
                                if (r1 == null) continue
                                val r2 = one(1, 11, 16)  //2
                                if (r2 == null) continue
                                val r3 = one(1, 11, 0)  //3
                                if (r3 == null) continue
                                val r4 = one(1, 10, 13)  //4
                                if (r4 == null) continue
                                val r5 = one(26, -14, 7)  //5
                                if (r5 == null) continue
                                val r6 = one(26,-4, 11)  //6
                                if (r6 == null) continue
                                val r7 = one(1, 11, 11)  //7
                                if (r7 == null) continue
                                val r8 = one(26, -3, 10)  //8
                                if (r8 == null) continue
                                val r9 = one(1, 12, 16)  //9
                                if (r9 == null) continue
                                val r10 = one(26, -12, 8)  //10
                                if (r10 == null) continue
                                val r11 = one(1, 13, 15)  //11
                                if (r11 == null) continue
                                val r12 = one(26, -12, 2)  //12
                                if (r12 == null) continue
                                val r13 = one(26, -15, 5)  //13
                                if (r13 == null) continue
                                val r14 = one(26, -12, 10)  //14
                                if (r14 == null) continue
                                println("partB: $r1$r2$r3$r4$r5$r6$r7$r8$r9$r10$r11$r12$r13$r14")
                                return
                            }

}
var z = 0L
var input = ""

fun one(c0: Long, c1 : Long, c2 : Long) : Long? {
    if (c0 == 26L) {
        val f = z % 26 + c1
        if (f !in 1..9) return null
        z /= 26
        return f
    } else {
        z *= (25 + 1)
        val r = input[0].digitToInt()
        z += r
        input = input.substring(1)
        z += c2
        return r.toLong()
    }
}
import java.io.File

var countB = 0
var countA = 0

fun pathA(x : String, inp :List<List<String>>, p : List<String>) {
    if (x == "end")
        countA++
    else
        for ((a,b) in inp)
            if (a == x && (b[0] in 'a'..'z' && b !in p || b[0] in 'A'..'Z'))
               pathA(b, inp, p.plus(x))
            else if (x == b && (a[0] in 'a'..'z' && a !in p || a[0] in 'A'..'Z'))
               pathA(a, inp, p.plus(x))
}

fun path(x : String, inp :List<List<String>>, p : List<String>, smallTwice : Boolean) {
    if (x == "end")
        countB++
    else {
        for ((a,b) in inp) {
            if (a == x) {
                if (b[0] in 'a'..'z') {
                    if (b in listOf("start", "end")) {
                        if (b !in p)
                            path(b, inp, p.plus(x), smallTwice)
                    } else {
                        if (b !in p)
                            path(b, inp, p.plus(x), smallTwice)
                        else if (p.filter { it == b }.size == 1 && !smallTwice)
                            path(b, inp, p.plus(x), true)
                    }
                } else
                    path(b, inp, p.plus(x), smallTwice)
            } else if (x == b)
                if (a[0] in 'a'..'z') {
                        if (a in listOf("start", "end")) {
                            if (a !in p)
                                path(a, inp, p.plus(x), smallTwice)
                        } else {
                            if (a !in p)
                                path(a, inp, p.plus(x), smallTwice)
                            else if (p.filter { it == a }.size == 1 && !smallTwice)
                                path(a, inp, p.plus(x), true)
                        }
                } else
                    path(a, inp, p.plus(x), smallTwice)
        }
    }

}
fun main() {
    val input: List<List<String>> = File(
     //  "day12_.txt"
       "day12.txt"
    ).readLines().map{ it.split("-")}
    pathA("start", input, listOf())
    println("partA: $countA")
    path("start", input, listOf(),false)
    println("partB: $countB")
}

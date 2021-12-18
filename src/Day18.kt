import java.io.File

sealed class P
data class R(var n : Long) : P()
data class N(val left : P, val right : P) : P()

val foo = 999999L
fun parse(s : String) : Pair<P,String> {
    if (s[0] == '[') {
        val (left, s1) = parse(s.substring(1))
        val (right, s2) = parse(s1.substring(1))
        return Pair(N(left, right), s2.substring(1))
    } else
        return Pair(R(s[0].digitToInt().toLong()),s.substring(1))
}

fun P.preorder() : List<P>  {
    when (this) {
        is R -> return listOf(this)
        is N -> return left.preorder().plus(right.preorder())
    }
}

fun P.update(values: Pair<Long, Long>) {
    val l = preorder()
    val fi =  l.indexOfFirst { (it as R).n == foo }
    if (fi > 0) {
        val before = l[fi-1] as R
        before.n += values.first
    }
    if (fi < l.size-1) {
        val after = l[fi+1] as R
        after.n += values.second
    }
    val actual = l[fi] as R
    actual.n = 0L
}

fun P.split() : Pair<P, Boolean> {
    when (this) {
        is R -> if (n >= 10) return Pair(N(R(n/2), R(n-n/2)), true)
                else return Pair(this, false)
        is N ->  {
            val (nleft, leftSplited) = left.split()
            if (leftSplited) return Pair(N(nleft, right), leftSplited )
            else {
                val (nright, rightSplited) = right.split()
                return Pair(N(nleft, nright), rightSplited )
            }
        }
    }
}

fun P.explode(level : Int) : Pair<P,Pair<Long,Long>?> {
    when (this) {
        is R -> return Pair(R(n), null)
        is N -> if (level == 4) {
            var lvalue = 0L
            if (left is R) lvalue = (left as R).n else println("left value is not regular ${left}")
            var rvalue = 0L
            if (right is R) rvalue = (right as R).n else println("right value is not regular ${right}")
            return Pair(R(foo), Pair(lvalue, rvalue))
        }
        else {
            val (nleft, exl) = left.explode(level + 1)
            if (exl != null)
                return Pair(N(nleft, right), exl)
            else {
                val (nright, exr) = right.explode(level + 1)
                return Pair(N(nleft, nright), exr)
            }
        }
    }
}

fun  P.eval() : Long {
    when (this) {
        is R -> return n
        is N -> return 3 * left.eval() + 2 * right.eval()
    }
}

fun main() {
    val inp = File(
        "day18.txt"
    ).readLines()
    val (x,_) = parse(inp[0])
    var p = x
    for (i in 1 until inp.size) {
        val (y,_) = parse(inp[i])
        p = N(p,y).nf()
    }
    println("partA: ${p.eval()}")

    val m = listOf<Long>().toMutableList()
    for (i in 0 until inp.size) {
        for (j in 0 until inp.size) {
            if (i == j) continue
            val (xx, _) = parse(inp[i])
            val (yy, _) = parse(inp[j])
            p = N(xx, yy).nf()
            m.add(p.eval())
        }
    }
    println("partB: ${m.toList().maxOrNull()}")
}

fun P.nf() : P {
    var p = this
    while(true) {
        val (pexploded, explodePair) = p.explode(0)
        if (explodePair == null) {
            val (splitted, wasSplit) = pexploded.split()
            if (!wasSplit) return p
            p = splitted
        } else {
            pexploded.update(explodePair)
            p = pexploded
        }
    }
}

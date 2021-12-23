import java.lang.Integer.max
import java.lang.Integer.min

val A = 'A'
val B = 'B'
val C = 'C'
val D = 'D'
val E = '.'

val price = mapOf(A to 1, B to 10, C to 100, D to 1000)

// part A
val f0 = 0
val f1 = 1
val f2 = 2
val f3 = 3
val f4 = 4

val h0 = 5
val h1 = 6
val h2 = 7
val h3 = 8

val l0 = 9
val l1 = 10
val l2 = 11
val l3 = 12

val nil = emptyList<Int>()
data class Swap( val from: Int, val to:Int, var free : List<Int>, val steps : Int)
val swaps2 = listOf(
    Swap(h0,f0, nil, 2),
    Swap(h0,f1, nil, 2),
    Swap(h1,f1, nil, 2),
    Swap(h1,f2, nil, 2),
    Swap(h2,f2, nil, 2),
    Swap(h2,f3, nil, 2),
    Swap(h3,f3, nil, 2),
    Swap(h3,f4, nil, 2),
    //---
    Swap(l0,f0, listOf(h0), 3),
    Swap(l0,f1, listOf(h0), 3),
    Swap(l1,f1, listOf(h1), 3),
    Swap(l1,f2, listOf(h1), 3),
    Swap(l2,f2, listOf(h2), 3),
    Swap(l2,f3, listOf(h2), 3),
    Swap(l3,f3, listOf(h3), 3),
    Swap(l3,f4, listOf(h3), 3),
    //---
    Swap(f0,f1, nil, 2),
    Swap(f1,f2, nil, 2),
    Swap(f2,f3, nil, 2),
    Swap(f3,f4, nil, 2),
)


data class Node(val x : Array<Char?>) {
    override fun toString() :String {
        return  "#############\n" +
                "#.${x[0]}.${x[1]}.${x[2]}.${x[3]}.${x[4]}.#\n" +
                "###${x[5]}#${x[6]}#${x[7]}#${x[8]}###\n" +
                "  #${x[9]}#${x[10]}#${x[11]}#${x[12]}#\n" +
                "  #########\n"

    }
}
data class Step(val n:Node, val price : Int) {
    override fun toString() :String {
        return "Price: $price\n" + n.toString()
    }

}
fun Node.next(): List<Step>  {
    val res = emptyList<Step>().toMutableList()
    for ((from, to, free, steps) in swaps2) {
        if (x[from] != E && x[to] == E && free.all{ x[it] == E} /*&& home(from) != x[from]*/) {
            val xx = x.copyOf()
            xx[to] = xx[from]
            xx[from] = E
            res.add(Step(Node(xx), steps*price[x[from]]!!))
        }
        if (x[to] != E && x[from] == E && free.all{ x[it] == E} /*&& home(to) != x[to]*/) {
            val xx = x.copyOf()
            xx[from] = xx[to]
            xx[to] = E
            res.add(Step(Node(xx), steps*price[x[to]]!!))
        }

    }
    return res
}

fun bfs(init : Node, final : Node) {
    val visi = mutableMapOf<String,Int>()
    val queue = mapOf(0 to listOf(
        init
    )).toMutableMap()
    visi[init.toString()] = 0
    while (queue.isNotEmpty()) {
        val keys = queue.keys
        val minKey = keys.minOrNull()!!
        val minValues = queue[minKey]!!
        for (node in minValues) {
            if (node.toString() == final.toString()) {
                println("partA: $minKey")
                return
            }
            for((nnode, delta) in node.next()) {
                val newprice = minKey + delta
                val nns = nnode.toString()
                if ((visi[nns]?:Int.MAX_VALUE) > newprice) {
                    visi[nns] = newprice
                   queue.computeIfAbsent(newprice, { _ -> listOf() })
                   queue.computeIfPresent(newprice, { _, v -> v.plus(nnode) })
               }
           }
            queue.remove(minKey)
        }
    }
}
//- partB

fun destRoom(ch:Char) : Int =
    when(ch) {
        A -> 0
        B -> 1
        C -> 2
        D -> 3
        else -> 999
    }

data class NodeB(val top : Array<Char>, val stacks : List<List<Char>>) {
    override fun toString() :String {
        var ss = "#############\n" + top.joinToString(prefix = "#", postfix = "#" ,separator = "") + "\n"
        for( i in 0 until 4) {
                var s = "  #"
                for (j in 0 until 4) {
                    val ii = i-(4-stacks[j].size)
                    s += "${if (ii < 0) E else stacks[j][ii]}#"
                }
                ss += s+"\n"
            }
        return ss + "  #########\n"
    }
}
data class StepB(val n:NodeB, val price : Int)

fun NodeB.next() : List<StepB> {
    val res = emptyList<StepB>().toMutableList()
    for(dest in listOf(0,1,3,5,7,9,10)) {   // pop out
        if (top[dest] != E) continue
        for(s in 0 until 4) {
            if (stacks[s].isNotEmpty()) {
              val topIndex = 2*s+2
              val topLetter = stacks[s].first()
              val freeWay = (min(topIndex,dest)..max(topIndex,dest)).all {top[it] == E}
                if (freeWay) {
                    val stepsUp = 5 - (stacks[s].size)
                    val stepsHallway = max(topIndex, dest) - min(topIndex, dest)
                    val price = (stepsUp + stepsHallway) * price[topLetter]!!
                    val ntop = top.copyOf()
                    ntop[dest] = topLetter
                    res.add(StepB(
                        NodeB(ntop, (0 until 4).map { it -> if (it == s) stacks[s].drop(1) else stacks[it]}),
                        price
                    ))
                }
            }
        }
    }
    for(dest in listOf(0,1,3,5,7,9,10)) {   // push in
        if (top[dest] == E) continue
        val moveLetter = top[dest]
        val s = destRoom(moveLetter)
        if (stacks[s].size < 4 && stacks[s].all { it == moveLetter}) {
            val topIndex = 2*s+2
            val freeWay = (min(topIndex,dest)..max(topIndex,dest)).all { it == dest || top[it] == E}
            if (freeWay) {
                val stepsDown = 4 - (stacks[s].size)
                val stepsHallway = max(topIndex, dest) - min(topIndex, dest)
                val price = (stepsDown + stepsHallway) * price[moveLetter]!!
                val ntop = top.copyOf()
                ntop[dest] = E
                res.add(StepB(
                    NodeB(ntop, (0 until 4).map { it -> if (it == s) pushTop(moveLetter,stacks[s]) else stacks[it]}),
                    price
                    ))
            }
        }
    }
    return res
}
fun pushTop(ch : Char, l : List<Char>) : List<Char> {
    val ml = l.toMutableList()
    ml.add(0, ch)
    return ml.toList()
}

fun bfsB(init : NodeB, final: NodeB) {
    val visiB = mutableMapOf<String,Int>()
    val previousB = mutableMapOf<NodeB, NodeB?>()
    val queue = mapOf(0 to listOf(
        init
    )).toMutableMap()
    visiB[init.toString()] = 0
    previousB[init] = null
    while (queue.isNotEmpty()) {
        val keys = queue.keys
        val minKey = keys.minOrNull()!!
        //println("minKey = $minKey ${visiB.size}")
        val minValues = queue[minKey]!!
        for (node in minValues) {
            if (node.toString() == final.toString()) {
                println("partB: $minKey")
                //var c = previousB[node]; while (c != null) { println(c); c = previousB[c] }
                return
            }
            for((nnode, delta) in node.next()) {
                val newprice = minKey + delta
                val nns = nnode.toString()
                if ((visiB[nns]?:Int.MAX_VALUE) > newprice) {
                    visiB[nns] = newprice
                    previousB[nnode] = node
                   queue.computeIfAbsent(newprice, { _ -> listOf() })
                   queue.computeIfPresent(newprice, { _, v -> v.plus(nnode) })
               }
           }
            queue.remove(minKey)
        }
    }
}
fun main() {
    part_A()
    part_B()
}

fun part_A() {
    val init = Node( arrayOf( E, E, E, E, E ,  D,C,A,B,  B,C,D,A ))
    val final = Node(arrayOf(E, E, E, E, E,    A,B,C,D , A,B,C,D))
    bfs(init, final)
}

fun part_B() {
    val initText = NodeB(   /// zadanie 44169
        Array(11){E},
        listOf(
            listOf(B,D,D,A),
            listOf(C,C,B,D),
            listOf(B,B,A,C),
            listOf(D,A,C,A)
        )
    )

    val init = NodeB(
        Array(11){E},
        listOf(
            listOf(D,D,D,B),
            listOf(C,C,B,C),
            listOf(A,B,A,D),
            listOf(B,A,C,A)
        )
    )

    val final = NodeB(
        Array(11){E},
        listOf(
            listOf(A,A,A,A),
            listOf(B,B,B,B),
            listOf(C,C,C,C),
            listOf(D,D,D,D)
        )
    )
    bfsB(init, final)
}

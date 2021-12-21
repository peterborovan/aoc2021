import java.lang.Integer.min

data class State(val p1:Int, val sc1 : Int, val p2 : Int, val sc2 : Int, val onTurn : Boolean)
fun reduce(p : Int) : Int = (p-1) % 10 + 1
fun Pair<Long,Long>.plus(times : Int, p : Pair<Long,Long>) = Pair(first + times*p.first, second+times*p.second)

/*
Player 1 starting position: 10
Player 2 starting position: 4
 */

//var player1 = 4
//var player2 = 8

var player1 = 10
var player2 = 4

fun play(s : State, results : MutableMap<State, Pair<Long, Long>>) : Pair <Long, Long> {
    if (s !in results) {
        var wins = Pair(0L,0L)
        val (p1,sc1,p2,sc2, onTurn) = s
        for ((d123, times) in mapOf(3 to 1, 4 to 3, 5 to 6,  6 to 7, 7 to 6, 8 to 3, 9 to 1))
            if (onTurn) {
                reduce(p1+d123).apply {
                    val nsc1 = sc1 + this
                    wins = wins.plus(times, if (nsc1 >= 21) Pair(1L,0L) else play(State(this, nsc1, p2, sc2, !onTurn), results))
                }
            } else {  // !onTurn
                reduce(p2+d123).apply {
                    val nsc2 = sc2 + this
                    wins = wins.plus(times, if (nsc2 >= 21) Pair(0L,1L) else play(State(p1, sc1, this, nsc2, !onTurn), results))
                }
            }
        results[s] = wins
    }
    return results[s]!!
}

fun main() {
    partA()
    val (sc1, sc2) = play(State(player1, 0, player2, 0, true), mutableMapOf())
    println("partB: ${Math.max(sc1,sc2)}")
}

private fun partA() {
    var p1 = player1
    var p2 = player2
    var sc1 = 0
    var sc2 = 0
    var onTurn = true
    var rolled = 0
    while (sc1 < 1000 && sc2 < 1000) {
        if (onTurn) {
            p1 = reduce(p1 + 3*rolled+6)
            sc1 += p1
        } else {
            p2 = reduce(p2 + 3*rolled+6)
            sc2 += p2
        }
        rolled += 3
        onTurn = !onTurn
    }
    println("partA: ${rolled * min(sc1, sc2)}")
}

import java.io.File
import java.math.BigInteger

fun main() {
    val input = File(
        //"day06_.txt"
       "day06.txt"
        ).readLines().map{it.split(",")}
    var a = input[0].map{it.toInt()}.toIntArray().toMutableList()
    println(a)
    repeat (80) {
        val n = mutableListOf<Int>()
        val m = mutableListOf<Int>()
        for (i in 0 until a.size) {
            val x = a[i]
            val xx = if (x > 0) x-1 else 6
            n.add(xx)
            if (x == 0) m.add(8)
        }
        a = n
        a.addAll(m)
    }
    println("partA:  ${a.size}")

    a = input[0].map{it.toInt()}.toIntArray().toMutableList()
    var d = Array(10){ 0L}
    for (e in a) d[e]++
    repeat (256) {
        val dd = Array(10){ 0L}
        for (i in 1 until d.size) dd[i-1] = d[i]
        dd[6] += d[0]
        dd[8] += d[0]
        d = dd
        //println("after ${it+1} days  ${d.sum()}" )
    }
    println("partB: ${d.sum()}")
}

/*

import Data.List

input :: [Integer]
input = [3, 5, 1, 5, 3, 2, 1, 3, 4, 2, 5, 1, 3, 3, 2, 5, 1, 3, 1, 5, 5, 1, 1, 1, 2, 4, 1, 4, 5, 2, 1, 2, 4, 3, 1, 2, 3, 4, 3, 4, 4, 5, 1, 1, 1, 1, 5, 5, 3, 4, 4, 4, 5, 3, 4, 1, 4, 3, 3, 2, 1, 1, 3, 3, 3, 2, 1, 3, 5, 2, 3, 4, 2, 5, 4, 5, 4, 4, 2, 2, 3, 3, 3, 3, 5, 4, 2, 3, 1, 2, 1, 1, 2, 2, 5, 1, 1, 4, 1, 5, 3, 2, 1, 4, 1, 5, 1, 4, 5, 2, 1, 1, 1, 4, 5, 4, 2, 4, 5, 4, 2, 4, 4, 1, 1, 2, 2, 1, 1, 2, 3, 3, 2, 5, 2, 1, 1, 2, 1, 1, 1, 3, 2, 3, 1, 5, 4, 5, 3, 3, 2, 1, 1, 1, 3, 5, 1, 1, 4, 4, 5, 4, 3, 3, 3, 3, 2, 4, 5, 2, 1, 1, 1, 4, 2, 4, 2, 2, 5, 5, 5, 4, 1, 1, 5, 1, 5, 2, 1, 3, 3, 2, 5, 2, 1, 2, 4, 3, 3, 1, 5, 4, 1, 1, 1, 4, 2, 5, 5, 4, 4, 3, 4, 3, 1, 5, 5, 2, 5, 4, 2, 3, 4, 1, 1, 4, 4, 3, 4, 1, 3, 4, 1, 1, 4, 3, 2, 2, 5, 3, 1, 4, 4, 4, 1, 3, 4, 3, 1, 5, 3, 3, 5, 5, 4, 4, 1, 2, 4, 2, 2, 3, 1, 1, 4, 5, 3, 1, 1, 1, 1, 3, 5, 4, 1, 1, 2, 1, 1, 2, 1, 2, 3, 1, 1, 3, 2, 2, 5, 5, 1, 5, 5, 1, 4, 4, 3, 5, 4, 4]

oneStep :: [Integer] -> [Integer]
oneStep xs = zipWith (+) [xs !! i |  i <- [1..9]]
                         [0,0,0,0,0,0,xs!!0,0,xs!!0,0]
             ++[0]

main :: IO ()
main = do
  let m = [ toInteger $ length (filter(==i) input)| i<-[0..9]]
  print $ sum $ (iterate oneStep m)!!80
  print $ sum $ (iterate oneStep m)!!256

 */

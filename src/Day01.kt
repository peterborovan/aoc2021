import java.io.File

fun inc(input: List<Int>) = (input zip (input.drop(1))).count { it.first < it.second }
fun join2(input1: List<Int>, input2: List<Int>) = (input1 zip input2).map { it.first + it.second }
fun join3(input1: List<Int>, input2: List<Int>, input3: List<Int>) = join2(join2(input1, input2), input3)


fun inc1(input : List<Int>) : Int {
    var count = 0
    for(i in 0 until input.size-1)
        if (input[i+1] > input[i]) count++
    return count
}

fun main() {
    //val input = File("day01_.txt").readLines().map{it.toInt()}
    val input = File("day01.txt").readLines().map{it.toInt()}
    println("partA:${inc(input)}")

//    val n = mutableListOf<Int>()
//    for(i in 0 until input.size-2) {
//        n.add(input[i] +input[i+1]  + input[i+2])
//    }
//    println("partB:${inc(n)}")

    println("partB:${inc(join3(input, input.drop(1), input.drop(2)))}")
}

/*
import Data.List

join2 i1 i2 = zipWith (+) i1 i2
join3 i1 i2 i3 = join2 (join2 i1 i2) i3

incr :: [Int] -> Int
incr input = length $ filter (id) $ zipWith (<) input (tail input)

main :: IO ()
main = do
  s <- readFile "01.inp"
  let input = map read (lines s)
  print (incr input)
  print (incr $ join3 input (tail input) (drop 2 input))

*/
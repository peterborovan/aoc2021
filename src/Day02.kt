import java.io.File

fun partA_02(input : List<String>) : Long {
    var x = 0L
    var y = 0L
    for (c in input) {
        val parts = c.split(" ")
        val steps = parts[1].toInt()
        when (parts[0]) {
            "forward" -> x+=steps
            "down" -> y += steps
            "up" -> y -=steps
        }
    }
    return x*y
}

fun partB_02(input : List<String>) : Long {
    var x = 0L
    var y = 0L
    var aim = 0L
    for (c in input) {
        val parts = c.split(" ")
        val steps = parts[1].toInt()
        when (parts[0]) {
            "forward" -> {x+=steps; y += aim * steps}
            "down" -> aim += steps
            "up" -> aim -=steps
        }
    }
    return x * y
}

fun main() {
    //val input = File("day02_.txt").readLines()
    val input = File("day02.txt").readLines()
    println("partA: ${partA_02(input)}")
    println("partB: ${partB_02(input)}")
}

/*
import Data.List

partA :: [[String]] -> Integer
partA commands = uncurry (*) $ foldl (\(x,y) -> \[cmd,steps] ->
                                                        let kroky = read steps in
                                                        case cmd of
                                                           "forward" -> (x+kroky, y)
                                                           "down" -> (x, y+kroky)
                                                           "up" ->  (x, y-kroky)) (0,0) commands

partB :: [[String]] -> Integer
partB commands = let (x,y,_) = foldl (\(x,y,aim) -> \[cmd,steps] ->
                                                        let kroky = read steps in
                                                        case cmd of
                                                           "forward" -> (x+kroky, y+aim*kroky, aim)
                                                           "down" -> (x, y, aim+kroky)
                                                           "up" ->  (x, y, aim-kroky)) (0,0,0) commands
                 in x*y


main :: IO ()
main = do
  s <- readFile "02.inp"
  let input = map words (lines s)
  print $ partA input
  print $ partB input
 */
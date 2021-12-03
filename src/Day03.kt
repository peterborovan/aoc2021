import java.io.File

val len = 12

fun filterMost(input : List<String>, i : Int) : Pair<Char, List<String>> =
    if (input.size == 1)
        Pair(input[0][i], input)
    else {
        val r = if (input.count { it[i] == '1' } >= input.count { it[i] == '0' }) '1' else '0'
        Pair(r, input.filter { it[i] == r })
    }

fun filterLess(input : List<String>, i : Int) : Pair<Char, List<String>> =
    if (input.size == 1)
        Pair(input[0][i], input)
    else {
        val r = if (input.count { it[i] == '1'} >= input.count { it[i] == '0'}) '0' else '1'
        Pair(r,input.filter { it[i] == r })
    }


fun main() {
    //val input = File("day03_.txt").readLines()
    val input = File("day03.txt").readLines()
    var s = 0L
    var z = 0L
    for (i in 0 until len) {
        s = 2*s + (if (input.count { it[i] == '1' } >= input.count { it[i] == '0' }) 1 else 0)
        z = 2*z + (if (input.count { it[i] == '1' } >= input.count { it[i] == '0' }) 0 else 1)
    }
    println("partA: ${s*z}")

    var oxy = input
    var oxi = 0L
    for(i in 0 until len) {
        val p = filterMost(oxy, i)
        val r = p.first
        oxy = p.second
        oxi = 2*oxi+(r-'0')
    }
    var co2 = input
    var co = 0L
    for(i in 0 until len) {
        val p = filterLess(co2, i)
        val r = p.first
        co2 = p.second
        co = 2*co+(r-'0')
    }
    println("partB: ${oxi*co}")
}

/*
import Data.List
import Data.Char
ones :: Int -> [String] -> Int
ones bit lines = length [ 1 | l <- lines, l!!bit == '1']

zeros :: Int -> [String] -> Int
zeros bit lines = length lines - ones bit lines

bits2Int :: [Int] -> Int
bits2Int = foldl (\acc x -> 2*acc+x) 0

partA :: [String] -> Int
partA lines = (bits2Int bits) * (bits2Int $ map (\x -> 1-x) bits)
              where len = length $ head lines
                    bits = [ if (ones bit lines) > (zeros bit lines) then 1 else 0 | bit <- [0..len-1] ]

filtruj :: Int -> [String] -> (Int, Int, [String], [String])
filtruj bit lines = if length lines == 1
                   then (ord ((head lines)!!bit)-48, ord ((head lines)!!bit)-48, lines, lines)
                   else (r, 1-r, fr, fnr)
                   where o = ones bit lines
                         z = zeros bit lines
                         r = if o >= z then 1 else 0
                         fr = filter (\l -> l!!bit == chr (r+48)) lines
                         fnr = filter (\l -> l!!bit /= chr (r+48)) lines


partB :: [String] -> Int
partB lines = let (oxy, co2, _, _) = foldl (\(ones, zeros, olines, zlines) -> \bit ->
                                       let (a1,_,c1,_) = filtruj bit olines
                                           (_,b0,_,d0) = filtruj bit zlines
                                       in (ones++[a1], zeros++[b0], c1, d0))
                                     ([],[], lines, lines)
                                     [0..length (head lines)-1]
               in  bits2Int oxy * bits2Int co2

main :: IO ()
main = do
  s <- readFile "03.inp"
  let input = lines s
  print $ partA input
  print $ partB input
 */

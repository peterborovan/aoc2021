import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

data class Po(val x: Int, val y: Int, val z : Int)

fun main() {
    val inp = File(
        "day22.txt"
     //   "day22_.txt"
     //   "day22__.txt"  // 2758514936282235
     // "day22___.txt"  //
    ).readLines().map { it.split(",")}

    val ons = mutableSetOf<Po>()
    val xs = mutableSetOf<Int>()
    val ys = mutableSetOf<Int>()
    val zs = mutableSetOf<Int>()
    for (l in inp) {
        val (xmin,xmax) = Pair(l[1].toInt(), l[2].toInt())
        val (ymin,ymax) = Pair(l[3].toInt(), l[4].toInt())
        val (zmin,zmax) = Pair(l[5].toInt(),l[6].toInt())

        xs.add(xmin)
        xs.add(xmax+1)
        ys.add(ymin)
        ys.add(ymax+1)
        zs.add(zmin)
        zs.add(zmax+1)
          for (x in max(-50,xmin)..min(50,xmax))
                for (y in max(-50,ymin)..min(50,ymax))
                    for (z in max(-50,zmin)..min(50,zmax))
                        if (l[0] == "on") ons.add(Po(x,y,z)) else ons.remove(Po(x,y,z))
    }
    println("partA: ${ons.size}")
    val xssort = xs.toList().sorted()
    val yssort = ys.toList().sorted()
    val zssort = zs.toList().sorted()
    val xxx = Array(xssort.size) {Array(yssort.size) {Array(zssort.size) { 0 }}}
    for (l in inp) {
        val (xmin,xmax) = Pair(l[1].toInt(), l[2].toInt()+1)
        val (ymin,ymax) = Pair(l[3].toInt(), l[4].toInt()+1)
        val (zmin,zmax) = Pair(l[5].toInt(),l[6].toInt()+1)
        for (xi in 0 until xssort.size-1)
            for (yi in 0 until yssort.size-1)
                for (zi in 0 until zssort.size-1)
                    if (xmin <= xssort[xi] && xssort[xi+1] <= xmax && ymin <= yssort[yi] && yssort[yi+1] <= ymax && zmin <= zssort[zi] && zssort[zi+1] <= zmax)
                        xxx[xi][yi][zi] = if (l[0] == "on") 1 else 0
    }
    val partB =
    (0 until xssort.size-1).flatMap { xi ->
        (0 until yssort.size-1).flatMap { yi ->
            (0 until zssort.size-1).map { zi ->
                if (xxx[xi][yi][zi] > 0)
                    (xssort[xi+1]-xssort[xi]).toLong() * (yssort[yi+1]-yssort[yi]) * (zssort[zi+1]-zssort[zi])
                else 0
            }
        }
    }.sum()
    println("partB: $partB")
}
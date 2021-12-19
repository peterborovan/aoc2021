import java.io.File

data class Point(val x:Int, val y: Int, val z: Int) {
    override fun toString() : String = "[$x,$y,$z]"
}

fun Point.dist(p : Point) : Int =
    Math.abs(x-p.x)+Math.abs(y-p.y)+Math.abs(z-p.z)

fun Point.perm(n : Int): Point =
    when (n) {
        0 -> Point(x,y,z)
        1 -> Point(x,z,y)
        2 -> Point(y,x,z)
        3 -> Point(y,z,x)
        4 -> Point(z,x,y)
        5 -> Point(z,y,x)
        else -> Point(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)
    }

fun Point.signs(n : Int): Point =
    when (n) {
        0 -> Point(x, y, z)
        1 -> Point(x, y, -z)
        2 -> Point(x, -y, z)
        3 -> Point(x, -y, -z)
        4 -> Point(-x, y, z)
        5 -> Point(-x, y, -z)
        6 -> Point(-x, -y, z)
        7 -> Point(-x, -y, -z)
        else -> Point(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)
    }
fun List<Point>.rotation(r : Int) : List<Point> = this.map { it.perm(r%6).signs(r/6) }
fun List<Point>.rotations() : List<List<Point>> = (0..47).map { r -> rotation(r) }
fun Point.absolute(l : List<Point>) : List<Point> = l.map{ Point(it.x+x, it.y+y, it.z+z) }

fun main() {
    val inp = File(
        "day19.txt"
      //  "day19_.txt"
    ).readLines()
    var scanner = 0
    val reports = listOf<List<Point>>().toMutableList()
    var points = listOf<Point>()
    for(l in inp) {
        if (l.isEmpty()) {
            scanner++
            reports.add(points)
            continue
        }
        if (l.contains("scanner")) {
            points = listOf()
        } else {
            val (x,y,z) = l.split(",").map {it.toInt()}
            points = points.plus(Point(x,y,z))
        }
    }
    reports.add(points)

    val beacons = reports[0].toMutableSet()
    val scanners = Array<Point?>(reports.size){null}
    scanners[0] = Point(0,0,0)
    label@
    for (i in 0 until reports.size) {
        for (next in 0 until reports.size) {
            if (scanners[next] != null) continue
            for (r in reports[next].rotations()) {
                val success =
                    beacons.flatMap {
                            be -> r.map {
                                nbe -> Point(be.x - nbe.x, be.y - nbe.y,be.z - nbe.z ) } }
                        .groupingBy { it }.eachCount().filter { (_,v) -> v >= 12 }
                if (success.isNotEmpty()) {
                    scanners[next] = success.keys.first()
                    beacons.addAll(scanners[next]!!.absolute(r))
                    continue@label
                }
            }
        }
    }
    println("partA: ${beacons.size}")
    val partB = scanners.flatMap {
        sc1 -> scanners.map { sc2 -> sc1!!.dist(sc2!!)
     }
    }.maxOrNull()
    println("partB: $partB")
}

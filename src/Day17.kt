fun main() {
    val rangex=185..221
    val rangey=-122..-74
//    val rangex = 20..30
//    val rangey = -10..-5
    var partB = 0
    val li = mutableListOf<Int>()
    for(idx in -1000..1000) {
        for(idy in -1000..1000) {
            var x = 0
            var y = 0
            var dx = idx // 6 //17
            var dy = idy // 3 // -4
            var maxy = Int.MIN_VALUE
            while (true) {
                x += dx
                y += dy
                if (y > maxy) maxy = y
                if (x in rangex && y in rangey) {
                    li.add(maxy)
                    partB++
                    break
                } else  if (y < rangey.first) {
                    break
                }
                if (dx > 0) dx--
                else {
                    if (dx < 0) dx++
                }
                dy--
            }
        }
    }
    println("partA: ${li.maxOrNull()}")
    println("partB: $partB")
}

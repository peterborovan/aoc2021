//import java.io.File
//import java.lang.Integer.max
//import java.lang.Integer.min
//
//data class Po(val x: Int, val y: Int, val z : Int)
//fun main() {
//    val inp = File(
//        "day22.txt"
//     //   "day22_.txt"
//    ).readLines().map { it.split(",")}
//
//    val ons = mutableSetOf<Po>()
//    for (l in inp) {
//        val xmin = l[1].toInt()
//        val xmax = l[2].toInt()
//        val ymin = l[3].toInt()
//        val ymax = l[4].toInt()
//        val zmin = l[5].toInt()
//        val zmax = l[6].toInt()
//        println(l)
//            for (x in max(-50,xmin)..min(50,xmax))
//                for (y in max(-50,ymin)..min(50,ymax))
//                    for (z in max(-50,zmin)..min(50,zmax))
//                        if (l[0] == "on") {
//                            ons.add(Po(x,y,z))
//                        } else {
//                            ons.remove(Po(x,y,z))
//                        }
//    }
//    println(ons.size)
//}
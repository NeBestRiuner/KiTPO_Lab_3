

import java.io.InputStreamReader
import java.util.Random
import kotlin.math.pow
import kotlin.math.sqrt



class Point : UserType {
    var x: Double = 0.0
    var y: Double = 0.0

    constructor()
    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    override fun typeName(): String {
        return javaClass.toString()
    }

    override fun create(): Any {
        val rnd = Random()
        x = rnd.nextDouble()
        y = rnd.nextDouble()
        return Point(x, y)
    }

    override fun clone(): Any {
        val myClone = Point()
        myClone.setXCustom(this.x)
        myClone.setYCustom(this.y)
        return myClone
    }

    override fun readValue(`in`: InputStreamReader?): Any? {
        return null
    }

    override fun parseValue(ss: String): Any {
        val words = ss.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val x = words[0].toDouble()
        val y = words[1].toDouble()
        return Point(x, y)
    }

    override val typeComparator: Comparator = object : Comparator {
            override fun compare(o1: Any, o2: Any): Int {
                val point1 = o1 as Point
                val point2 = o2 as Point

                val distance1 = sqrt(point1.x.pow(2.0) + point1.y.pow(2.0))
                val distance2 = sqrt(point2.x.pow(2.0) + point2.y.pow(2.0))

                val diff = distance2 - distance1

                return when {
                    diff > 0 -> if (diff < 1) 1 else Math.round(diff).toInt()
                    diff == 0.0 -> 0
                    diff > -1 -> -1
                    else -> Math.round(diff).toInt()
                }
            }
    }

    override fun getValue(): Any {
        return "$x $y"
    }

    fun setXCustom(x: Double) {
        this.x = x
    }

    fun setYCustom(y: Double) {
        this.y = y
    }
}
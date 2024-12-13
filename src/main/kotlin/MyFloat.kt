

import java.io.InputStreamReader
import java.util.Random

class MyFloat(var value : Float) : UserType {

    override fun typeName(): String {
        return javaClass.toString()
    }

    override fun create(): Any {
        val rnd = Random()
        value = rnd.nextFloat(100f)
        return MyFloat(value)
    }

    override fun clone(): Any {
        val myClone = MyFloat(value)
        myClone.setValueCustom(this.value)
        return myClone
    }

    override fun readValue(`in`: InputStreamReader?): Any? {
        return null
    }

    override fun parseValue(ss: String): Any {
        return MyFloat(ss.toFloat())
    }

    override val typeComparator: Comparator = object: Comparator {
        override fun compare(o1: Any, o2: Any): Int {
            val float1 = o1 as MyFloat
            val float2 = o2 as MyFloat
            return (float1.value - float2.value).toInt()
        }
    }
    override fun getValue(): Any {
        return value
    }

    fun setValueCustom(value: Float) {
        this.value = value
    }
    companion object{
        val tc : Comparator = object: Comparator {
            override fun compare(o1: Any, o2: Any): Int {
                val float1 = o1 as MyFloat
                val float2 = o2 as MyFloat
                return (float1.value - float2.value).toInt()
            }
        }
    }
}
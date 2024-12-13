

import java.io.InputStreamReader
import java.util.*

class MyInt(var value : Int) : UserType {

    override fun typeName(): String {
        return javaClass.toString()
    }

    override fun create(): Any {
        val rnd = Random()
        value = rnd.nextInt(100)
        return MyInt(value)
    }

    override fun clone(): Any {
        val myClone = MyInt(value)
        myClone.setValueCustom(value)
        return myClone
    }

    override fun readValue(`in`: InputStreamReader?): Any? {
        return null
    }

    override fun parseValue(ss: String): Any {
        return MyInt(ss.toInt())
    }

    override fun getValue(): Any{
        return value
    }

    fun setValueCustom(value: Int) {
        this.value = value
    }
    override val typeComparator: Comparator = object: Comparator {
        override fun compare(o1: Any, o2: Any): Int {
            val int1 = o1 as MyInt
            val int2 = o2 as MyInt
            return (int1.value - int2.value)
        }
    }
    companion object{
        val tc : Comparator = object: Comparator {
            override fun compare(o1: Any, o2: Any): Int {
                val int1 = o1 as MyInt
                val int2 = o2 as MyInt
                return (int1.value - int2.value)
            }
        }
    }

}


import java.io.InputStreamReader
import java.util.*

class MyString(var value: String) : UserType {

    override fun typeName(): String {
        return javaClass.toString()
    }

    override fun create(): Any {
        val rnd = Random()
        value = rnd.nextInt(20).toString() + rnd.nextInt(20).toString() + " string"
        return MyString(value)
    }

    override fun clone(): Any {
        val myClone = MyString(value)
        myClone.setValueCustom(this.value)
        return myClone
    }

    override fun readValue(`in`: InputStreamReader?): Any? {
        return null
    }

    override fun parseValue(ss: String): Any {
        return MyString(ss)
    }

    override val typeComparator: Comparator = object: Comparator {
        override fun compare(o1: Any, o2: Any): Int {
            val str1 = o1 as MyString
            val str2 = o2 as MyString

            return str1.value.length - str2.value.length
        }

    }

    override fun getValue(): Any {
        return value
    }

    fun setValueCustom(value: String) {
        this.value = value
    }
    companion object{
        val tc : Comparator = object: Comparator {
            override fun compare(o1: Any, o2: Any): Int {
                val str1 = o1 as MyString
                val str2 = o2 as MyString

                return str1.value.length - str2.value.length
            }
        }
    }
}
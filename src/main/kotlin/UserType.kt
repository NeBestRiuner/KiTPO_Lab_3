
import java.io.InputStreamReader
import java.io.Serializable

interface UserType : Serializable {
    fun typeName(): String?
    fun create(): Any?
    fun clone(): Any
    fun readValue(`in`: InputStreamReader?): Any?
    fun parseValue(ss: String): Any?
    fun getValue(): Any?
   // val value: Any?
    val typeComparator: Comparator
}
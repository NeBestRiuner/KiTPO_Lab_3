
import java.io.Serializable

class MyNode internal constructor(var prev: MyNode?, var value: UserType?, var next: MyNode?) : Serializable {
    fun printValue(): Any? {
        return value?.getValue()
    }
}
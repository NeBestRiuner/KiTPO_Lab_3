import java.io.Serializable

interface Comparator : Serializable{
    fun compare(o1: Any, o2: Any) : Int
}

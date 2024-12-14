
import java.io.*
import java.io.Serializable

class MyLinkedList : Serializable {
    var first: MyNode? = null
    var last: MyNode? = null

    private var size = 0
    fun add(value: UserType) {
        val newNode = MyNode(null, value, null)
        if (size == 0) {
            last = newNode
            first = newNode
        } else {
            newNode.prev = last!!
            last!!.next = newNode
            last = newNode
        }
        size++
    }

    fun get(index: Int): MyNode? {
        require(!(index < 0 || index >= size)) { "Invalid index $index" }
        var cur: MyNode?
        if (index < size / 2) {
            cur = first
            for (i in 0 until index) {
                cur = cur!!.next
            }
        } else {
            cur = last
            for (i in size - 1 downTo index + 1) {
                cur = cur!!.prev
            }
        }
        return cur
    }

    fun remove(index: Int): Any {
        val cur = this.get(index)
        if (cur!!.prev != null) {
            cur.prev!!.next = cur.next
        } else {
            this.first = cur.next
        }
        if (cur.next != null) {
            cur.next!!.prev = cur.prev
        } else {
            this.last = cur.prev
        }
        val value: UserType? = cur.value
        cur.value = null
        cur.next = null
        cur.prev = null
        size--
        return value!!
    }

    fun addNum(value: UserType, index: Int) {
        if (index == size) {
            this.add(value)
        } else {
            val myNode = MyNode(null, value, null)
            val cur = this.get(index)
            myNode.next = cur!!
            if (cur.prev != null) {
                cur.prev!!.next = myNode
                myNode.prev = cur.prev
            } else {
                first = myNode
            }
            cur.prev = myNode
            size++
        }
    }

    fun set(value: UserType, index: Int) {
        val cur = this.get(index)
        cur!!.value = value
    }

    fun size(): Int {
        return size
    }

    fun print() {
        var prev = first
        for (i in 0 until size) {
            print(" " + prev!!.printValue() + " ")
            prev = prev.next
        }
    }

    fun quickSort(linkedList: MyLinkedList, low: Int, high: Int, comparator: Comparator) {
        if (linkedList.size() == 0 || low >= high) return

        //выбираем опорный элемент  - средний
        val middle = low + (high - low) / 2
        val border = linkedList.get(middle)!!.value
        //разделияем на подмассивы и меняем местами
        var i = low
        var j = high
        while (i <= j) {
            var valueI = linkedList.get(i)
            while (comparator.compare(valueI!!.value!!, border!!) < 0) {
                i++
                valueI = valueI.next
            }
            var valueJ = linkedList.get(j)
            while (comparator.compare(valueJ!!.value!!, border!!) > 0) {
                j--
                valueJ = valueJ.prev
            }
            if (i <= j) { // улучшение, вместо get valueI и valueJ
                /*
                UserType swap = linkedList.get(i).getValue();
                linkedList.set(linkedList.get(j).getValue(),i);
                linkedList.set(swap,j);*/
                val swap = valueI.value
                valueI.value = valueJ.value
                valueJ.value = swap
                i++
                j--
            }
        }

        //рекурсия для сортировки левой и правой части
        if (low < j) quickSort(linkedList, low, j, comparator)
        if (high > i) quickSort(linkedList, i, high, comparator)
    }
    fun mergeSort(linkedList: MyLinkedList, comparator: Comparator):MyLinkedList{
        if (linkedList.size <= 1) {
            return linkedList
        }
        val middle = linkedList.size / 2
        // Разделяем список на две половины
        var leftLL : MyLinkedList = MyLinkedList()
        var rightLL : MyLinkedList = MyLinkedList()
        var current = linkedList.first
        for(num in 0..<(linkedList.size)){
            if(num<middle){
                leftLL.add(current!!.value!!)
            }else{
                rightLL.add(current!!.value!!)
            }
            current = current.next
        }
        // Рекурсивно сортируем каждую половину
        val sortedLeft = mergeSort(leftLL, comparator)
        val sortedRight = mergeSort(rightLL, comparator)

        // Объединяем отсортированные половины
        return merge(sortedLeft, sortedRight, comparator)
    }
    fun merge(left:MyLinkedList, right:MyLinkedList,comparator: Comparator):MyLinkedList{
        var leftIndex = 0
        var rightIndex = 0
        val result = MyLinkedList()

        var leftElem = left.first
        var rightElem = right.first
        while (leftIndex < left.size && rightIndex < right.size) {
            if (comparator.compare(leftElem!!.value!!,rightElem!!.value!!)>=0) {
                result.add(leftElem.value!!)
                leftIndex++
                leftElem = leftElem.next
            } else {
                result.add(rightElem.value!!)
                rightIndex++
                rightElem  = rightElem.next
            }
        }
        while (leftIndex < left.size){
            result.add(leftElem!!.value!!)
            leftIndex++
            leftElem = leftElem.next
        }
        while (rightIndex < right.size){
            result.add(rightElem!!.value!!)
            rightIndex++
            rightElem  = rightElem.next
        }
        return result
    }
    fun forEach(forEach: ForEach) {
        var cur = first
        while (cur != null) {
            forEach.toDo(cur.value)
            cur = cur.next
        }
    }

    companion object {
       // @Throws(IOException::class)
        fun serializeToBinary(myLinkedList: MyLinkedList) {
            val file = File("file.out")
           // println(1)
            val fos = FileOutputStream("file.out")
          //  println(2)
            val oos = ObjectOutputStream(fos)
         //   println(3)
            oos.writeObject(myLinkedList)
       //     println(4)
            oos.flush()
    //       println(5)
            oos.close()
        }

        @Throws(IOException::class, ClassNotFoundException::class)
        fun deserializeFromBinary(): MyLinkedList {
            val fis = FileInputStream("file.out")
            val oin = ObjectInputStream(fis)
            return oin.readObject() as MyLinkedList
        }
    }
}
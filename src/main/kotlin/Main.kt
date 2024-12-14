
import java.io.IOException
import java.io.PrintStream
import java.util.*
import kotlin.reflect.KClass

@Throws(IOException::class)
fun main() {
    var linkedList: MyLinkedList? = null
    var myType = -1
    val scanner = Scanner(System.`in`)
    val printStream = PrintStream(System.out, true, "UTF-8")
    try {
        while (true) {
            printStream.println(
                """
                    Введите номер действия: 
                    1. Создать новый двусвязный список
                    2. Добавить значение в конец списка
                    3. Добавить значение в список по индексу
                    4. Удалить значение из списка
                    5. Заменить значение в списке
                    6. Вывести длину списка
                    7. Отсортировать список
                    8. Вывести список
                    9. Сериализовать список в файл
                    10. Десериализовать список из файла
                    11. Выйти
                    
                    """.trimIndent()
            )
            val num = scanner.nextInt()
            when (num) {
                1 -> {
                    printStream.println(
                        """
                            Введите номер поддействия:
                            1. Создать двусвязный список Int
                            2. Создать двусвязный список Float
                            3. Создать двусвязный список String
                            4. Создать двусвязный список Point
                            
                            """.trimIndent()
                    )
                    val podNum = scanner.nextInt()
                    when (podNum) {
                        1 -> {
                            linkedList = MyLinkedList()
                            myType = 1
                        }

                        2 -> {
                            linkedList = MyLinkedList()
                            myType = 2
                        }

                        3 -> {
                            linkedList = MyLinkedList()
                            myType = 3
                        }

                        4 -> {
                            linkedList = MyLinkedList()
                            myType = 4
                        }

                        else -> {}
                    }
                }

                2 -> {
                    var str: String
                    when (myType) {
                        -1 -> printStream.println("Нельзя добавить значение, список не создан")
                        1 -> try {
                            printStream.println("Введите целочисленное значение:")
                            str = scanner.nextLine()
                            str = scanner.nextLine()
                            linkedList!!.add(((MyInt(str.toInt())) as MyInt?)!!)
                        } catch (nfe: NumberFormatException) {
                            printStream.println("Неправильно введённый тип данных")
                        }

                        2 -> {
                            printStream.println("Введите значение типа float:")
                            try {
                                str = scanner.nextLine()
                                str = scanner.nextLine()
                                linkedList!!.add(((MyFloat(str.toFloat())) as MyFloat?)!!)
                            } catch (nfe: NumberFormatException) {
                                printStream.println("Неправильно введённый тип данных")
                            }
                        }

                        3 -> {
                            printStream.println("Введите строковое значение:")
                            try {
                                str = scanner.nextLine()
                                str = scanner.nextLine()
                                linkedList!!.add(((MyString(str)) as MyString?)!!)
                            } catch (nfe: NumberFormatException) {
                                printStream.println("Неправильно введённый тип данных")
                            }
                        }

                        4 -> {
                            var x = 0.0
                            var y = 0.0
                            try {
                                printStream.println("Введите координату точки X:")
                                str = scanner.nextLine()
                                str = scanner.nextLine()
                                x = str.toDouble()
                                printStream.println("Введите координату точки Y:")
                                str = scanner.nextLine()
                                y = str.toDouble()
                                linkedList!!.add(Point(x, y))
                            } catch (nfe: NumberFormatException) {
                                printStream.println("Неправильно введённый тип данных")
                            }
                        }

                        else -> {}
                    }
                }

                3 -> when (myType) {
                    -1 -> printStream.println("Нельзя добавить значение, список не создан")
                    1 -> try {
                        var str: String
                        printStream.println("Введите целочисленное значение:")
                        str = scanner.nextLine()
                        str = scanner.nextLine()
                        printStream.println("Введите индекс:")
                        val idx = scanner.nextInt()
                        linkedList!!.addNum(((MyInt(str.toInt())) as MyInt?)!!, idx)
                    } catch (nfe: NumberFormatException) {
                        printStream.println("Неправильно введённый тип данных")
                    }

                    2 -> {
                        printStream.println("Введите значение типа float:")
                        try {
                            var str: String
                            str = scanner.nextLine()
                            str = scanner.nextLine()
                            printStream.println("Введите индекс:")
                            val idx = scanner.nextInt()
                            linkedList!!.addNum(((MyFloat(str.toFloat())) as MyFloat?)!!, idx)
                        } catch (nfe: NumberFormatException) {
                            printStream.println("Неправильно введённый тип данных")
                        }
                    }

                    3 -> {
                        printStream.println("Введите строковое значение:")
                        try {
                            var str: String
                            str = scanner.nextLine()
                            str = scanner.nextLine()
                            printStream.println("Введите индекс:")
                            val idx = scanner.nextInt()
                            linkedList!!.addNum(((MyString(str)) as MyString?)!!, idx)
                        } catch (nfe: NumberFormatException) {
                            printStream.println("Неправильно введённый тип данных")
                        }
                    }

                    4 -> {
                        var x = 0.0
                        var y = 0.0
                        try {
                            var str: String
                            printStream.println("Введите координату точки X:")
                            str = scanner.nextLine()
                            str = scanner.nextLine()
                            x = str.toDouble()
                            printStream.println("Введите координату точки Y:")
                            str = scanner.nextLine()
                            y = str.toDouble()
                            printStream.println("Введите индекс:")
                            val idx = scanner.nextInt()
                            linkedList!!.addNum(Point(x, y), idx)
                        } catch (nfe: NumberFormatException) {
                            printStream.println("Неправильно введённый тип данных")
                        }
                    }

                    else -> {}
                }

                4 -> {
                    printStream.println("Введите индекс значения, которое хотите удалить:")
                    val idx = scanner.nextInt()
                    linkedList?.remove(idx) ?: printStream.println("Списка несуществует")
                }

                5 -> when (myType) {
                    -1 -> printStream.println("Нельзя изменить значение, список не создан")
                    1 -> try {
                        var str: String
                        printStream.println("Введите целочисленное значение:")
                        str = scanner.nextLine()
                        str = scanner.nextLine()
                        printStream.println("Введите индекс:")
                        var idx = scanner.nextInt()
                        linkedList!!.set(((MyInt(str.toInt())) as MyInt?)!!, idx)
                    } catch (nfe: NumberFormatException) {
                        printStream.println("Неправильно введённый тип данных")
                    }

                    2 -> {
                        printStream.println("Введите значение типа float:")
                        try {
                            var str: String
                            str = scanner.nextLine()
                            str = scanner.nextLine()
                            printStream.println("Введите индекс:")
                            var idx = scanner.nextInt()
                            linkedList!!.set(((MyFloat(str.toFloat())) as MyFloat?)!!, idx)
                        } catch (nfe: NumberFormatException) {
                            printStream.println("Неправильно введённый тип данных")
                        }
                    }

                    3 -> {
                        printStream.println("Введите строковое значение:")
                        try {
                            var str: String
                            str = scanner.nextLine()
                            str = scanner.nextLine()
                            printStream.println("Введите индекс:")
                            var idx = scanner.nextInt()
                            linkedList!!.set(((MyString(str)) as MyString?)!!, idx)
                        } catch (nfe: NumberFormatException) {
                            printStream.println("Неправильно введённый тип данных")
                        }
                    }

                    4 -> {
                        var x = 0.0
                        var y = 0.0
                        try {
                            var str: String
                            printStream.println("Введите координату точки X:")
                            str = scanner.nextLine()
                            str = scanner.nextLine()
                            x = str.toDouble()
                            printStream.println("Введите координату точки Y:")
                            str = scanner.nextLine()
                            y = str.toDouble()
                            printStream.println("Введите индекс:")
                            var idx = scanner.nextInt()
                            linkedList!!.set(Point(x, y), idx)
                        } catch (nfe: NumberFormatException) {
                            printStream.println("Неправильно введённый тип данных")
                        }
                    }

                    else -> {}
                }

                6 -> if (linkedList != null) {
                    printStream.println("Длина списка: " + linkedList.size())
                } else {
                    printStream.println("Списка несуществует")
                }

                7 -> when (myType) {
                    -1 -> printStream.println("Нельзя сортировать значения, список не создан")
                    1 -> try {
                        printStream.println("Список до сортировки: ")
                        linkedList!!.print()
                        printStream.println("Список после сортировки: ")
                       // linkedList.quickSort(
                        // linkedList, 0, linkedList.size()-1,
                        //    (MyInt.tc)
                       // )
                        linkedList = linkedList.mergeSort(linkedList,(MyInt.tc))
                        linkedList.print()
                    } catch (nfe: NumberFormatException) {
                        printStream.println("Неправильно введённый тип данных")
                    }

                    2 -> try {
                        printStream.println("Список до сортировки: ")
                        linkedList!!.print()
                        printStream.println("Список после сортировки: ")
                   //     linkedList.quickSort(
                   //         linkedList, 0, linkedList.size()-1,
                  //          (MyFloat.tc)
                   //     )
                        linkedList = linkedList.mergeSort(linkedList,MyFloat.tc)
                        linkedList.print()
                    } catch (nfe: NumberFormatException) {
                        printStream.println("Неправильно введённый тип данных")
                    }

                    3 -> try {
                        printStream.println("Список до сортировки: ")
                        linkedList!!.print()
                        printStream.println("Список после сортировки: ")
                      //  linkedList.quickSort(
                     //       linkedList, 0, linkedList.size()-1,
                     //       (MyString.tc)
                     //   )
                        linkedList = linkedList.mergeSort(linkedList,MyString.tc)
                        linkedList.print()
                    } catch (nfe: NumberFormatException) {
                        printStream.println("Неправильно введённый тип данных")
                    }

                    4 -> try {
                        printStream.println("Список до сортировки: ")
                        linkedList!!.print()
                        printStream.println("Список после сортировки: ")
                        // linkedList.quickSort(
                   //         linkedList, 0, linkedList.size()-1,
                   //         (Point().typeComparator)
                   //     )
                        linkedList = linkedList.mergeSort(linkedList,Point().typeComparator)
                        linkedList.print()
                    } catch (nfe: NumberFormatException) {
                        printStream.println("Неправильно введённый тип данных")
                    }

                    else -> {}
                }

                8 -> if (linkedList != null) {
                    linkedList.print()
                } else {
                    printStream.println("Списка несуществует")
                }

                9 -> try {
                    printStream.println("Список сохраняется: ")
                    if (linkedList != null) {
                        linkedList.print()
                    } else {
                        printStream.println("Списка несуществует")
                    }
                    MyLinkedList.serializeToBinary(linkedList!!)
                   } catch (ioe: IOException) {
                    printStream.println("Неудалось сериализовать список")
               }

                10 -> {
                    try {
                        linkedList = MyLinkedList.deserializeFromBinary()
                        printStream.println("Загрузка из сохранения: ")
                        if (linkedList != null) {
                            linkedList.print()
                        } else {
                            printStream.println("Списка несуществует")
                        }
                    } catch (cnfe: ClassNotFoundException) {
                        printStream.println("Не удалось найти класс")
                    }
                    if (linkedList != null && linkedList.size() != 0) {
                        val myNode = linkedList.first
                        //val cl: Class<*> = myNode!!.value!!.javaClass
                        val kc = myNode!!.value!!::class
                        if (kc == MyInt::class) {
                            myType = 1
                        }
                        if (kc == MyFloat::class) {
                            myType = 2
                        }
                        if (kc == MyString::class) {
                            myType = 3
                        }
                        if (kc == Point::class) {
                            myType = 4
                        }
                    }
                }

                11 -> return
                else -> {}
            }
        }
    } catch (ime: InputMismatchException) {
        printStream.println("Неправильно введённый тип данных")
    }
}
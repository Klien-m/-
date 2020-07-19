package 牛客网.剑指offer.kotlin

import java.util.*

/**
 * @since 2020/7/15 18:11
 */
class GetMedian {

    var array: ArrayList<Int> = ArrayList<Int>()

    fun Insert(num: Int) {
        array.add(num)
    }

    private fun BubbleSort() {
        for (i in 0 until array.size - 1) {
            for (j in 0 until array.size - 1 - i) {
                if (array[j] > array[j + 1]) {
                    val tmp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = tmp
                }
            }
        }
    }

    private fun InsertSort(num: Int) {
        if (array.size == 0) {
            array.add(num)
        } else {
            var index = -1
            for (i in array.indices) {
                if (array[i] > num) {
                    index = i
                    break
                }
            }
            if (index == -1) {
                array.add(num)
            } else {
                array.add(0)
                for (i in array.size - 1 downTo index + 1) {
                    array[i] = array[i - 1]
                }
                array[index] = num
            }
        }
    }

    fun GetMedian(): Double? {
        array.sort()
        val index = array.size / 2
        return if (array.size % 2 != 0) {
            array[index].toDouble()
        } else (array[index - 1].toDouble() + array[index].toDouble()) / 2
    }

    // 维护两个堆
    var minHeap = PriorityQueue<Int>()
    var maxHeap = PriorityQueue(Comparator<Int> { o1, o2 -> o2 - o1 })
    var count = 0 // 记录当前个数

    fun insert(num: Int) {
        if (count % 2 == 0) {
            maxHeap.offer(num)
            val max = maxHeap.poll()
            minHeap.offer(max)
        } else {
            minHeap.offer(num)
            val min = minHeap.poll()
            maxHeap.offer(min)
        }
        count++
    }

    fun getMedian(): Double? {
        // 注意此处的浮点数与整数转换
        return if (count % 2 == 0) {
            (minHeap.peek() + maxHeap.peek()) / 2.0
        } else {
            minHeap.peek().toDouble()
        }
    }
}
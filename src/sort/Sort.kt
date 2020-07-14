package sort

import kotlin.random.Random

/**
 * @since 2020/7/14 10:09
 */
object Sort {

    /**
     * 选择排序，每次在未排序的数字中选择一个最小的数与未排序的数字中的第一个交换位置，
     * 如果第一个即为最小的则不交换。
     * 时间复杂度为 pow(n, 2)
     * @param list 输入的待排序的列表
     * @return 排好序的新列表
     */
    fun selectionSort(list: List<Int>): List<Int> {
        val newList = list.toMutableList()
        val length = newList.size
        for (i in 0 until length - 1) {
            var min = i
            for (j in i + 1 until length) {
                if (newList[j] < newList[min]) {
                    min = j
                }
            }
            if (i != min) {
                val tmp = newList[i]
                newList[i] = newList[min]
                newList[min] = tmp
            }
        }
        return newList
    }

    /**
     * 快速排序，选择基准值，将list分为三部分：大于基准值的、小于基准值的、基准值。对前两部分递归调用quickSort。
     * 时间复杂度取决于选择的基准值，最糟糕的情况下为 pow(n, 2)，平均情况下为 (n log n)
     */
    fun quickSort(list: List<Int>): List<Int> {
        return if (list.size < 2) {
            list
        } else {
            val index = list.size / 2
            val pivot = list[index]
            val less = list.filterIndexed { i, it -> (i != index && it < pivot) }
            val greater = list.filterIndexed { i, it -> (i != index && it > pivot) }
            quickSort(less) + listOf(pivot) + quickSort(greater)
        }
    }
}
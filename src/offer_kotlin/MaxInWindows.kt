package offer_kotlin

import java.util.*

/**
 * @since 2020/7/15 11:16
 */
class MaxInWindows {

    fun maxInWindows(num: IntArray, size: Int): ArrayList<Int>? {
        val ret = ArrayList<Int>()
        if (size == 0 || size < 1 || num.size < size) return ret
        for (i in 0 until num.size - (size - 1)) { // 此处注意i的最大值要小于 num.length - (size - 1)，最后一个窗口。
            var max = Int.MIN_VALUE
            for (j in 0 until size) {
                max = max.coerceAtLeast(num[i + j])
            }
            ret.add(max)
        }
        return ret
    }
}
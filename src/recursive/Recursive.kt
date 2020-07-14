package recursive

import EmptyListException
import java.util.*

/**
 * 递归的一些练习
 * @since 2020/7/14 10:37
 */
object Recursive {

    /**
     * 计算遗一串数字和的对外接口，在其内将list转为stack，调用sum(stack)方法
     */
    fun sum(list: List<Int>): Long {
        if (list.isEmpty()) {
            throw EmptyListException("输入列表为空")
        }
        val stack = Stack<Int>()
        stack.addAll(list)
        return sum(stack)
    }

    /**
     * 递归计算一串数字的和
     * @param stack 为了较方便的得到除了第一个元素之外剩余的元素，因此在这里需要传入stack
     * @return 数字和
     */
    private fun sum(stack: Stack<Int>): Long {
        return if (stack.isEmpty()) {
            0L
        } else {
            stack.pop() + sum(stack)
        }
    }

    fun size(list: List<Int>): Int {
        if (list.isEmpty()) {
            throw EmptyListException("输入列表为空")
        }
        val stack = Stack<Int>()
        stack.addAll(list)
        return size(stack)
    }

    private fun size(stack: Stack<Int>): Int {
        return if (stack.isEmpty()) {
            0
        } else {
            stack.pop()
            1 + size(stack)
        }
    }

    fun max(list: List<Int>): Int {
        if (list.isEmpty()) {
            throw EmptyListException("输入列表为空")
        }
        val stack = Stack<Int>()
        stack.addAll(list)
        return max(stack)
    }

    private fun max(stack: Stack<Int>): Int {
        return if (stack.size == 1) {
            stack.pop()
        } else {
            val num = stack.pop()
            if (num > max(stack)) num else max(stack)
        }
    }
    
}
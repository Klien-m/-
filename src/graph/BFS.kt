package graph

import java.util.*

/**
 * 广度优先搜索
 * 代码示例为：在 you 的朋友网中寻找一个名字最后一个字母为 m 的芒果销售商，图用 Map 来存储。
 * @since 2020/7/14 12:34
 */

class BFS {

    /**
     * LinkedList实现了Queue接口，在这里将其作为Queue使用
     */
    private var searchQueue: LinkedList<String> = LinkedList()


    private val graph: Map<String, List<String>> =
            mapOf("you" to listOf("alice", "bob", "claire"),
                    "bob" to listOf("anuj", "peggy"),
                    "alice" to listOf("peggy"),
                    "claire" to listOf("thom", "jonny"),
                    "anuj" to emptyList(), "peggy" to emptyList(), "thom" to emptyList(), "jonny" to emptyList())

    private fun personIsSeller(name: String) = name[name.length - 1] == 'm'

    /**
     * 广度优先搜索，寻找非加权图的最短路径
     * @param name 开始搜索的第一个元素，实际时应为搜索的元素，此处搜索的元素为 最后一个字母为 m 的字符串
     * 时间复杂度为 O(V+E) V为顶点数，E为边数
     */
    fun search(name: String = "you"): Boolean {
        searchQueue.addAll(graph["you"] ?: error(""))
        val searched = emptyList<String>().toMutableList()
        while (searchQueue.isNotEmpty()) {
            val person = searchQueue.pop()
            if (person !in searched) {
                if (personIsSeller(person)) {
                    println("$person is a mango seller!")
                    return true
                } else {
                    searchQueue.addAll(graph[person] ?: error(""))
                    searched.add(person)
                }
            }
        }
        return false
    }
}
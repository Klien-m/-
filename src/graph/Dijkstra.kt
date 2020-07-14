package graph

import kotlin.math.cos

/**
 * @since 2020/7/14 14:34
 */
class Dijkstra {

    /**
     * 使用 map 来表示 有向无环图（DAG）
     */
    private val graph: Map<String, Map<String, Int>> =
            mapOf("start" to mapOf("a" to 6, "b" to 2),
                    "a" to mapOf("fin" to 1),
                    "b" to mapOf("a" to 3, "fin" to 5),
                    "fin" to emptyMap())

    private val maxInt = Int.MAX_VALUE

    /**
     * 到达所有节点的最小花费
     */
    private var costs: MutableMap<String, Int> = mutableMapOf("a" to 6, "b" to 2, "fin" to maxInt)

    /**
     * 到达某一节点所需经过的上一节点
     */
    private var parents: MutableMap<String, String?> = mutableMapOf("a" to "start", "b" to "start", "fin" to null)

    /**
     * 处理过的节点
     */
    private var processed = emptyList<String>().toMutableList()

    /**
     * 找到目前为止花费最低的点。
     */
    private fun findLowestCostNode(costs: Map<String, Int>): String? {
        var lowestCost = maxInt
        var lowestCostNode: String? = null
        for (node in costs.keys) {
            val cost = costs[node]
            if (cost!! < lowestCost && node !in processed) {
                lowestCost = cost
                lowestCostNode = node
            }
        }
        return lowestCostNode
    }

    fun dijkstra(): Int{
        var node = findLowestCostNode(costs)
        while (node != null) {
            val cost = costs[node]
            val neighbors = graph[node]
            for (n in neighbors?.keys!!) {
                val newCost = neighbors[n]?.let { cost?.plus(it) }
                if (costs[n] ?: error("") > newCost!!) {
                    costs[n] = newCost
                    parents[n] = node
                }
            }
            processed.add(node)
            node = findLowestCostNode(costs)
        }
        return costs["fin"]!!
    }
}
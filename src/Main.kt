import graph.BFS
import graph.Dijkstra
import recursive.Recursive
import search.Search
import sort.Sort

fun main() {

    // 二分查找
    val searchNumbs = listOf(1, 3, 4, 5, 6, 7, 100)
    println(Search.binarySearch(searchNumbs, 5))

    // 选择排序、快速排序
    val sortNumbs = listOf(5, 10, 3, 15, 9, 7, 6, 15, 19, 2)
    println(Sort.selectionSort(sortNumbs))
    println(Sort.quickSort(sortNumbs))

    // 递归实现求和、求和、求最大值 DC
    val sumNumbs = listOf(1, 2, 3, 4, 5, 6)
    println(Recursive.sum(sumNumbs))
    println(Recursive.size(sumNumbs))
    println(Recursive.max(sumNumbs))

    // 广度优先搜索
    val bfs = BFS()
    bfs.search()

    // 狄克斯特拉算法
    val dijkstra = Dijkstra()
    println("最小花费为 ${dijkstra.dijkstra()}")
}
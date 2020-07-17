package offer_kotlin

import offer_java.KthNode
import java.util.*

/**
 * @since 2020/7/15 19:59
 */
class KthNode {

    class TreeNode(value: Int) {
        var value = 0
        var left: TreeNode? = null
        var right: TreeNode? = null

        init {
            this.value = value
        }
    }

    private var treeNode: TreeNode? = null
    var count = 0

    private fun dfs(pRoot: TreeNode?, k: Int) {
        if (count < k && pRoot!!.left != null) {
            dfs(pRoot.left, k)
        }
        if (++count == k) {
            treeNode = pRoot
        }
        if (count < k && pRoot!!.right != null) {
            dfs(pRoot.right, k)
        }
    }

    /**
     * 寻找二叉搜索树的第k小的节点。
     */
    fun kthNode(pRoot: TreeNode?, k: Int): TreeNode? {
        if (pRoot == null || k <= 0) {
            return null
        }
        dfs(pRoot, k)
        return treeNode
    }

    /**
     * 先序序列化二叉树
     */
    fun Serialize(root: TreeNode?): String {
        return if (root == null) {
            "#"
        } else {
            root.value.toString() + "," + Serialize(root.left) + "," + Serialize(root.right)
        }
    }

    var index = -1

    /**
     * 反序列化二叉树
     */
    fun Deserialize(str: String): TreeNode? {
        val s = str.split(",".toRegex()).toTypedArray()
        index++
        val len = s.size
        if (index > len) {
            return null
        }
        var treeNode: TreeNode? = null
        if (s[index] != "#") {
            treeNode = TreeNode(s[index].toInt())
            treeNode!!.left = Deserialize(str)
            treeNode.right = Deserialize(str)
        }
        return treeNode
    }

    /**
     * 层序遍历二叉树，根据层数换行。
     */
    fun Print(pRoot: TreeNode?): ArrayList<ArrayList<Int>>? {
        val ret = ArrayList<ArrayList<Int>>()
        if (pRoot == null) return ret
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(pRoot)
        while (!queue.isEmpty()) {
            var size = queue.size
            val ans = ArrayList<Int>()
            while (size-- != 0) {
                val treeNode = queue.remove()
                ans.add(treeNode.value)
                if (treeNode.left != null) queue.add(treeNode.left)
                if (treeNode.right != null) queue.add(treeNode.right)
            }
            ret.add(ans)
        }
        return ret
    }
}
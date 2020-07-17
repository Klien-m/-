package offer_kotlin

/**
 * @since 2020/7/14 21:19
 */
class HasPath {
    lateinit var mat: CharArray
    var h = 0
    var w = 0
    var strLength = 0
    var dir = intArrayOf(-1, 0, 1, 0, -1)

    private fun dfs(i: Int, j: Int, pos: Int, str: CharArray): Boolean {
        if (i < 0 || i >= h || j < 0 || j >= w) {
            return false
        }
        val ch = mat[i * w + j]
        if (ch == '#' || ch != str[pos]) {
            return false
        }
        if (pos + 1 == strLength) {
            return true
        }
        mat[i * w + j] = '#'
        for (k in 0..3) {
            if (dfs(i + dir[k], j + dir[k + 1], pos + 1, str)) {
                return true
            }
        }
        mat[i * w + j] = ch
        return false
    }

    fun hasPath(matrix: CharArray, rows: Int, cols: Int, str: CharArray): Boolean {
        mat = matrix
        h = rows
        w = cols
        strLength = str.size
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (dfs(i, j, 0, str)) {
                    return true
                }
            }
        }
        return false
    }
}
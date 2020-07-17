package offer_kotlin

/**
 * @since 2020/7/14 19:30
 */
class MovingCount {

    private fun sum(row: Int, col: Int): Int {
        var row = row
        var col = col
        var sum = 0
        while (row != 0) {
            sum += row % 10
            row /= 10
        }
        while (col != 0) {
            sum += col % 10
            col /= 10
        }
        return sum
    }

    fun movingCount(threshold: Int, rows: Int, cols: Int): Int {
        val flag = Array(rows) { BooleanArray(cols) }
        return dfs(threshold, rows, cols, flag, 0, 0)
    }

    private fun dfs(threshold: Int, rows: Int, cols: Int, flag: Array<BooleanArray>, i: Int, j: Int): Int {
        if (i < 0 || i >= rows || j < 0 || j >= cols || flag[i][j]) {
            return 0
        }
        if (sum(i, j) > threshold) {
            return 0
        }
        flag[i][j] = true
        return dfs(threshold, rows, cols, flag, i - 1, j) +
                dfs(threshold, rows, cols, flag, i + 1, j) +
                dfs(threshold, rows, cols, flag, i, j - 1) +
                dfs(threshold, rows, cols, flag, i, j + 1) + 1
    }
}

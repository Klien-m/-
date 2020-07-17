package offer_java;

/**
 * @since 2020/7/14 18:19
 *
 * 机器人的运动范围
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 *
 * 知识点：BFS
 */

public class MovingCount {

    private int sum(int row, int col) {
        int sum = 0;
        while (row != 0) {
            sum += row % 10;
            row /= 10;
        }
        while (col != 0) {
            sum += col % 10;
            col /= 10;
        }
        return sum;
    }

    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] flag = new boolean[rows][cols];
        return dfs(threshold, rows, cols, flag, 0, 0);
    }

    private int dfs(int threshold, int rows, int cols, boolean[][] flag, int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || flag[i][j]) {
            return 0;
        }
        if (sum(i, j) > threshold) {
            return 0;
        }
        flag[i][j] = true;
        return dfs(threshold, rows, cols, flag, i - 1, j) +
                dfs(threshold, rows, cols, flag, i + 1, j) +
                dfs(threshold, rows, cols, flag, i, j - 1) +
                dfs(threshold, rows, cols, flag, i, j + 1) + 1;
    }
}

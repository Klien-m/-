package offer_java;

/**
 * @since 2020/7/14 20:40
 *
 * 矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 *
 * 知识点：DFS
 */
public class HasPath {
    char[] mat;
    int h = 0, w = 0;
    int strLength = 0;
    int[] dir = {-1, 0, 1, 0, -1};

    private boolean dfs(int i, int j, int pos, char[] str) {
        if (i < 0 || i >= h || j < 0 || j >= w) {
            return false;
        }
        char ch = mat[i * w + j];
        if (ch == '#' || ch != str[pos]) {
            return false;
        }
        if (pos + 1 == strLength) {
            return true;
        }
        mat[i * w + j] = '#';
        for (int k = 0; k < 4; ++k) {
            if (dfs(i + dir[k], j + dir[k + 1], pos + 1, str)) {
                return true;
            }
        }
        mat[i * w + j] = ch;
        return false;
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        mat = matrix;
        h = rows;
        w = cols;
        strLength = str.length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (dfs(i, j, 0, str)) {
                    return true;
                }
            }
        }
        return false;
    }
}

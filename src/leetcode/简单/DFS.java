package leetcode.简单;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 2020/7/23 20:12
 */
public class DFS {

    // DFS TODO
    // 面试题 08.10 2020-07-23 20:13:20
    // 733 2020-07-23 20:16:24
    int[][] ans;

    private void dfs(int[][] image, int i, int j, int newColor, int initColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length) return;
        if (image[i][j] == newColor || image[i][j] != initColor) return;
        image[i][j] = newColor;
        dfs(image, i - 1, j, newColor, initColor);
        dfs(image, i + 1, j, newColor, initColor);
        dfs(image, i, j - 1, newColor, initColor);
        dfs(image, i, j + 1, newColor, initColor);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int initColor = image[sr][sc];
        ans = image;
        dfs(ans, sr, sc, newColor, initColor);
        return ans;
    }

//    private int[][] floodFill(int[][] image, int i, int j, int oldColor, int newColor) {
//        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length) return image;
//        if (image[i][j] == newColor || image[i][j] != oldColor) return image;
//        image[i][j] = newColor;
//        floodFill(image, i - 1, j, oldColor, newColor);
//        floodFill(image, i + 1, j, oldColor, newColor);
//        floodFill(image, i, j - 1, oldColor, newColor);
//        floodFill(image, i, j + 1, oldColor, newColor);
//        return image;
//    }
//
//    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
//        int oldColor = image[sr][sc];
//        return floodFill(image, sr, sc, oldColor, newColor);
//    }


    // 690 2020-07-23 21:04:34
    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) map.put(e.id, e);
        return getImportance(map, id);
    }

    private int getImportance(Map<Integer, Employee> employees, int id) {
        Employee employee = employees.get(id);
        int res = employee.importance;
        for (int i = 0; i < employee.subordinates.size(); i++) {
            Integer subId = employee.subordinates.get(i);
            res += getImportance(employees, subId);
        }
        return res;
    }

}

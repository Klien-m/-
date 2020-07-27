package leetcode.date;

import java.util.*;

/**
 * @since 2020/7/25 7:59
 */
public class Function {

    // 686 2020-07-27 10:13:59
    // 如果B能够在A的N次循环中被找到，最多只需要循环n+2次。
    // 循环终止条件就是累积的字符串长度 > (n + 2) * sizeA = (sizeB/sizeA + 2) * sizeA= sizeB + 2*sizeA.
    public int repeatedStringMatch1(String A, String B) {
        if (A.isEmpty() || B.isEmpty()) return -1;
        int count = 1;
        String tmp = A;
        int sizeA = A.length();
        int sizeB = B.length();
        while (count <= (sizeB / sizeA + 2)) { // 多余的比较没必要，累加到长度大于B再比较
            if (tmp.indexOf(B) != -1) {
                return count;
            }
            count++;
            tmp += A;
        }
        return -1;
    }

    public int repeatedStringMatch(String A, String B) {
        int count = 1;
        StringBuilder s = new StringBuilder(A);
        int lengthB = B.length();
        while (s.length() < lengthB) {
            s.append(A);
            count++;
        }
        if (s.lastIndexOf(B) == -1) {
            s.append(A);
            if (s.lastIndexOf(B) == -1) return -1;
            else return count + 1;
        } else return count;
    }

    // 204 2020-07-27 11:20:38
    // 埃氏筛
    // public int countPrimes(int n) {
    //     if (n < 2) return 0;
    //     boolean[] isPrime = new boolean[n];
    //     Arrays.fill(isPrime, true);
    //     for (int i = 2; i <= Math.sqrt(n); i++) {
    //         if (isPrime[i]) {
    //             for (int j = 2; i * j < n; j++) {
    //                 isPrime[i * j] = false;
    //             }
    //         }
    //     }
    //     int count = 0;
    //     for (int i = 2; i < n; i++) {
    //         if (isPrime[i]) count++;
    //     }
    //     return count;
    // }
    public int countPrimes(int n) {
        if (n < 2) return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) { // 避免重复判断
                    isPrime[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }

    // 840 2020-07-27 14:56:33

    /**
     * 利用了3阶（且取值为：1～9）幻方矩阵的某些数学特性
     * 1):  每行、每列、对角线的和必为 15 （1+...+9）/ 3 = 15
     * 2):  中间数num必为5  (包含中间行的：第2行+第2列+两条斜线的和 == (1+...+9)+ 3*num = 15*4, 故num=5)
     * 3):  取值 1～9
     * 4):  不存在重复数
     */
    public int numMagicSquaresInside(int[][] grid) {
        int row = grid.length, column = grid[0].length, answer = 0;

        //判断对角为 [i-2][j-2] ~ [i][j] 的3阶矩阵是否为幻方
        for (int i = 2; i < row; i++) {
            for (int j = 2; j < column; j++) {
                //若中间数不为 5， 跳过
                if (grid[i - 1][j - 1] != 5)
                    continue;
                //若行、列、斜线和 存在一个不为15， 则跳过
                if ((grid[i - 2][j - 2] + grid[i - 2][j - 1] + grid[i - 2][j] != 15)
                        || (grid[i - 1][j - 2] + grid[i - 1][j] != 10)
                        || (grid[i][j - 2] + grid[i][j - 1] + grid[i][j] != 15)

                        || (grid[i - 2][j - 2] + grid[i - 1][j - 2] + grid[i][j - 2] != 15)
                        || (grid[i - 2][j - 1] + grid[i][j - 1] != 10)
                        || (grid[i - 2][j] + grid[i - 1][j] + grid[i][j] != 15)

                        || (grid[i - 2][j - 2] + grid[i][j] != 10)
                        || (grid[i - 2][j] + grid[i][j - 2] != 10)
                )
                    continue;
                //若存在小于1的数直接跳过（若有不再1～9范围的数，必存在超过9的数和小于1的数）
                if (grid[i - 2][j - 2] < 1 || grid[i - 2][j - 1] < 1 || grid[i - 2][j] < 1
                        || grid[i - 1][j - 2] < 1 || grid[i - 1][j] < 1
                        || grid[i][j - 2] < 1 || grid[i][j - 1] < 1 || grid[i][j] < 1)
                    continue;
                //若存在重复数跳过（此处只可能全为 5）
                if (grid[i - 2][j - 2] == 5)
                    continue;
                //余下情况，满足幻方
                answer++;
            }
        }
        return answer;
    }

    // 532 2020-07-27 15:22:27
    public int findPairs(int[] nums, int k) {
        if (k < 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) { // 先把数组里的数清一遍，以解决 3-5 和 5-3 的问题
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            int key = e.getKey();
            int value = e.getValue();
            if (k == 0 && value > 1)
                count++;
            else if (k != 0 && (map.containsKey(key - k) || map.containsKey(key + k)))
                count++;
        }
        return count;
    }

    // 581 2020-07-27 16:04:08
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[len - 1];
        int left = 0, right = -1;
        for (int i = 0; i < len; i++) {
            if (max > nums[i]) right = i;
            else max = nums[i];
            if (min < nums[len - i - 1]) left = len - i - 1;
            else min = nums[len - i - 1];
        }
        return right - left + 1;
    }

    // 29 2020-07-27 16:23:29
    // 60/8 = (60-32)/8 + 4 = (60-32-16)/8 + 2 + 4 = 1 + 2 + 4 = 7
    private long div(long a, long b) {
        if (a < b) return 0;
        long count = 1;
        long tmp = b;
        while ((tmp + tmp) <= a) {
            count = count + count;
            tmp = tmp + tmp;
        }
        return count + div(a - tmp, b);
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) return -dividend;
            return Integer.MAX_VALUE;
        }
        long a = dividend;
        long b = divisor;
        boolean sign = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = Math.abs(a);
        b = Math.abs(b);
        long res = div(a, b);
        if (sign) return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
        else return (int) (-res);
    }

    // LCP 03 2020-07-27 16:53:50
    // 机器人移动轨迹是固定的，
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int xx = 0, yy = 0;
        Set<Long> set = new HashSet<>();
        set.add((long) xx << 30 | yy); // 存储经过的点的坐标
        for (char c : command.toCharArray()) {
            if (c == 'U') yy++;
            else xx++;
            set.add((long) xx << 30 | yy);
        }
        int cir = Math.min(x / xx, y / yy);
        if (!set.contains(((long) (x - cir * xx) << 30) | (y - cir * yy)))
            return false;
        for (int[] s : obstacles) {
            if (s.length != 2) continue;
            int x1 = s[0];
            int y1 = s[1];
            if (x1 > x || y1 > y) continue;
            cir = Math.min(x1 / xx, y1 / yy);
            if (set.contains(((long) (x1 - cir * xx) << 30) | (y1 - cir * yy)))
                return false;
        }
        return true;
    }
}


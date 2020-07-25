package leetcode.date;

import java.util.Arrays;

/**
 * @since 2020/7/25 7:59
 */
public class Function {

    // 859 2020-07-25 08:00:16
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false; // ①长度不相等
        if (A.equals(B)) {
            int[] count = new int[26];
            for (char c : A.toCharArray())
                count[c - 'a']++;
            for (int c : count)
                if (c > 1) return true; // ②A==B 有重复元素
            return false; // ③A==B 无重复元素，无法交换
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false; // 有两个以上不同元素
                }
            }
            return (second != -1 && A.charAt(first) == B.charAt(second) && A.charAt(second) == B.charAt(first));
        }
    }

    // 475 2020-07-25 09:21:05
    public int findRadius(int[] houses, int[] heaters) {
        int res = 0;
        int n = heaters.length;
        Arrays.sort(heaters);
        for (int house : houses) {
            int left = 0, right = n;
            while (left < right) {
                int mid = left + (right - left) / 2; // 防止加法溢出
                if (house > heaters[mid]) left = mid + 1;
                else right = mid;
            }
            int dist1 = (right == 0) ? Integer.MAX_VALUE : Math.abs(house - heaters[right - 1]);
            int dist2 = (right == n) ? Integer.MAX_VALUE : Math.abs(house - heaters[right]);
            res = Math.max(res, Math.min(dist1, dist2));
        }
        return res;
    }

    // 605 2020-07-25 21:23:24
    // 贪心
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 &&
                    (i == 0 || flowerbed[i - 1] == 0) &&
                    (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    }

    // 58 2020-07-25 21:42:10
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') end--; // 去除末尾多余的空格
        if (end < 0) return 0;
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') start--;
        return end - start;
    }

    // 633 2020-07-25 21:54:19
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }

    // 费马平方和定理
    // 一个非负整数 c 能够表示为两个整数的平方和，
    // 当且仅当 c 的所有形如 4k+3 的质因子的幂次均为偶数。

    // LCP 03 2020-07-25 22:00:03
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int i = 0, j = 0;
        char[] com = command.toCharArray();
        while (i < x && j < y) {
            for (char c : com) {
                if (c == 'R') i++;
                if (obstacles.length != 0)
                    if (i == obstacles[0][0] && j == obstacles[0][1]) return false;
                if (c == 'U') j++;
                if (obstacles.length != 0)
                    if (i == obstacles[0][0] && j == obstacles[0][1]) return false;
            }
        }
        return (i == x && j == y);
    }
}

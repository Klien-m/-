package 牛客网.剑指offer.java;

import java.util.ArrayList;

/**
 * @since 2020/7/15 11:00
 * <p>
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 */
public class MaxInWindows {

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (size == 0 || size < 1 || num.length < size) return ret;
        for (int i = 0; i < num.length - (size - 1); ++i) { // 此处注意i的最大值要小于 num.length - (size - 1)，最后一个窗口。
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < size; j++) {
                max = Math.max(max, num[i + j]);
            }
            ret.add(max);
        }
        return ret;
    }
}

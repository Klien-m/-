package leetcode.date;

import java.util.Arrays;

/**
 * @since 2020/7/25 7:59
 */
public class Function {

    // 1201 2020-07-29 11:33:42
    class TODO1 {
        private long lcmAB;
        private long lcmAC;
        private long lcmBC;
        private long lcmABC;

        public int nthUglyNumber(int n, int a, int b, int c) {
            if (n < 1 || a < 1 || b < 1 || c < 1) return -1;
            long lcmAB = lcm(a, b);
            long lcmAC = lcm(a, c);
            long lcmBC = lcm(b, c);
            long lcmABC = lcm(lcmAB, c);
            long low = Math.min(Math.min(a, b), c);
            long high = low * n;
            long res = binarySearch(low, high, a, b, c, n);
            long leftA = res % a;
            long leftB = res % b;
            long leftC = res % c;
            return (int) (res - Math.min(Math.min(leftA, leftB), leftC));
        }

        private long binarySearch(long low, long high, int a, int b, int c, int n) {
            if (low >= high) return low;
            long mid = (low + high) >> 1;
            long count = mid / a + mid / b + mid / c - mid / lcmAB - mid / lcmBC - mid / lcmAC + mid / lcmABC;
            if (count == n) return mid;
            if (count < n) return binarySearch(mid + 1, high, a, b, c, n);
            return binarySearch(low, mid - 1, a, b, c, n);
        }

        // 最小公倍数
        private long lcm(long a, long b) {
            long multi = a * b;
            while (b > 0) {
                long tmp = a % b;
                a = b;
                b = tmp;
            }
            return multi / a;
        }
    }

    // 面试题 08.03 2020-07-31 10:17:46
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i])
                return i;
        }
        return -1;
    }

    // 69 2020-07-31 11:08:07
    // 牛顿迭代求平方根
    public int mySqrt(int x) {
        if (x == 0) return 0;
        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7)
                break;
            x0 = xi;
        }
        return (int) x0;
    }

    // 643 2020-07-31 11:26:34
    // 累计求和  sum[i] - sum[i-k]
    public double findMaxAverage(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        double res = sum[k - 1] * 1.0 / k;
        for (int i = k; i < nums.length; i++) {
            res = Math.max(res, (sum[i] - sum[i - k]) * 1.0 / k);
        }
        return res;
    }

    // 滑动窗口
    public double findMaxAverage2(int[] nums, int k) {
        double sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i];
        double res = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            res = Math.max(res, sum);
        }
        return res / k;
    }

    // 914 2020-07-31 11:45:35
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[10000];
        for (int d : deck)
            count[d]++;
        int x = 0;
        for (int c : count) {
            if (c > 0) {
                x = gcd(x, c);
                if (x == 1)
                    return false;
            }
        }
        return x >= 2;
    }

    // 747 2020-07-31 14:37:56
    // 第一次遍历找到最大值，第二次遍历看是否存在违反题目要求的数组元素
    public int dominantIndex(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] > nums[maxIndex])
                maxIndex = i;
        for (int i = 0; i < nums.length; i++) {
            if (maxIndex != i && nums[maxIndex] < 2 * nums[i])
                return -1;
        }
        return maxIndex;
    }

    // 一次遍历求得最大值、第二大值
    public int dominantIndex2(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int maxIndex = 0;
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                secondMax = max;
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > secondMax) {
                secondMax = nums[i];
            }
        }
        return max >= secondMax * 2 ? maxIndex : -1;
    }

    // 1477 2020-07-31 14:52:46
    // 此方法超时
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        boolean[] flag = new boolean[100001];
        int res = 0;
        for (int[] event : events) {
            for (int day = event[0]; day <= event[1]; day++) {
                if (!flag[day]) {
                    flag[day] = true;
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}


package leetcode;

/**
 * @since 2020/7/20 15:49
 */
public class 位运算 {

    // 231 2020-07-21 08:57:05
    // -x=-x+1，二进制位中 -x 与 x 只有一位同为 1。
    // x & (x - 1) 若 x 是 2 的幂次方，二进制的 x 中只有一位为 1，减去 1 后，对应位 1 变为 0，右边位全变为 1，相与后为 0。
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = n;
        return (x & (-x)) == x; // return (x & (x - 1)) == 0;
    }

    // 342 2020-07-21 09:08:22
    public boolean isPowerOfFour(int num) {
        if (num < 0 || (num & (num - 1)) != 0) {
            return false;
        }
        return num % 3 == 1;
    }

    // 05.01 2020-07-21 09:13:06
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] a = new int[26];
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0; i < s1.length(); i++) {
            a[c1[i] - 'a']++;
            a[c2[i] - 'a']--;
        }
        for (int n : a) {
            if (n != 0) return false;
        }
        return true;
    }
}

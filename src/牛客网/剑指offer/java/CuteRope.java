package 牛客网.剑指offer.java;

/**
 * @since 2020/7/14 16:32
 * <p>
 * 割绳子
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
 * 每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 */


public class CuteRope {
    public int cutRope(int target) {
        if (target == 2) {
            return 1;
        } else if (target == 3) {
            return 2;
        }
        int[] dp = new int[target + 1];
        for (int i = 1; i <= 4; ++i) {
            dp[i] = i;
        }
//        for (int i = 5; i <= target; ++i) {
//            for (int j = 1; j < i; ++j) { // 此处可使 j < 4，尽可能使每一段长度都为3
//                dp[i] = Math.max(dp[i], j * dp[i - j]);
//            }
//        }
//        return dp[target];

        for (int i = 5; i <= target; ++i) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[target];

//        // 尽可能使每一段长度都为3，此时乘积最大（此为针对此题的做法）。详见：https://www.nowcoder.com/profile/457828468/codeBookDetail?submissionId=64514486
//        if (target % 3 == 0) {
//            return (int) Math.pow(3, target / 3);
//        } else if (target % 3 == 1) {
//            return 4 * (int) Math.pow(3, target / 3 - 1);
//        } else {
//            return 2 * (int) Math.pow(3, target / 3);
//        }

//        if (target < 4) {
//            return target - 1;
//        }
//        int res = 1;
//        while (target > 4) {
//            res = res * 3;
//            target -= 3;
//        }
//        return res * target;
    }
}
package offer_kotlin

/**
 * @since 2020/7/14 16:53
 */
class CuteRope {

    fun cuteRope(target: Int): Int {
        return when (target) {
            2 -> 1
            3 -> 2
            else -> {
                var dp = emptyList<Int>().toMutableList()
                dp.addAll(listOf(0, 1, 2, 3, 4))
                for (i in 5..target) {
                    dp.add(0)
                    for (j in 1..i / 2) {
                        dp[i] = dp[i].coerceAtLeast(dp[j] * dp[i - j])
                    }
                }
                dp[target]
            }
        }
    }
}
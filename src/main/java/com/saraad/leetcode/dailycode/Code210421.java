package com.saraad.leetcode.dailycode;

/**
 * @Title: Code210421
 * @Package:com.saraad.leetcode.dailycode
 * @Description: 91
 * @author: saraad
 * @date: 2021/4/21 10:55 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class Code210421 {

    //1 <= s.length <= 100
    //s 只包含数字，并且可能包含前导零。
    public int numDecodings(String s) {
        int n = s.length();
        //前导0
        if (s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[n + 1];
        //边界 dp[0] = 1, dp[1] = 1
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            if ((s.charAt(i - 2) == '1' && s.charAt(i - 1) != '0') ||
                    (s.charAt(i - 2) == '2' && s.charAt(i - 1) > '0' && s.charAt(i - 1) <= '6')) {
                dp[i] = dp[i - 2] + dp[i - 1];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n];
    }
}

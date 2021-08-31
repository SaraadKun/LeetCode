package com.saraad.leetcode.dailycode.may;

import java.util.HashMap;
import java.util.Map;

public class NumWays {
    public int numWays(int steps, int arrLen) {
        //dp[i][j] 表示移动i步后停在索引j处的方案数, 需%(10^9 + 7)
        //转移方程 dp[i][j]=dp[i-1][j] + dp[i-1][j-1] + dp[i-1][j+1]  分别对应 不动,右移,左移
        //特别的j-1<0 dp[i-1][j-1]=0   j+1>=arrLen dp[i-1][j+1]=0
        //边界条件 1<= j <arrLen dp[0][j]=0  起始索引在0处,dp[0][0] = 1
        int[][] dp = new int[steps + 1][arrLen];
        dp[0][0] = 1;
        int mod = (int) Math.pow(10, 9) + 7;
        for (int i = 1; i < steps + 1; i++) {
            for (int j = 0; j < arrLen; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j < 1) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % mod;
                }
                if (j >= arrLen - 1) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
                }
            }
        }
        return dp[steps][0];
    }
    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};
}

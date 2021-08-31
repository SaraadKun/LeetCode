package com.saraad.leetcode.dailycode.june;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LCP 07. 传递信息
 */
public class NumWays {
    public int numWays(int n, int[][] relation, int k) {
        //dp[i][j] 代表从dp[0][0]出发,经过i步可以到达编号j的方案数 根据题意0 <= i <= k
        //dp[i][j] = sum(dp[i-1][x]) x为可以到达编号j的传递关系
        //dp[0][0] = 1  dp[0][y]=0  y!=0
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] arr : relation) {
                dp[i + 1][arr[1]] += dp[i][arr[0]];
            }
        }
        return dp[k][n-1];
    }

}

package com.saraad.leetcode.dailycode2022.june;

/**
 * @Description: 剑指 Offer II 091. 粉刷房子
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/JEj789/
 * @Date: 25-06-2022 17:54
 */

public class MinCost {

    public int minCost0(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n + 1][3];
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = Math.min(dp[i][1], dp[i][2]) + costs[i][0];
            dp[i + 1][1] = Math.min(dp[i][0], dp[i][2]) + costs[i][1];
            dp[i + 1][2] = Math.min(dp[i][0], dp[i][1]) + costs[i][2];
        }
        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }

    public int minCost(int[][] costs) {
        int n = costs.length;
        //状态压缩
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < n; i++) {
            int aa = Math.min(b, c) + costs[i][0];
            int bb = Math.min(a, c) + costs[i][1];
            int cc = Math.min(a, b) + costs[i][2];
            a = aa; b = bb; c = cc;
        }
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        MinCost minCost = new MinCost();
        int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
        assert minCost.minCost(costs) == 10;
//        assert minCost.minCost(costs) == 11;
    }

}

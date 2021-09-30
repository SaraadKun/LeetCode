package com.saraad.leetcode.dailycode.april;

public class NthUglyNumber {
    public int nthUglyNumber(int n) {
        //1.1为丑数
        //2.对于任意的0<=i<n, 2dp[i],3dp[i],5dp[i]均为丑数
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int[] arr = {1, 1, 1};
        int[] cons = {2, 3, 5};
        for (int i = 1; i < n; i++) {
            dp[i] = min(2 * dp[arr[0]], 3 * dp[arr[1]], 5 * dp[arr[2]]);
            for (int j = 0; j < 3; j++) {
                if (dp[arr[j]] * cons[j] == dp[i]) {
                    arr[j] += 1;
                }
            }
        }
        return dp[n];
    }

    private static int min(int... nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = Math.min(res, nums[i]);
        }
        return res;
    }
}

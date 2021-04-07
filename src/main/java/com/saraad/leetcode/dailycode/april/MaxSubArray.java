package com.saraad.leetcode.dailycode.april;

/**
 * 53. 最大子序和
 */
public class MaxSubArray {
    /**
     * 一维动归  0<=i=len, dp[i]代表以下标i为结尾的最大子序和
     * 边界: dp[0] = nums[0]
     * 状态转移方程: dp[i] = dp[i-1] > 0 ? dp[i-1] + nums[i] : nums[i]
     *
     * 空间可优化,只需存dp[i-1]即可,O(n)=>O(1)
     */
    public int maxSubArray(int[] nums) {
        int sum = 0, res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum = sum > 0 ? sum + nums[i] : nums[i];
            res = Math.max(sum, res);
        }
        return res;
    }
}

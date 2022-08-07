package com.saraad.leetcode.dailycode2022.august;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 07-08-2022 11:02
 */

public class ValidPartition {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; dp[1] = false; dp[2] = nums[0] == nums[1];
        for (int i = 2; i < n; i++) {
            dp[i + 1] = (dp[i - 1] && nums[i] == nums[i - 1])
                    || (dp[i - 2] && nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2])
                    || (dp[i - 2] && nums[i] == nums[i - 1] + 1 && nums[i - 1] == nums[i - 2] + 1);
        }
        return dp[n];
    }

    private boolean backtrack(int[] nums, int i) {
        if (i == nums.length) {
            return true;
        }
        int j = i + 1, k;
        boolean flag = false;
        if (j < nums.length && nums[i] == nums[j]) {
            flag |= backtrack(nums, j + 1);
            if ((k = j + 1) < nums.length && nums[k] == nums[j]) {
                flag |= backtrack(nums, k + 1);
            }
        }
        if (j < nums.length && nums[i] + 1 == nums[j] && (k = j + 1) < nums.length && nums[k] == nums[j] + 1) {
            flag |= backtrack(nums, k + 1);
        }
        return flag;
    }

    public static void main(String[] args) {
        ValidPartition vp = new ValidPartition();
//        int[] nums = {1,1,1,2};
        int[] nums = {803201,803201,803201,803201,803202,803203};
        System.out.println(vp.validPartition(nums));
    }
}

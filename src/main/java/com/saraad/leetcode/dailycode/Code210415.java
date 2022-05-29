package com.saraad.leetcode.dailycode;

/**
 * @Title: Code210415
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/4/15 10:38 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class Code210415 {
    public static void main(String[] args) {
        int[] nums = {200,3,140,20,10};
        int rob = rob(nums);
        System.out.println(rob);
    }

    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int p1 = nums[0], ans1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n - 1; i++) {
            int pp = p1;
            p1 = ans1;
            ans1 = Math.max(pp + nums[i], p1);
        }
        int p2 = nums[1], ans2 = Math.max(nums[1], nums[2]);
        for (int j = 3; j < n; j++) {
            int pp = p2;
            p2 = ans2;
            ans2 = Math.max(pp + nums[j], p2);
        }
        return Math.max(ans1, ans2);
    }

    private static int max(int[] nums, int start, int end) {
        int res = nums[start];
        for (int i = start + 1; i < end; i++) {
            res = Math.max(nums[i], res);
        }
        return res;
    }

}

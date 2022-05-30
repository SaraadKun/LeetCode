package com.saraad.leetcode.dailycode2022.february;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1984. 学生分数的最小差值
 */
public class MinimumDifference {

    public static void main(String[] args) {
        MinimumDifference instance = new MinimumDifference();
//        int[] nums = {93614,91956,83384,14321,29824,89095,96047,25770,39895};
        int[] nums = {9, 4, 1, 7};
        System.out.println(JSON.toJSONString(nums));
        int k = 2;
        int res = instance.minimumDifference(nums, k);
        System.out.println(res);
        Arrays.sort(nums);
        System.out.println(JSON.toJSONString(nums));
    }

    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < len - k + 1; i++) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }

}

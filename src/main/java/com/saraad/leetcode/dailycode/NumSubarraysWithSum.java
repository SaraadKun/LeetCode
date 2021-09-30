package com.saraad.leetcode.dailycode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: NumSubarraysWithSum
 * @Package:com.saraad.leetcode.dailycode
 * @Description: 930 和相同的二元子数组
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 * 子数组 是数组的一段连续部分。
 * @author: saraad
 * @date: 2021/7/8 1:20 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class NumSubarraysWithSum {

    public int numSubarraysWithSum(int[] nums, int goal) {
        int res = 0, sum = 0;
        Map<Integer,Integer> prefixSum = new HashMap<>();
        for (int num : nums) {
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
            sum += num;
            res += prefixSum.getOrDefault(sum - goal, 0);
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 0, 1, 0, 1};
        int[] nums = {0, 0, 0, 0, 0};
        int goal = 0;
        NumSubarraysWithSum obj = new NumSubarraysWithSum();
        int i = obj.numSubarraysWithSum(nums, goal);
        System.out.println(i);
    }
}

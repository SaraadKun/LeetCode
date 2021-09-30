package com.saraad.leetcode.dailycode;

/**
 * @Title: Code210409
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/4/8 9:56 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class Code210409 {

    public static void main(String[] args) {
//        int[] nums = {11,13,15,17};
//        int[] nums = {3};
        int[] nums = {3,4,1,2};
        int min = findMin(nums);
        System.out.println(min);
    }
    public static int findMin(int[] nums) {
        int len = nums.length;
        int lo = 0, hi = len - 1;
        while (lo < hi && nums[lo] > nums[hi]) {
            int mid = lo + (hi - lo) / 2;
            //最小值在(lo, mid]中,向左归并
            if (nums[mid] < nums[lo]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return nums[lo];
    }
}

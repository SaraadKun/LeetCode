package com.saraad.leetcode.dailycode.april;

/**
 * 80. 删除有序数组中的重复项 II
 */
public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        int l = 0, r = 2;
        for (; r < len; r++) {
            nums[l + 2] = nums[r];
            if (nums[l] != nums[r]) {
                l++;
            }
        }
        return l + 2;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 3, 3};
        int i = removeDuplicates(nums);
        System.out.println(i);
    }
}

package com.saraad.leetcode.dailycode.april;

/**
 * 88 合并两个有序数组  2021-04-05
 */
public class Merge2SortArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m + n == 0 || n == 0) {
            return;
        }
        int i = m - 1, j = n - 1;
        for (int p = m + n - 1; p >= 0; p--) {
            if (j < 0 || (i >= 0 && nums1[i] > nums2[j])) {
                nums1[p] = nums1[i];
                i--;
            } else {
                nums1[p] = nums2[j];
                j--;
            }
        }
    }

}

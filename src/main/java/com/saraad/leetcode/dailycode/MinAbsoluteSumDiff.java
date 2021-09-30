package com.saraad.leetcode.dailycode;

import java.util.Arrays;

/**
 * @Title: MinAbsoluteSumDiff
 * @Package:com.saraad.leetcode.dailycode
 * @Description: 1818. 绝对差值和
 * @author: saraad
 * @date: 2021/7/14 10:33 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class MinAbsoluteSumDiff {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int mod = (int)1e9 + 7;
        int[] sorted = new int[len];
        System.arraycopy(nums1, 0, sorted, 0, len);
        Arrays.sort(sorted);
        int res = 0;
        int maxn = 0;
        for (int i = 0; i < len; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            res += diff;
            if (res > mod) {
                res -= mod;
            }
            int n = binarySearch(sorted, nums2[i]);
            if (n < len) {
                maxn = Math.max(maxn, diff - Math.abs(sorted[n] - nums2[i]));
            }
            if (n > 0) {
                maxn = Math.max(maxn, diff - Math.abs(sorted[n - 1] - nums2[i]));
            }
        }
        return res - maxn < 0 ? res - maxn + mod : res - maxn;
    }

    public int binarySearch(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        if (nums[hi] < target) {
            return hi + 1;
        }
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        int lo = 1, hi = 5;
        int mid = lo + ((hi - lo) >> 1);
        System.out.println(mid);
    }

}

package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/array-nesting/
 * @Date: 17-07-2022 15:18
 */

public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = i, cnt = 0;
            while (nums[cur] != -1) {
                cnt++;
                int c = cur;
                cur = nums[cur];
                nums[c] = -1;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}

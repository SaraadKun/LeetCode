package com.saraad.leetcode.dailycode2022.november;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: <a href="https://leetcode.cn/problems/next-greater-element-i/">...</a>
 * @Date: 01-09-2022 00:36
 */

public class NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] ans = new int[m];
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            int cur = nums2[i];
            while (!stack.isEmpty() && stack.peek() <= cur) {
                stack.pop();
            }
            map.put(cur, stack.isEmpty() ? -1 : stack.peek());
            stack.push(cur);
        }
        for (int i = 0; i < m; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}

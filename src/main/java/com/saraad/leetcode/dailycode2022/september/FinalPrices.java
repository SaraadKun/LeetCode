package com.saraad.leetcode.dailycode2022.september;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: <a href="https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop/">...</a>
 * @Date: 01-09-2022 00:35
 */

public class FinalPrices {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int cur = prices[i];
            while (!stack.isEmpty() && stack.peek() > cur) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? cur : cur - stack.peek();
            stack.push(cur);
        }
        return ans;
    }
}

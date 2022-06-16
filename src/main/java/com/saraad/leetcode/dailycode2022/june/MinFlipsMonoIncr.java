package com.saraad.leetcode.dailycode2022.june;

import java.util.Arrays;

public class MinFlipsMonoIncr {

    public static void main(String[] args) {
//        MinFlipsMonoIncr obj = new MinFlipsMonoIncr();
//        String s = "11011";
//        int ans = obj.minFlipsMonoIncr(s);
//        System.out.println(ans);
        int i = 0;
        System.out.println(i);
        i &= i + 1;
        System.out.println(i);


    }

    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        if (n == 1) {
            return 0;
        }
        //表示每个位置及其左边1的个数
        int[] prefixSum = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1')
                prefixSum[i] = 1;
            if (i > 0)
                prefixSum[i] += prefixSum[i - 1];
        }
        int ans = n;
        for (int i = 0; i < n; i++) {
            int l = i == 0 ? 0 : prefixSum[i - 1];
            int r = n - i - 1 - (prefixSum[n - 1] - prefixSum[i]);
            ans = Math.min(ans, l + r);
        }
        return ans;
    }
}

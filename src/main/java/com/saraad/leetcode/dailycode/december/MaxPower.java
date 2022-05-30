package com.saraad.leetcode.dailycode.december;

public class MaxPower {
    public int maxPower(String s) {
        int res = 1;
        int cur = 1;
        char pre = '0';
        for(int i = 0; i < s.length(); i++) {
            if (pre == s.charAt(i)) {
                cur++;
                res = Math.max(res, cur);
            } else {
                cur = 1;
                pre = s.charAt(i);
            }
        }
        return res;
    }
}

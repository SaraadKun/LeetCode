package com.saraad.leetcode.dailycode.may;

public class Reverse {

    public int reverse(int x) {
        int res = 0;
        while (x > 0) {
            int i = x % 10;
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE) {
                return 0;
            }
            res = res * 10 + i;
            x /= 10;
        }
        return res;
    }

}

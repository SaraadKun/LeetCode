package com.saraad.leetcode.dailycode.april;

public class IsScramble {

    public boolean isScramble(String s1, String s2) {

        return false;
    }

    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        if ("".equals(haystack)) {
            return -1;
        }

        return -1;
    }

    public int numDecodings(String s) {
        int n = s.length();
        if (s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            char ch1 = s.charAt(i - 2);
            char ch2 = s.charAt(i - 1);
            //ch2 != '0'
            if (ch2 >= '1' && ch2 <= '9') {
                if (ch1 == '1' || (ch1 == '2' && ch2 >= '1' && ch2 <= '6')) {
                    dp[i] = dp[i - 2] + dp[i - 1];
                } else {
                    dp[i] = dp[i - 1];
                }
            } else { //ch2 == '0'
                if (ch1 == '1' || ch1 == '2') {
                    dp[i] = dp[i - 2];
                } else {
                    return 0; //非法字符 00 30 40...
                }
            }
        }
        return dp[n];
    }

    public int numDecodings2(String s) {
        int n = s.length();
        if (s.charAt(0) == '0') {
            return 0;
        }
        long lo = 1, hi = 1;
        for (int i = 1; i < n; i++) {
            char ch1 = s.charAt(i - 1);
            char ch2 = s.charAt(i);
            //ch2 != '0'
            if (ch2 >= '1' && ch2 <= '9') {
                if (ch1 == '1' || (ch1 == '2' && ch2 >= '1' && ch2 <= '6')) {
                    long dp = lo + hi;
                    lo = hi;
                    hi = dp;
                } else {
                    lo = hi;
                }
            } else { //ch2 == '0'
                if (ch1 == '1' || ch1 == '2') {
                    long dp = lo;
                    lo = hi;
                    hi = dp;
                } else {
                    return 0; //非法字符 00 30 40...
                }
            }
        }
        return (int) (hi % Math.pow(10, 9) + 7);
    }

}

package com.saraad.leetcode.dailycode2022.august;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/longest-palindromic-substring/
 * @Date: 02-08-2022 21:48
 */

public class LongestPalindrome {

    //s.length() >= 1
    public String longestPalindrome(String s) {
        int n = s.length(), max = 1, lo = 0, hi = 0;
        char[] chs = s.toCharArray();
        //f[i][j](j >= i) 表示s[i..j]是否是回文串
        boolean[][] f = new boolean[n][n];
        //预处理单个字符和相邻两个字符的情况
        // 对于单个字符: f[i][i] = true,即单个字符均为回文串
        // 对于相邻两个字符: f[i][i+1] = chs[i] == chs[i+1]
        for (int i = 0; i < n; i++) {
            f[i][i] = true;
            if (i < n - 1) {
                f[i][i + 1] = chs[i] == chs[i + 1];
                if (f[i][i + 1] && max < 2) {
                    max = 2;
                    lo = i;
                    hi = i + 1;
                }
            }
        }
        for (int diff = 2; diff < n; diff++) {
            //从三个字符的相邻字符串开始枚举
            for (int i = 0; i < n; i++) {
                int j = i + diff;
                if (j < n) {
                    if (chs[i] == chs[j]) {
                        f[i][j] = f[i + 1][j - 1]; // chs[i] == chs[j],就看[i+1, j-1]是否是回文串
                        if (f[i][j] && diff + 1 > max) { //更新最大长度及坐标
                            max = diff + 1;
                            lo = i;
                            hi = j;
                        }
                    }
                }
            }
        }
        return s.substring(lo, hi + 1);
    }

    public static void main(String[] args) {
        LongestPalindrome lp = new LongestPalindrome();
//        String s = "babad";
        String s = "cbbd";
        System.out.println(lp.longestPalindrome(s));
    }
}

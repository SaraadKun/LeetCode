package com.saraad.leetcode.dailycode.june;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

 * 示例:
 *
 * 输入：s = "abc"
 * 输出 ["abc","acb","bac","bca","cab","cba"]
 *
 *
 * 1 <= s 的长度 <= 8
 */
public class Permutation {

    private List<String> list = new ArrayList<>();

    public String[] permutation(String s) {
        int len = s.length();
        int resLen = 1;
        for (int i = 1; i <= len; i++) {
            resLen *= i;
        }
        for (int i = 0; i < len; i++) {
            char[] tmp = new char[len];
            tmp[0] = s.charAt(i);
            find(tmp, s, 0, i);
        }

        return list.toArray(new String[resLen]);
    }

    private void find(char[] tmp, String s, int i, int i1) {
//        Integer.bitCount();
    }
}

package com.saraad.leetcode.dailycode;

/**
 * @Title: ReverseWords
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/5/7 5:18 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class ReverseWords {

    public String reverseWords(String s) {
        s = s.trim();
        int space = s.indexOf(" ");
        if (space == -1) {
            return s;
        }
        char[] chs = s.toCharArray();
        reverse(chs, 0, chs.length - 1);
        int lo = 0;
        for (int i = 0; i < chs.length; i++){
            if (chs[i] == ' '){
                reverse(chs, lo, i - 1);
                lo = i + 1;
            }
        }
        reverse(chs, lo, chs.length - 1);
        return new String(chs);
    }

    public void reverse(char[] chs, int start, int end) {
        while (start < end) {
            char tmp = chs[start];
            chs[start] = chs[end];
            chs[end] = tmp;
            start++;
            end--;
        }
    }

}

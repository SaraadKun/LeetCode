package com.saraad.leetcode.dailycode2022.august;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 11-08-2022 00:33
 */

public class ReFormatString {
    public String reformat(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length, cnt = 0;
        for (char ch : chs) {
            if (ch >= 'a' && ch <= 'z') {
                cnt++;
            }
        }
        if (cnt != n >> 1 && cnt != (n + 1) >> 1) {
            return "";
        }
        int i = 0, j = 0;
        char[] letters = new char[cnt], nums = new char[n - cnt];
        for (char ch : chs) {
            if (ch >= 'a' && ch <= 'z') {
                letters[i++] = ch;
            } else {
                nums[j++] = ch;
            }
        }
        if ((n & 1) == 1 && cnt > n - cnt) {
            i = 0;
            j = 1;
        } else {
            j = 0;
            i = 1;
        }
        for (int idx = 0; idx < letters.length; idx++, i += 2) {
            chs[i] = letters[idx];
        }
        for (int idx = 0; idx < nums.length; idx++, j += 2) {
            chs[j] = nums[idx];
        }
        return new String(chs);
    }

    public static void main(String[] args) {
        String str = "a0b1c22";
        System.out.println(new ReFormatString().reformat(str));
    }
}

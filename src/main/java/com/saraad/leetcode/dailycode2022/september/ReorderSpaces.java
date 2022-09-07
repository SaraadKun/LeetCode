package com.saraad.leetcode.dailycode2022.september;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 07-09-2022 12:18
 */

public class ReorderSpaces {
    public String reorderSpaces(String text) {
        int n = text.length(), cw = 0, cs = 0;
        char[] chs = text.toCharArray(), ans = new char[n];
        char pre = '\001';
        for (int i = 0; i < n; i++) {
            if (chs[i] == ' ') {
                cs++;
            } else if (i == 0 || pre == ' ') {
                cw++;
            }
            pre = chs[i];
        }
        int k = cw == 1 ? cs : cs / (cw -  1), idx = 0;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (chs[i] == ' ') {
                if (flag) {
                    for (int j = 0; j < k && idx < n; j++) {
                        ans[idx++] = ' ';
                    }
                }
                flag = false;
            } else if (chs[i] != ' ') {
                ans[idx++] = chs[i];
                flag = true;
            }
        }
        while (idx < n) {
            ans[idx++] = ' ';
        }
        return new String(ans);
    }

    public static void main(String[] args) {
        ReorderSpaces obj = new ReorderSpaces();
        String text = "  this   is  a sentence ";
        String ans = obj.reorderSpaces(text);
        System.out.println(ans);
    }
}

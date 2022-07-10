package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 10-07-2022 00:03
 */

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        int ans = 0, n = Integer.MAX_VALUE;
        for (String str : strs) {
            if ("".equals(str)) {
                return "";
            }
            n = Math.min(n, str.length());
        }
        boolean flag = true;
        for (; flag && ans < n; ans++) {
            for (int i = 1; i < strs.length; i++) {
                if (strs[i - 1].charAt(ans) != strs[i].charAt(ans)) {
                    flag = false;
                    break;
                }
            }
        }
        return strs[0].substring(0, flag ? ans : ans - 1);
    }

    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
//        String[] strs = {"flower", "flow", "flight"};
        String[] strs = {"ab", "a"};
        System.out.println(lcp.longestCommonPrefix(strs));
    }
}

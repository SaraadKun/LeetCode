package com.saraad.leetcode.dailycode.april;

/**
 * 5 最长回文子串
 */
public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        //i < j <= len(s)
        //dp[i][j] 表示s[i:j] (包含i,包含j) 是否为回文串
        //边界条件 1.i==j时,单字符必定为回文串; 2.i==j-1 && s[i]==s[j],相邻相同的字符串为回文
        //状态转移方程
        //  s[i] == s[j] && dp[i][j-1]==true,dp[i][j]==true
        // else dp[i][j] == false
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int[] res = {0, 0};
        //j = i+l  l:子字符串长度最长为len
        for (int l = 1; l < len; l++) {
            for (int i = 0; i + l < len; i++) {
                int j = i + l;
                //内部为回文,两端相同则为回文串  特殊的,相邻的相同字符组成的子串为回文串
                if (s.charAt(i) == s.charAt(j) && (l == 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    //更新最长回文串
                    if (j - i > res[1] - res[0]) {
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }
        return s.substring(res[0], res[1] + 1);
    }

    public static void main(String[] args) {
        String s = "aacabdkacaa";
        String s1 = longestPalindrome(s);
        System.out.println(s1);
    }
}

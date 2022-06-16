package com.saraad.leetcode.dailycode2022.june;

import java.util.*;

public class LongestStrChain {

    public static void main(String[] args) {
        LongestStrChain obj = new LongestStrChain();
//        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        String[] words = {"a","b","ba","fbdca","bca","ca","c","bda","bdca"};
        System.out.println(obj.longestStrChain(words));
    }
    public int longestStrChain(String[] words) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] dp = new int[words.length];
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            int n = words[i].length();
            List<Integer> list = map.getOrDefault(n - 1, new ArrayList<>());
            int max = 0;
            for (int idx : list) {
                if (check(words[idx], words[i])) {
                    max = Math.max(max, dp[idx]);
                }
            }
            dp[i] = max + 1;
            List<Integer> cur = map.get(n);
            if (cur == null) {
                cur = new ArrayList<>();
                map.put(n, cur);
            }
            cur.add(i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    boolean check(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m + 1 != n)
            return false;
        int i = 0, j = 0, cnt = 0;
        while (i < m && j < n) {
            if (word1.charAt(i) == word2.charAt(j))
                i++;
            else
                cnt++;
            j++;
        }
        if (cnt == 0)
            j++;
        return i == m && j == n;
    }
}

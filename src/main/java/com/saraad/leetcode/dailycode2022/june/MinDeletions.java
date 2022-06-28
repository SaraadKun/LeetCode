package com.saraad.leetcode.dailycode2022.june;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/minimum-deletions-to-make-character-frequencies-unique/
 * @Date: 28-06-2022 20:52
 */

public class MinDeletions {
    public int minDeletions(String s) {
        int ans = 0;
        int[] cnts = new int[26];
        Map<Integer, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray())
            cnts[ch - 'a']++;
        for (int cnt : cnts) {
            int key = cnt;
            while (key > 0 && map.containsKey(key))
                key--;
            map.put(key, 1);
            ans += cnt - key;
        }
        return ans;
    }

    public static void main(String[] args) {
        MinDeletions md = new MinDeletions();
        assert md.minDeletions("ab") == 1;
        assert md.minDeletions("abc") == 2;
    }
}

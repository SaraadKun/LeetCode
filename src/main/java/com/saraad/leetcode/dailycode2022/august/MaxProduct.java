package com.saraad.leetcode.dailycode2022.august;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/aseY1I/
 * @Date: 15-08-2022 00:36
 */

public class MaxProduct {
    public int maxProduct(String[] words) {
        int n = words.length, ans = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                int key = words[i].charAt(j) - 'a';
                Set<Integer> set = map.getOrDefault(key, new HashSet<>());
                set.add(i);
                map.put(key, set);
            }
        }
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            int len = words[i].length();
            for (int j = 0; j < len; j++) {
                set.addAll(map.get(words[i].charAt(j) - 'a'));
            }
            for (int k = i + 1; k < n; k++) {
                if (!set.contains(k)) {
                    ans = Math.max(ans, len * words[k].length());
                }
            }
        }
        return ans;
    }
}

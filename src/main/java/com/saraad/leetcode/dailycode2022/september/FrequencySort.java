package com.saraad.leetcode.dailycode2022.september;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: <a href="https://leetcode.cn/problems/sort-characters-by-frequency/">...</a>
 * @Date: 01-09-2022 01:06
 */

public class FrequencySort {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        final StringBuilder sb = new StringBuilder();
        map.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .forEach(e -> sb.append(String.valueOf(e.getKey()).repeat(Math.max(0, e.getValue()))));
        return sb.toString();
    }
}

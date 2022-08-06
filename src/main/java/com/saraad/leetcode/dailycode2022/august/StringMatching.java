package com.saraad.leetcode.dailycode2022.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: <a href="https://leetcode.cn/problems/string-matching-in-an-array/">...</a>
 * @Date: 06-08-2022 02:07
 */

public class StringMatching {
    public List<String> stringMatching(String[] words) {
        List<String> ans = new ArrayList<>();
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if (words[j].contains(words[i])) {
                    ans.add(words[i]);
                    break;
                }
            }
        }
        return ans;
    }
}

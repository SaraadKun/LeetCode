package com.saraad.leetcode.dailycode2022.june;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/longest-uncommon-subsequence-ii/
 * @Date: 27-06-2022 00:44
 */

public class FindLUSlength {

    public int findLUSlength(String[] strs) {
        //输入数组按长度降序排序，从最大长度的字符串开始，检查当前字符串是否为特殊序列，若是则直接返回当前长度
        //检查逻辑为：
        //  1.对于当前str，若数组中有重复的元素则不符合要求；
        //  2.从已遍历过的长度不小于str.length()的字符串集合中进行筛查，若当前字符串为其子序列则不符合要求；
        //  3. 1.2.两步筛查后仍然未排除的str，即为最长特殊序列（之一）
        Arrays.sort(strs, ((o1, o2) -> o2.length() - o1.length()));
        Set<String> set = new HashSet<>();
        int idx = 0, len = strs[0].length();
        for (int i = len; i > 0; i--) {
            Map<String, Integer> map = new HashMap<>();
            while (idx < strs.length && strs[idx].length() == i) {
                map.put(strs[idx], map.getOrDefault(strs[idx], 0) + 1);
                idx++;
            }
            out:
            for (String s : map.keySet()) {
                if (map.get(s) == 1) {
                    for (String str : set) {
                        if (check(str, s)) {
                            continue out;
                        }
                    }
                    return i;
                }
                set.add(s);
            }
        }
        return -1;
    }

    private boolean check(String str, String s) {
        int n = str.length(), m = s.length(), i = 0, j = 0;
        while (i < n && j < m) {
            if (str.charAt(i) == s.charAt(j))
                j++;
            i++;
        }
        return j == m;
    }

    public static void main(String[] args) {
        FindLUSlength findLUSlength = new FindLUSlength();
        String[] strs = {"aba", "cdc", "eae"};
        assert Objects.equals(findLUSlength.findLUSlength(strs), 3);
   }
}

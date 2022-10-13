package com.saraad.leetcode.dailycode2022.october;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/number-of-valid-words-for-each-puzzle/
 * @Date: 12-10-2022 21:13
 */

public class FindNumOfValidWords {

    //统计words中每个可能的谜底出现的次数
    Map<Integer, Integer> map = new HashMap<>();

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int n = words.length, m = puzzles.length;
        List<Integer> ans = new ArrayList<>();
        //预处理words,每个单词都处理成一个int值,对应字母的位置为1,同时将处理后的谜底用哈希表存储并统计次数
        for (int i = 0; i < n; i++) {
            int b = 0;
            for (char ch : words[i].toCharArray()) {
                b |= 1 << ch - 'a';
            }
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        //遍历谜面,对每个谜面,枚举所有可能的谜底,并从map中查找对应谜底出现的次数
        for (int i = 0; i < m; i++) {
            ans.add(backtrack(puzzles[i].toCharArray(), 0, 0, true));
        }
        return ans;
    }

    /**
     * @Description:  回溯枚举谜面puzzles[i]除首位字符外的每一位是否存在的情况,即枚举所有可能得谜底
     * @param chs 谜面的字符数组
     * @param idx 当前处理的索引位置
     * @param b 当前可能得谜底
     * @param flag 是否选择当前位置字符
     * @return 当前路径所能够产生的谜底在words[i]中出现的次数
     */
    private int backtrack(char[] chs, int idx, int b, boolean flag) {
        if (flag) {
            b |= 1 << chs[idx] - 'a';
        }
        if (idx == 6) {
            return map.getOrDefault(b, 0);
        }
        return backtrack(chs, idx + 1, b, true) + backtrack(chs, idx + 1, b, false);
    }
}

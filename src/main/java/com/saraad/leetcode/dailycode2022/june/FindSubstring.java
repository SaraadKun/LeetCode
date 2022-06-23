package com.saraad.leetcode.dailycode2022.june;

import com.saraad.util.JSONUtil;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
 * @Date: 23-06-2022 16:28
 */

public class FindSubstring {

    public static void main(String[] args) {
//        String s = "barfoothefoobarman";
//        String[] words = {"foo", "bar"};
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","word"};
//        String s = "barfoofoobarthefoobarman";
//        String[] words = {"bar","foo","the"};
        FindSubstring obj = new FindSubstring();
        List<Integer> res = obj.findSubstring(s, words);
//        assert Objects.equals(JSONUtil.writeValueAsString(res), "[0,9]");
        assert Objects.equals(JSONUtil.writeValueAsString(res), "[]");
//        assert Objects.equals(JSONUtil.writeValueAsString(res), "[6,9,12]");
    }

    public List<Integer> findSubstring(String s, String[] words) {
        //words.length = n, words[i].length = m,
        // 1.对于s,以步长m为间隔,查找words[i],每找到一个符合题意的位置,放入res中.对于words,使用map进行存储,key为word,value为出现的次数.
        // 2.对于1.的过程,总共需要查找m轮,起始遍历位置为[0,m)
        int n = words.length, m = words[0].length();
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            //初始化字典
            map = initMap(words, map);
            int k = i, j = k;
            while (j <= s.length() - m) {
                String word = s.substring(j, j + m);
                if (!map.containsKey(word)) {
                    map = initMap(words, map);
                    k += m;
                    j = k;
                    continue;
                }
                map.put(word, map.get(word) - 1);
                if (map.get(word) == 0) {
                    map.remove(word);
                }
                if (map.isEmpty()) {
                    res.add(k);
                    j = k;
                    k += m;
                    map = initMap(words, map);
                }
                j += m;
            }
        }
        return res;
    }

    Map<String, Integer> initMap(String[] words, Map<String, Integer> map) {
        map.clear();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }


}

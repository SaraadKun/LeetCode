package com.saraad.leetcode.dailycode;

import java.util.*;

/**
 * @Title: TopKFrequent
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/5/20 4:05 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class TopKFrequent {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((p1, p2) -> p1.getValue().equals(p2.getValue())
                ? p1.getKey().compareTo(p2.getKey()) : p2.getValue() - p1.getValue());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(pq.poll().getKey());
        }
        return ans;
    }

}

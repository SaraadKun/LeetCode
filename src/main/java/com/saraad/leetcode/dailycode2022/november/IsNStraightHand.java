package com.saraad.leetcode.dailycode2022.november;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: <a href="https://leetcode.cn/problems/hand-of-straights/">...</a>
 * @Date: 01-09-2022 01:58
 */

public class IsNStraightHand {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            pq.offer(num);
        }
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            if (map.get(cur) == 0) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                int cnt = map.getOrDefault(cur, 0);
                if (cnt == 0) {
                    return false;
                }
                map.put(cur, cnt - 1);
                cur++;
            }
        }
        return true;
    }
}

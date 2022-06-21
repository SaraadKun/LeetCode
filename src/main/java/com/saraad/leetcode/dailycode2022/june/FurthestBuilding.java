package com.saraad.leetcode.dailycode2022.june;

import java.util.PriorityQueue;

/**
 * @Description: desc
 * @Author: saraadpeng
 * @Link: https://leetcode.cn/problems/furthest-building-you-can-reach/
 * @Date: 21-06-2022 16:32
 */

public class FurthestBuilding {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int sum = 0, n = heights.length;
        for (int i = 1; i < n; i++){
            int diff = heights[i] - heights[i - 1];
            if (diff > 0) {
                pq.offer(diff);
                if (pq.size() > ladders) {
                    sum += pq.poll();
                }
                if (sum > bricks) {
                    return i - 1;
                }
            }
        }
        return n - 1;
    }

}

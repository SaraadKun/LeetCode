package com.saraad.leetcode.dailycode2022.june;

import java.util.PriorityQueue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.com/problems/kth-largest-element-in-an-array/
 * @Date: 22-06-2022 17:10
 */

public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        //小根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i< nums.length; i++) {
            if (i < k) {
                pq.offer(nums[i]);
            } else {
                if (nums[i] > pq.peek()) {
                    pq.poll();
                    pq.offer(nums[i]);
                }
            }
        }
        return pq.poll();
    }

    public static void main(String[] args) {
        FindKthLargest obj = new FindKthLargest();
        int[] nums = {3,2,1,5,6,4};
        assert obj.findKthLargest(nums, 2) == 5;
        assert obj.findKthLargest(nums, 4) == 3;
    }

}

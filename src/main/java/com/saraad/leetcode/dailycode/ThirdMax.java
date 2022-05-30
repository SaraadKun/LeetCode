package com.saraad.leetcode.dailycode;

import java.util.*;

public class ThirdMax {

    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Map<Integer, Boolean> map = new HashMap<>();
        for(int n : nums) {
            if (map.get(n) == null) {
                pq.offer(n);
            }
            map.put(n, true);
        }
        if (pq.size() < 3) {
            return  pq.poll();
        }
        int res = 0;
        for (int i = 0; i < 3; i++) {
            res = pq.poll();
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {2,2,3,1};
//        ThirdMax instance = new ThirdMax();
//        int ans = instance.thirdMax(arr);
//        System.out.println(ans);
//        String s = "    foo    bar";
        String s = " ";
        String[] split = s.trim().split("\\s+");
        System.out.println(split.length);
        System.out.println("".equals(split[0]));
        System.out.println(Arrays.stream(s.split("\\s+")).filter(str -> !"".equals(str)).count());

    }

}

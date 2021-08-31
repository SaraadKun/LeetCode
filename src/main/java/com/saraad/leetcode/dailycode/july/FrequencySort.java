package com.saraad.leetcode.dailycode.july;

import java.util.*;
import java.util.stream.Collectors;

public class FrequencySort {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = map.getOrDefault(s.charAt(i), 0) + 1;
            map.put(s.charAt(i),cur);
            max = Math.max(max, cur);
        }
        StringBuffer[] buckets = new StringBuffer[max + 1];
        for (int i = 0; i <= max; i++) {
            buckets[i] = new StringBuffer();
        }
        map.forEach((k, v) -> buckets[v].append(k));
        StringBuffer sb = new StringBuffer();
        for (int i = buckets.length - 1; i > 0; i--) {
            for (int j = 0; j < buckets[i].length(); j++) {
                for (int k = 0; k < i; k++) {
                    sb.append(buckets[i].charAt(j));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        int[] nums = {1,2,2,4};
        int[] nums = {1,1};
        int[] buckets = new int[nums.length + 1];
        for(int num : nums) {
            buckets[num] += 1;
        }
        int[] res = new int[2];
        for(int i = 1; i < buckets.length; i++) {
            if (buckets[i] < 1) {
                res[1] = i;
            }
            if(buckets[i] > 1) {
                res[0] = i;
            }
        }
        System.out.println(res[0] + ":" + res[1]);
    }
}

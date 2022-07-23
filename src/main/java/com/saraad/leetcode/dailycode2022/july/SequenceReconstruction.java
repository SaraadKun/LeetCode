package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/ur2n8P/
 * @Date: 23-07-2022 01:07
 */

public class SequenceReconstruction {

    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        //拓扑排序,若排序后与nums不一致(顺序,长度;有环无环情况均包含),则false
        //遍历sequences,统计各元素入度,每轮BFS入度为0的元素入队,
        //若当前队列元素数量大于1,则存在不止一个最短超序列,返回false,否则校验当前元素是否满足要求
        //循环结束判断已校验过的元素和nums.length是否相同,不同返回false
        int n = nums.length;
        int[] cnts = new int[n + 1];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] seq: sequences) {
            for (int i = 1; i < seq.length; i++) {
                cnts[seq[i]]++;
                adj.get(seq[i - 1]).add(seq[i]);
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (cnts[i] == 0) {
                q.offer(i);
            }
        }
        int idx = 0;
        while (!q.isEmpty()) {
            //若当前队列元素数量大于1,则存在不止一个最短超序列,返回false;
            if (q.size() > 1) {
                return false;
            }
            int cur = q.poll();
            //校验当前元素和nums序列
            if (cur != nums[idx++]) {
                return false;
            }
            for (int ne: adj.get(cur)) {
                if (--cnts[ne] == 0) {
                    q.offer(ne);
                }
            }
        }
        return idx == n;
    }

    public static void main(String[] args) {
        SequenceReconstruction sr = new SequenceReconstruction();
//        int[] nums = {1,2,3};
//        int[][] sequences = {{1,2},{1,3},{2,3}};
//        int[][] sequences = {{1,2},{2,3},{3, 1}};
        // [10,5,1,8,7,4,3,2,6,9]
        //[[7,4],[4,3,2,6,9],[10,5,1,8,7]]
        int[] nums = {10,5,1,8,7,4,3,2,6,9};
        int[][] sequences = {{7,4},{4,3,2,6,9},{10,5,1,8,7}};
        System.out.println(sr.sequenceReconstruction(nums, sequences));
    }
}

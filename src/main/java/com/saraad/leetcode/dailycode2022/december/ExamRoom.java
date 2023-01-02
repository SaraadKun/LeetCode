package com.saraad.leetcode.dailycode2022.december;

import java.util.PriorityQueue;
import java.util.TreeMap;

class ExamRoom {

    TreeMap<Integer, Boolean> map = new TreeMap<>();
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
    int N;

    public ExamRoom(int n) {
        this.N = n;
    }
    
    public int seat() {
        int sz = map.size();
        if (sz == 0) {
            map.put(0, true);
            pq.offer(new int[]{N, N - 1});
            return 0;
        } else {
            int[] arr = pq.poll();
            Integer lo = map.lowerKey(arr[1]), hi = map.higherKey(arr[1]);
            if (lo != null && arr[1] - lo > 1) {
                pq.offer(new int[]{arr[1] - lo - 1, (arr[1] + lo) >> 1});
            }
            if (hi != null && hi - arr[1] > 1) {
                pq.offer(new int[]{hi - arr[1] - 1, (arr[1] + hi) >> 1});
            }
            map.put(arr[1], true);
            return arr[1];
        }
    }
    
    public void leave(int p) {
        int sz = map.size();
        if (sz > 1) {
            Integer lo = map.lowerKey(p), hi = map.higherKey(p);
            lo = lo == null ? 0 : lo;
            hi = hi == null ? N - 1 : hi;
            pq.offer(new int[]{hi - lo - 1, (hi + lo) >> 1});
        }
        map.remove(p);
    }

    public static void main(String[] args) {
        ExamRoom obj = new ExamRoom(10);
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        obj.leave(4);
        System.out.println(obj.seat());
    }
}
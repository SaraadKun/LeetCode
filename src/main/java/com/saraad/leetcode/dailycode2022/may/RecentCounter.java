package com.saraad.leetcode.dailycode2022.may;



import java.util.*;

class RecentCounter {

    private int[] seq;

    private int oldest;

    private int lastest;

    private boolean flag;

    public RecentCounter() {
        seq = new int[3003];
        oldest = 0;
        lastest = 0;
    }
    
    public int ping(int t) {
        if (lastest == 3003) {
            lastest = 0;
            flag = !flag;
        }
        seq[lastest++] = t;
        int target = t - 3000;
        while (seq[oldest] < target && flag ^ (oldest < lastest)){
            oldest++;
            if (oldest == 3003) {
                oldest = 0;
                flag = !flag ;
            }
        }
        return flag ? lastest + 3003 - oldest : lastest - oldest;
    }

    public static void main(String[] args) {
        RecentCounter instance = new RecentCounter();
        int[] data = {1,2,3,100,3001,3002,3003,3004,3005};
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
//        for (int t : data) {
//            sb1.append(instance.ping(t));
//            sb2.append(instance2.ping(t));
//        }
        Queue<String> queue = new ArrayDeque<String>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        String str = list.toString();


    }
}
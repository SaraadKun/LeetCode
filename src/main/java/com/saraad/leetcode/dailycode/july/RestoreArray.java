package com.saraad.leetcode.dailycode.july;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class RestoreArray {

    public int[] restoreArray(int[][] adjacentPairs) {
        //首尾元素必然只出现1次 使用字典对元素计数获取任意一个头尾元素
        Map<Integer, Integer> cnts = new HashMap<>();
        Map<Integer, Integer> dict1 = new HashMap<>();
        Map<Integer, Integer> dict2 = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            cnts.put(pair[0], cnts.getOrDefault(pair[0], 0) + 1);
            cnts.put(pair[1], cnts.getOrDefault(pair[1], 0) + 1);
            if (!dict1.containsKey(pair[0])) {
                dict1.put(pair[0], pair[1]);
            } else {
                dict2.put(pair[0], pair[1]);
            }
            if (!dict2.containsKey(pair[1])) {
                dict2.put(pair[1], pair[0]);
            } else {
                dict1.put(pair[1], pair[0]);
            }
        }
        Integer head = cnts.entrySet().stream().filter(o -> o.getValue() == 1).findFirst().get().getKey();
        int n = adjacentPairs.length;
        int[] res = new int[n + 1];
        res[0] = head;
        res[1] = dict1.get(head) == null ? dict2.get(head) : dict1.get(head);;
        for (int i = 2; i <= n; i++) {
            Integer cur = dict1.get(res[i - 1]);
            if (cur == null || cur == res[i - 2]) {
                cur = dict2.get(res[i - 1]);
            }
            res[i] = cur;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 1000000000;
        int a = 2;
        int b = 217983653;
        int c = 336916467;
        int res = nthUglyNumber(n, a, b, c);
        System.out.println(res);
//        System.out.println(gcd(12, 8));
//        System.out.println(lcm(12, 8));
    }
    public static int nthUglyNumber(int n, int a, int b, int c) {
        //[0, num] 范围内丑数个数为 num/a + num/b + num/c - num/lcm(ab) - num/lcm(ac) -  num/lcm(bc) + num/lcm(abc)
        long lab = lcm(a, b);
        long lac = lcm(a, c);
        long lbc = lcm(b, c);
        long labc = lcm(lab, c);
        int min = Math.min(Math.min(a, b), c);
        long lo = 0;
        long hi = (long)Math.min(min * n, 2 * 1e9);
        while (lo <= hi) {
            long mid = (hi - lo) / 2 + lo;
            long cur = mid/a + mid/b + mid/c - mid/lab - mid/lac - mid/lbc + mid/labc;
            if (cur < n) {
                lo = mid + 1;
            } else{
                hi = mid - 1;
            }
        }
        return (int)lo;
    }

    public static long lcm(long a, long b) {
        long gcd = gcd(a, b);
        return a * b / gcd;
    }


    public static long gcd(long a, long b) {
        if (a < b) {
            long tmp = a;
            a = b;
            b = tmp;
        }
        if (a % b != 0) {
            return gcd(b, a % b);
        }
        return b;
    }
}

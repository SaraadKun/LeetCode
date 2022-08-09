package com.saraad.leetcode.dailycode2022.august;

import com.saraad.util.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 09-08-2022 16:19
 */

public class FindNthDigit {

    private static TreeMap<Integer,int[]> map = new TreeMap<>();
    static {
        long j = 10;
        for (int i = 2; j < Integer.MAX_VALUE; i++) {
            int m = (int) Math.pow(10, i - 1);
            map.put((int)j, new int[]{i, m});
            j += (long)i * 9 * m;
        }
    }
    public int findNthDigit (int n){
        if (n < 10) {
            return n;
        }
        Map.Entry<Integer, int[]> entry = map.floorEntry(n);
        int offset = entry.getKey(), start = entry.getValue()[1], m = entry.getValue()[0];
        int t = start + (n - offset) / m, b = (n - offset) % m;
        while (b < m - 1) {
            t /= 10;
            b--;
        }
        return t % 10;
    }

    public static void main(String[] args) {
        FindNthDigit obj = new FindNthDigit();
        int n = 23453;
        System.out.println(obj.findNthDigit(n));
    }

}

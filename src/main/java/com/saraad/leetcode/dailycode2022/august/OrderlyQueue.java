package com.saraad.leetcode.dailycode2022.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/orderly-queue/
 * @Date: 03-08-2022 15:54
 */

public class OrderlyQueue {

    public String orderlyQueue(String s, int k) {
        char[] chs = s.toCharArray();
        if (k > 1) {
            Arrays.sort(chs);
            return new String(chs);
        }
        int idx = 0, n = chs.length;
        char min = chs[idx];
        for (int i = 1; i < n; i++) {
            if (chs[i] < min) {
                min = chs[i];
                idx = i;
            } else if (chs[i] == min) {
                if (check(i, idx, n, chs)) {
                    idx = i;
                }
            }
        }
        return s.substring(idx) + s.substring(0, idx);
    }

    private boolean check(int hi, int lo, int n, char[] chs) {
        while (lo < hi && chs[hi] == chs[lo]) {
            hi = hi == n - 1 ? 0 : hi + 1;
            lo++;
        }
        return hi < n && chs[hi] < chs[lo];
    }

    public static void main(String[] args) {
        OrderlyQueue orderlyQueue = new OrderlyQueue();
        String s = mock();
        System.out.println(orderlyQueue.orderlyQueue(s, 1));
    }

    private static String mock() {
        String s = "xitavoyjqiupzadbdyymyvuteolyeerecnuptghlzsynozeuuvteryojyokpufanyrqqmtgxhyycltlnusyeyyqygwupcaagtkuq";
        return s;
    }

}

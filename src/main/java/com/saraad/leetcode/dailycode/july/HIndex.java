package com.saraad.leetcode.dailycode.july;

import java.util.HashMap;

/**
 * 274. H 指数
 */
public class HIndex {

    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] counter = new int[n + 1];
        for (int c : citations) {
            if (c > n) {
                counter[n]++;
            } else {
                counter[c]++;
            }
        }
        int h = 0;
        for (int i = n; i >= 0; i--) {
            h += counter[i];
            if (h >= i) {
                return i;
            }
        }

        return 0;
    }
}

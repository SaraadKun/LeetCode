package com.saraad.leetcode.dailycode2022.june;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 27-06-2022 17:56
 */

public class MinPartitions {
    public int minPartitions(String n) {
        char[] chs = n.toCharArray();
//        char ans = '0';
        int ans = 0;
        for (char ch : chs) {
            ans = Math.max(ans, ch - '0');
        }
        return ans;
    }

    public static void main(String[] args) {
        MinPartitions mp = new MinPartitions();
        assert Objects.equals(mp.minPartitions("100"), 1);
    }
}

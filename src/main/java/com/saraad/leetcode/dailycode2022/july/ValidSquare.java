package com.saraad.leetcode.dailycode2022.july;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 29-07-2022 00:15
 */

public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        //根据x,y坐标从左到右从上到下对点排序,验证:
        // 1.四条边p1p2,p1p3,p2p4,p3p4是否相等; 并排除输入四点为同一点的情况
        // 2.若满足1,验证∠p1p2p4是否为直角, 即(p1p2)^2 + (p2p4)^2 = (p1p4)^2
        int[][] ps = Stream.of(p1, p2, p3, p4)
                .sorted((a, b) -> {
                    if (a[0] == b[0]) {
                        return a[1] - b[1];
                    }
                    return a[0] - b[0];
                }).toArray(int[][]::new);
        int d12 = distance(ps[0], ps[1]);
        int d13 = distance(ps[0], ps[2]);
        int d24 = distance(ps[1], ps[3]);
        int d34 = distance(ps[2], ps[3]);
        //若四条边相等,排除输入坐标为同一点的情况
        if (d12 != d13 || d12 != d24 || d12 != d34 || (p1[0] == p2[0] && p1[1] == p2[1])) {
            return false;
        }
        int d14 = distance(ps[0], ps[3]);
        return d12 + d24 == d14;
    }

    private int distance(int[] a, int[] b) {
        int x1 = a[0], y1 = a[1], x2 = b[0], y2 = b[1];
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    public static void main(String[] args) {
        ValidSquare validSquare = new ValidSquare();
        System.out.println(validSquare.validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{0, 1}));
    }
}

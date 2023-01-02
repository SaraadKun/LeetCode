package com.saraad.leetcode.dailycode2022.november;

import com.saraad.leetcode.utils.ArrayUtil;
import org.junit.Assert;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/coordinate-with-maximum-network-quality/
 * @Date: 02-11-2022 12:04
 */

public class BestCoordinate {

//    public int[] bestCoordinate(int[][] towers, int radius) {
//        int[][] pos = new int[101][101];
//        int rr = radius * radius, max = 0;
//        for (int[] tower : towers) {
//            int a = pos[3][33];
//            int b = 1;
//            for (int i = 0; i < 101; i++) {
//                for (int j = 0; j < 101; j++) {
//                    int x = tower[0] - i, y = tower[1] - j, dd = x * x + y * y;
//                    if (dd <= rr) {
//                        pos[i][j] += tower[2] / (1 + (int)Math.sqrt(dd));
//                        max = Math.max(max, pos[i][j]);
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < 101; i++) {
//            for (int j = 0; j < 101; j++) {
//                if (pos[i][j] == max) {
////                    return new int[]{i, j};
//                    System.out.println(i + ", " + j);
//                }
//            }
//        }
//        return new int[]{-1, -1};
//    }

    public static void main(String[] args) {
        BestCoordinate obj = new BestCoordinate();
        Assert.assertArrayEquals(new int[]{24, 45}, obj.bestCoordinate(ArrayUtil.mock2d("[[31,13,33],[24,45,38],[28,32,23],[7,23,22],[41,50,33],[47,21,3],[3,33,39],[11,38,5],[26,20,28],[48,39,16],[34,29,25]]"), 21));
    }

    public int[] bestCoordinate(int[][] towers, int k) {
        int[][] g = new int[110][110];
        int x = 0, y = 0, val = 0;
        for (int[] t : towers) {
            int aa = g[3][33];
            int a = t[0], b = t[1], q = t[2];
            for (int i = Math.max(0, a - k); i <= a + k; i++) {
                for (int j = Math.max(0, b - k); j <= b + k; j++) {
                    double d = Math.sqrt((a - i) * (a - i) + (b - j) * (b - j));
                    if (d > k) continue;
                    g[i][j] += Math.floor(q / (1 + d));
                    if (g[i][j] > val) {
                        x = i; y = j; val = g[i][j];
                    } else if (g[i][j] == val && (i < x || (i == x && j < y))) {
                        x = i; y = j;
                    }
                }
            }
        }
//        System.out.println(val);
//        System.out.println(g[3][33]);
//        System.out.println(g[x][y]);
        return new int[]{x, y};
    }
}

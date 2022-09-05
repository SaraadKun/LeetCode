package com.saraad.leetcode.dailycode2022.september;

import com.saraad.leetcode.utils.ArrayUtil;
import org.junit.Assert;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 04-09-2022 01:29
 */

public class NumSpecial {
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length, ans = 0;
        int[] rows = new int[m], cols = new int[n];
        Arrays.fill(rows, -1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    if (rows[i] == -1) {
                        rows[i] = j;
                    } else {
                        rows[i] = -2;
                    }
                    cols[j]++;
                }
            }
        }
        for (int r : rows) {
            if (r >= 0 && cols[r] == 1) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        NumSpecial obj = new NumSpecial();
        Assert.assertEquals(2, obj.numSpecial(ArrayUtil.mock("[[0,0,0,1],[1,0,0,0],[0,1,1,0],[0,0,0,0]]")));
        Assert.assertEquals(3, obj.numSpecial(ArrayUtil.mock("[[1,0,0],[0,0,1],[0,1,0]]")));
    }
}

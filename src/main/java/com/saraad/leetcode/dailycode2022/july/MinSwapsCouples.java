package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/couples-holding-hands/
 * @Date: 17-07-2022 15:18
 */

public class MinSwapsCouples {
    public int minSwapsCouples(int[] row) {
        //贪心,步长为2的遍历row,对于没个遍历到的元素 i,r,若i+1处不为r的情侣,则使其与r的情侣交换位置,注意维护情侣id与位置的关系
        int ans = 0, n = row.length;
        int[] mapping = new int[n];
        for (int i = 0; i < n; i++) {
            mapping[row[i]] = i;
        }
        for (int i = 0; i < n; i += 2) {
            if (row[i] / 2 == row[i + 1] / 2) {
                continue;
            }
            int next = row[i] ^ 1, nid = mapping[next];
            row[nid] = row[i + 1];
            row[i + 1] = next;
            mapping[row[nid]] = nid;
            mapping[next] = i + 1;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
//        MinSwapsCouples minSwapsCouples = new MinSwapsCouples();
//        int[] row = {0, 2, 4, 6, 7, 1, 3, 5};
//        System.out.println(minSwapsCouples.minSwapsCouples(row));
        int a = 5, b = 6;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        int c = a ^ b;
        int d = a ^ b ^ b;
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));
        System.out.println(d);
    }
}

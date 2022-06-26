package com.saraad.leetcode.dailycode2022.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 26-06-2022 17:41
 */

public class RandomPickWithBlacklist {

    Random rd = new Random();
    int[] prefixSum = new int[100005];
    List<int[]> list = new ArrayList<>();
    int size;

    public RandomPickWithBlacklist(int n, int[] blacklist) {
        Arrays.sort(blacklist);
        int m = blacklist.length;
        //初始化线段集合
        if (m == 0) {
            list.add(new int[]{0, n});
        } else {
            if (blacklist[0] != 0) {
                list.add(new int[]{0, blacklist[0]});
            }
            for (int i = 1; i < m; i++) {
                if (blacklist[i - 1] == blacklist[i] - 1) {
                    continue;
                }
                list.add(new int[]{blacklist[i - 1] + 1, blacklist[i]});
            }
            if (blacklist[m - 1] != n - 1) {
                list.add(new int[]{blacklist[m - 1] + 1, n});
            }
        }
        //初始化前缀和数组
        size = list.size();
        for (int i = 0; i < size; i++) {
            prefixSum[i + 1] = prefixSum[i] + list.get(i)[1] - list.get(i)[0];
        }
    }

    public int pick() {
        int rn = rd.nextInt(prefixSum[size]);
//        int rn = 1;
        //二分法查找定位随机值, list区间左闭右开,找到最后一个小于等于rn的值  小于等于,取lo
        int lo = -1, hi = size + 1;
        while (lo + 1 != hi) {
            int mid = (hi - lo) / 2 + lo;
            if (prefixSum[mid] <= rn) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return list.get(lo)[0] + rn - prefixSum[lo];
    }

    public static void main(String[] args) {
        int n = 7;
        int[] blacklist = new int[]{2, 3, 5};
        RandomPickWithBlacklist rpwb = new RandomPickWithBlacklist(n, blacklist);
        for (int i = 0; i < 17; i++) {
            System.out.println(rpwb.pick());
        }
    }

    //黑名单Hash映射解法
    //class Solution {
    //
    //    Random rd = new Random();
    //    Map<Integer, Integer> map = new HashMap<>();
    //    int total;
    //
    //    public Solution(int n, int[] blacklist) {
    //        int m = blacklist.length;
    //        total = n - m;
    //        Set<Integer> bs = new HashSet<>();
    //        int hi = total;
    //        for (int num : blacklist) {
    //            if (num >= total)
    //                bs.add(num);
    //        }
    //        for (int num : blacklist) {
    //            if (num < total){
    //                while(bs.contains(hi))
    //                    hi++;
    //                map.put(num, hi);
    //                hi++;
    //            }
    //        }
    //    }
    //
    //    public int pick() {
    //        int rn = rd.nextInt(total);
    //        return map.getOrDefault(rn, rn);
    //    }
    //}

}

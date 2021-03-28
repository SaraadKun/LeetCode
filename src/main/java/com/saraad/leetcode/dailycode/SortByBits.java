package com.saraad.leetcode.dailycode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: SortByBits
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2020/11/6 0:01
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class SortByBits {

    public int[] sortByBits(int[] arr) {
        if (arr==null || arr.length<2){
            return arr;
        }
        List<int[]> list = new ArrayList<>();
        for (int i : arr) {
            int count = count(i);
            int[] tuple = {i, count};
            list.add(tuple);
        }
        int[] sort = list.stream().sorted((t1, t2) -> t1[1] > t2[1] ? 1 : t1[1] < t2[1] ? -1 : 0)
                .mapToInt(tuple -> tuple[0]).toArray();
        return sort;
    }

    private int count(int i) {
        int count = 0;
        int num = i;
        while (num > 0){
            if ((num&1)==1){
                count++;
            }
            num>>=1;
        }
        return count;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            int count = 0;
            int num = i;
            while (num > 0){
                if ((num&1)==1){
                    count++;
                }
                num>>=1;
            }
            System.out.printf("%d 包含1的个数为%d\n", i, count);
        }


    }
}

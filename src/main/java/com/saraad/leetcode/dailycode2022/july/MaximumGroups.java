package com.saraad.leetcode.dailycode2022.july;

import java.util.Arrays;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 31-07-2022 10:41
 */

public class MaximumGroups {
    public int maximumGroups(int[] grades) {
        int n = grades.length, i = 2;
        while ((1 + i) * i / 2 < n) {
            i++;
        }
        if ((1 + i) * i / 2 == n) {
            return i;
        }
        return i - 1;
    }

    public static void main(String[] args) {
        int[] grades = {10,6,12,7,3,5};
        System.out.println(new MaximumGroups().maximumGroups(grades));
    }
}

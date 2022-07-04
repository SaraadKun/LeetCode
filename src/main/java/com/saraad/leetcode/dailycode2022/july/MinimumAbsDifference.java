package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/minimum-absolute-difference/
 * @Date: 04-07-2022 16:27
 */

public class MinimumAbsDifference {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i + 1] - arr[i];
            if (diff < min) {
                min = diff;
                res.clear();
                res.add(List.of(arr[i], arr[i + 1]));
            } else if (diff == min) {
                res.add(List.of(arr[i], arr[i + 1]));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinimumAbsDifference minimumAbsDifference = new MinimumAbsDifference();
        int[] arr = {3, -7, 0};
        System.out.println(minimumAbsDifference.minimumAbsDifference(arr));
    }
}

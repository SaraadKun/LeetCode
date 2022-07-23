package com.saraad.leetcode.dailycode2022.july;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 23-07-2022 21:00
 */

public class CanReorderDoubled {
    public boolean canReorderDoubled(int[] arr) {
        //按绝对值升序排序,遍历排序后的数组,记录每个元素的访问情况
        //对于每个元素,使用二分查找找到绝对值大小等于其2倍的元素区间,遍历该区间取第一个满足条件的数
        //若二分查找未找到目标元素,返回false,若遍历结束后有元素未被访问到,返回false;
        List<Integer>  list = Arrays.stream(arr).mapToObj(Integer::valueOf)
                .sorted((a, b) -> {
                    if (Math.abs(a) == Math.abs(b)) {
                        return a - b;
                    }
                    return Math.abs(a) - Math.abs(b);
                })
                .collect(Collectors.toList());
        int n = arr.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            int target = list.get(i) * 2;
            int lo = searchFirst(list, i, n, Math.abs(target)),
                    hi = searchLast(list, i, n, Math.abs(target));
            if (lo == n) {
                return false;
            }
            while (lo <= hi && (visited[lo] || list.get(lo) != target)) {
                lo++;
            }
            if (lo > hi) {
                return false;
            }
            visited[lo] = true;
        }
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    //找到第一个大于等于target的元素坐标
    private int searchFirst(List<Integer> arr, int lo, int hi, int target) {
        if (lo + 1 == hi) {
            return hi;
        }
        int mid = (hi - lo) / 2 + lo;
        if (Math.abs(arr.get(mid)) < target) {
            return searchFirst(arr, mid, hi, target);
        } else {
            return searchFirst(arr, lo, mid, target);
        }
    }

    //找到最后一个小于等于target的元素坐标
    private int searchLast(List<Integer> arr, int lo, int hi, int target) {
        if (lo + 1 == hi) {
            return lo;
        }
        int mid = (hi - lo) / 2 + lo;
        if (Math.abs(arr.get(mid)) <= target) {
            return searchLast(arr, mid, hi, target);
        } else {
            return searchLast(arr, lo, mid, target);
        }
    }

    public static void main(String[] args) {
        CanReorderDoubled canReorderDoubled = new CanReorderDoubled();
//        int[] arr = {4,-2,2,-4};
        int[] arr = {1,2,1,-8,8,-4,4,-4,2,-2};
        System.out.println(canReorderDoubled.canReorderDoubled(arr));
    }
}

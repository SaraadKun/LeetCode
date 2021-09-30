package com.saraad.leetcode.dailycode;

import com.alibaba.fastjson.JSON;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Title: SmallestK
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/9/3 10:03 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class SmallestK {

    public int[] smallestK(int[] arr, int k) {
        //使用快排思路处理
        int lo = 0, hi = arr.length - 1;
        while (true) {
            int j = partition(arr, lo, hi);
            if (j == k) break;
            if (j > k) hi = j - 1;
            if (j < k) lo = j + 1;
        }
        int[] res = new int[k];
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        SmallestK instance = new SmallestK();
        instance.fastSort(arr, 0, arr.length - 1);
        System.out.println(JSON.toJSONString(arr));
    }

    public void fastSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        //切分数组
        int j = partition(arr, lo, hi);
        //因为j处左侧均小于等于arr[j],右侧均大于等于arr[j],故j不需要作为边界
        fastSort(arr, lo, j - 1);
        fastSort(arr, j + 1, hi);
    }

    private int partition(int[] arr, int lo, int hi) {
        //取lo处的值作为切分依赖元素,对[lo + 1, hi]进行处理
        int i = lo;
        int j = hi + 1;
        while (true) {
            //边界条件,极端情况,[lo + 1, hi]全部大于v或者全部小于arr[lo],以及左右指针相遇
            while (arr[++i] < arr[lo]) if (i == hi) break;
            while (arr[--j] > arr[lo]) if (j == lo) break;
            if (j <= i) break;
            //交换左右元素,继续遍历
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

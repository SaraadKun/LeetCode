package com.saraad.leetcode.dailycode2022.june;

import com.saraad.util.JSONUtil;

public class DumplicateZeros {

    public static void main(String[] args) {
        DumplicateZeros obj = new DumplicateZeros();
//        int[] arr = {8,4,5,0,0,0,0,7};
        int[] arr = {0,0,0,0,0,0,0};
        System.out.println(JSONUtil.writeValueAsString(arr));
        obj.duplicateZeros(arr);
        System.out.println(JSONUtil.writeValueAsString(arr));
    }

    public void duplicateZeros(int[] arr) {
        int i = 0, j = 0;
        while (i < arr.length && j < arr.length) {
            if (arr[i] == 0)
                j++;
            if (j == arr.length)
                break;
            i++;
            j++;
        }
        int lo = i, hi = arr.length - 1;
        while(hi > lo) {
            if (arr[lo] == 0)
                arr[hi--] = 0;
            arr[hi--] = arr[lo--];
        }
    }
}

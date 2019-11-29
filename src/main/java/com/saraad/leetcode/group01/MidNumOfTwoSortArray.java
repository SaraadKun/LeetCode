package com.saraad.leetcode.group01;

/**
 * @Title: 寻找两个有序数组的中位数
 * @Package:com.saraad.leetcode.group01
 * @Description:
 * @author: saraad
 * @date: 2019/11/29 10:10 上午
 * @Copyright: 2019  Inc. All rights reserved.
 */
public class MidNumOfTwoSortArray {

    public static void main(String[] args) {
        int[] num1 = {};
        int[] num2 = {};
        double mid = findMedianSortedArrays(num1, num2);
        System.out.println(mid);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) return 0;
        int len1 = nums1 == null ? 0 : nums1.length;
        int len2 = nums2 == null ? 0 : nums2.length;
        if (len1 + len2 == 0) return 0;
        int[] aux = new int[len1 + len2];
        int i = 0, j = 0;
        for (int k = 0; k < aux.length; k++) {
            if (i >= len1) aux[k] = nums2[j++]; //num1数组用光使用num2数组
            else if (j >= len2) aux[k] = nums1[i++]; //num2数组用光使用num1数组
            //将较小的数归入aux中 正序排序
            else if (nums1[i] < nums2[j]) aux[k] = nums1[i++];
            else aux[k] = nums2[j++];
        }
        //奇数直接取中位数,偶数去中间两数的平均值
        if (aux.length % 2 == 1)
            return aux[aux.length/2];
        return (aux[aux.length/2 - 1] + aux[aux.length/2]) / 2.0;
    }

}

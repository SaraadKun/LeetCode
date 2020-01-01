package com.saraad.leetcode.group01;

/**
 * @Title: 寻找两个有序数组的中位数 要求时间复杂度O(log(m+n))
 * @Package:com.saraad.leetcode.group01
 * @Description:
 * @author: saraad
 * @date: 2019/11/29 10:10 上午
 * @Copyright: 2019  Inc. All rights reserved.
 */
public class MidNumOfTwoSortArray {

    public static void main(String[] args) {
        int[] num2 = {1,3};
        int[] num1 = {2,4};
        double mid = findMedianSortedArrays(num1,num2);
        System.out.println(mid);
    }

    public static double findMedianSortedArrays(int[] num1, int[] num2){
        int len1 = num1.length;
        int len2 = num2.length;
        int k1 = (len1 + len2 + 1)/2;
        int k2 = (len1 + len2 + 2)/2;
        //总长度为奇数会计算两次重复值
        return (findMedianSortedArrays(num1, 0, len1-1, num2, 0, len2-1, k1) +
                findMedianSortedArrays(num1, 0, len1-1, num2, 0, len2-1, k2))/2;
    };

    //二分法 第k小的数
    public static double findMedianSortedArrays(int[] nums1,int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让num1为短数组
        if (len1 > len2) return findMedianSortedArrays(nums2, start2, end2, nums1, start1, end1, k);
        //num1元素用光
        if (len1 == 0) return nums2[start2 + k - 1];
        if (k == 1) return min(nums1[start1], nums2[start2]);
        //比较两数组k/2处数大小,较小的数最大为第k-1小的数,故可排除k/2个数,构建新数组,重新确定k的值
        int index1 = start1 + min(len1, k/2) - 1;
        int index2 = start2 + min(len2, k/2) - 1;
        if (nums1[index1] > nums2[index2]){
            return findMedianSortedArrays(nums1, start1, end1, nums2, index2 + 1, end2, k - min(len2,k/2));
        }else {
            return findMedianSortedArrays(nums1, index1 + 1, end1, nums2, start2, end2, k - min(len1,k/2));
        }
    }

    public static int min(int n1, int n2){
        return n1 > n2 ? n2 : n1;
    }

    //归并排序merge O(m+n)
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
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

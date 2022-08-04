package com.saraad.leetcode.dailycode2022.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/orderly-queue/
 * @Date: 03-08-2022 15:54
 */

public class OrderlyQueue {

//    public String orderlyQueue(String s, int k) {
//        char[] chs = s.toCharArray();
//        if (k > 1) {
//            Arrays.sort(chs);
//            return new String(chs);
//        }
//        int idx = 0, n = chs.length;
//        char min = chs[idx];
//        for (int i = 1; i < n; i++) {
//            if (chs[i] < min) {
//                min = chs[i];
//                idx = i;
//            } else if (chs[i] == min) {
//                if (check(i, idx, n, chs)) {
//                    idx = i;
//                }
//            }
//        }
//        return s.substring(idx) + s.substring(0, idx);
//    }
//
//    private boolean check(int hi, int lo, int n, char[] chs) {
//        while (lo < hi && chs[hi] == chs[lo]) {
//            hi = hi == n - 1 ? 0 : hi + 1;
//            lo++;
//        }
//        return hi < n && chs[hi] < chs[lo];
//    }

    //最小表示法解法
    public String orderlyQueue(String s, int k) {
        char[] chs = s.toCharArray();
        if (k > 1) {
            Arrays.sort(chs);
            return new String(chs);
        }
        //最小表示法,维护两个指向字符串s的指针:
        // i 代表当前可以构成最小字符串的指针,初始i=0;
        // j 代表当前遍历到的位置范围为[i+1, n),初始j=i+1;
        //对于每一个j,从i,j开始依次向后比较字符大小,
        //1.若发现chs[i+m] > chs[j+m],则[i,i+m]位置作为开头的字符串都不是最优解,因为对于任意的p∈[i,i+m],都有q∈[j,j+m]比p更优,所以将i置为i+m+1;
        //2.同理,若chs[i+m] < chs[j+m],则可以排除范围[j,j+m],j = j+m+1;
        //3.若chs[i+m] == chs[j+m], 则k++;直到i,j,m任意一个值大于等于n为止,遍历结束后,i和j中的较小值即为正确结果
        int n = chs.length, i = 0, j = 1, m = 0;
        while (i < n && j < n && m < n) {
            char chi = chs[(i + m) % n], chj = chs[(j + m) % n];
            if (chi == chj) {
                m++;
            } else {
                if (chi > chj) {
                    i = i + m + 1;
                } else {
                    j = j + m + 1;
                }
                //保证i, j不相同
                if (i == j) {
                    j++;
                }
                m = 0; //重新定位后,比较的偏移量m重置为0
            }
        }
        i = Math.min(i, j);
        return s.substring(i) + s.substring(0, i);
    }

    public static void main(String[] args) {
        OrderlyQueue orderlyQueue = new OrderlyQueue();
        String s = mock();
        System.out.println(orderlyQueue.orderlyQueue(s, 1));
    }

    private static String mock() {
        String s = "xitavoyjqiupzadbdyymyvuteolyeerecnuptghlzsynozeuuvteryojyokpufanyrqqmtgxhyycltlnusyeyyqygwupcaagtkuq";
        return s;
    }

}

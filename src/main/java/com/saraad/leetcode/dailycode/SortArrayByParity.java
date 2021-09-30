package com.saraad.leetcode.dailycode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: SortArrayByParity
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2020/11/12 0:14
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class SortArrayByParity {

    public static void main(String[] args) {
        int[] A = {3,1,4,2};
        for(int i=0; i<A.length; i++){
            if (((i^A[i])&1) == 1) {
                int j = i;
                while(((i^A[j])&1) == 1){
                    j++;
                }
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        System.out.println((2^3)&1);
    }

}

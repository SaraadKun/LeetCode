package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 11-07-2022 23:07
 */

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int i = len - 1;
        while (i >= 0) {
            if (digits[i] < 9) {
                digits[i]++;
                break;
            } else {
                digits[i] = 0;
                i--;
            }
        }
        if (i < 0) {
            int[] newDigits = new int[len + 1];
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }

    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        int[] digits = {9, 9, 9};
        int[] newDigits = plusOne.plusOne(digits);
        for (int i = 0; i < newDigits.length; i++) {
            System.out.print(newDigits[i] + " ");
        }
        Integer a = 123;
        Object obj = a;
    }
}

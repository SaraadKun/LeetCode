package com.saraad.leetcode.dailycode;

/**
 * @Title: Code200720
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2020/7/21 9:54 上午
 * @Copyright: 2020  Inc. All rights reserved.
 */
public class Code200720 {

    public static void main(String[] args) {
        String s1 = "db";
        String s2 = "";
        String s3 = "db";
        boolean flag = isInterleave(s1, s2, s3);
        System.out.println(flag);
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        return isInterleaveRecursion(s1, s2, s3, s1.length(), s2.length());
    }

    public static boolean isInterleaveRecursion(String s1, String s2, String s3, int i, int j) {
        int k = i + j - 1;
        if (k < 0){
            return true;
        }
        if(i > 0 && s3.charAt(k) == s1.charAt(i-1)){
            if (j > 0 && s3.charAt(k) == s2.charAt(j-1)){
                return isInterleaveRecursion(s1, s2, s3, i-1, j) || isInterleaveRecursion(s1, s2, s3, i, j-1);
            }
            return isInterleaveRecursion(s1, s2, s3, i-1, j);
        }else if (j > 0 && s3.charAt(k) == s2.charAt(j-1)){
            return isInterleaveRecursion(s1, s2, s3, i, j-1);
        }
        return false;
    }

}

package com.saraad.leetcode.dailycode;

/**
 * @Title: CompareVersion
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/9/2 1:10 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class CompareVersion {

    public int compareVersion(String version1, String version2) {
        int len1 = version1.length();
        int len2 = version2.length();
        int i = 0, j = 0;
        boolean flag = true;
        while (i < len1 || j < len2) {
            int sum1 = 0, sum2 = 0;
            while (i < len1 && version1.charAt(i) != '.') {
                sum1 = sum1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            while (j < len2 && version2.charAt(j) != '.') {
                sum2 = sum2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            if (sum1 != sum2) {
                return sum1 > sum2 ? 1 : -1;
            }
            i++;
            j++;
        }
        return 0;
    }

    public static void main(String[] args) {
        CompareVersion instance = new CompareVersion();
        String v1 = "1.0.1";
        String v2 = "1.00";
        int res = instance.compareVersion(v1, v2);
        System.out.println(res);
    }

}

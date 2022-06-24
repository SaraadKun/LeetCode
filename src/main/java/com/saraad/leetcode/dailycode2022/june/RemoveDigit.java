package com.saraad.leetcode.dailycode2022.june;

/**
 * @Description: 2259. 移除指定数字得到的最大结果
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/remove-digit-from-number-to-maximize-result
 * @Date: 24-06-2022 09:45
 */

public class RemoveDigit {

    public String removeDigit(String number, char digit) {
        int last = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == digit) {
                if (i < number.length() - 1 && number.charAt(i) < number.charAt(i + 1)) {
                    return number.substring(0, i) + number.substring(i + 1);
                }
                last = i;
            }
        }
        return number.substring(0, last) + number.substring(last + 1);
    }
}

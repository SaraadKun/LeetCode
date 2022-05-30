package com.saraad.leetcode.dailycode2022.may;

import java.util.Date;

public class RemoveOuterParentheses {

    String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (count > 0) {
                    sb.append(s.charAt(i));
                }
                count++;
            } else {
                count--;
                if (count > 0) {
                    sb.append(s.charAt(i));
                }
            }
        }
        return sb.toString();
    }


}

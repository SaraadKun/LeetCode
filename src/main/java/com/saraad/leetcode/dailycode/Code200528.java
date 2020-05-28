package com.saraad.leetcode.dailycode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: Code 76. 最小覆盖字符串
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 *
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * @author: saraad
 * @date: 2020/5/28 22:49
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code200528 {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = minWindow(s, t);
        System.out.println(result);
    }

    public static String minWindow(String s, String t){
        if (s == null || "".equals(s) || t == null || "".equals(t)){
            return "";
        }
        HashMap<Character, Integer> current = new HashMap<>();
        HashMap<Character, Integer> origin = new HashMap<>();
        int[] arr = {0,-1};
        for (char c : t.toCharArray()) {
            origin.put(c, origin.get(c) == null ? 1 : origin.get(c) + 1);
        }
        char[] chars = s.toCharArray();
        int i = 0, j = 0;
        while (true){
            if (!check(current, origin)) {
                current.put(chars[i], current.get(chars[i]) == null ? 1 : current.get(chars[i]) + 1);
                i++;
            }else {
                if (arr[1] < 0 || (i-j) < (arr[1] - arr[0])){
                    arr[0] = j;
                    arr[1] = i;
                }
                current.put(chars[j], current.get(chars[j]) - 1);
                j++;
            }
            if (i >= chars.length && !check(current,origin)){
                break;
            }
        }
        return arr[1] < 0 ? "" : s.substring(arr[0],arr[1]);

    }

    //校验当前窗口是否符合要求
    static boolean check(Map<Character,Integer> current, Map<Character, Integer> origin){
        if (current == null || origin == null){
            return false;
        }
        for (Character key : origin.keySet()) {
            if (current.get(key) == null || current.get(key) < origin.get(key)){
                return false;
            }
        }
        return true;
    }

}

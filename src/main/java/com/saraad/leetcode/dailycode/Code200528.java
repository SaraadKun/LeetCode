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
//        String s = "A";
//        String t = "A";
        String result = minWindow(s, t);
        System.out.println(result);
//        System.out.println(s.substring(9,12));
    }

    public static String minWindow(String s, String t){
        if (s == null || "".equals(s) || t == null || "".equals(t)){
            return "";
        }
        HashMap<Character, Integer> current = new HashMap<>();
        HashMap<Character, Integer> origin = new HashMap<>();
        //存放索引
        int[] ids = new int[s.length()];
        char[] shorts = new char[s.length()];
        int len = 0;
        for (char c : t.toCharArray()) {
            origin.put(c, origin.get(c) == null ? 1 : origin.get(c) + 1);
        }
        Set<Character> keySet = origin.keySet();
        //预处理s
        for (int i = 0; i < s.length(); i++) {
           if (keySet.contains(s.charAt(i))){
               ids[len] = i;
               shorts[len] = s.charAt(i);
               len++;
           }
        }
        int l = 0, r = -1;
        int i = -1, j = 0;
        while (true){
            if (!check(current, origin)) {
                if (++i >= len){
                    break;
                }
                current.put(shorts[i],current.getOrDefault(shorts[i],0) + 1);
            }else {
                if (r < 0 || ids[i] - ids[j] < r - l){
                    r = ids[i];
                    l = ids[j];
                }
                current.put(shorts[j], current.get(shorts[j]) - 1);
                j++;
            }
        }

        return r < 0 ? "" : s.substring(l,r+1);

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

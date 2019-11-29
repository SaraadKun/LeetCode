package com.saraad.leetcode.group01;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * @Title: 无重复字符的最长子串
 * @Package:com.saraad.leetcode.group01
 * @Description:
 * @author: saraad
 * @date: 2019/11/28 7:17 下午
 * @Copyright: 2019  Inc. All rights reserved.
 */
public class DistinctString {

    public static void main(String[] args) {
        String str = "abcabcbb";
        int i = lengthOfLongestSubstring(str);
        System.out.println(i);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int maxlen = 0;
        int leftIndex = 0;
        //右索引
        for (int right = 0; right < chars.length; right++) {
            //移动左侧索引寻找重复字符
            for (int j = leftIndex; j < right; j++) {
                if (chars[j] == chars[right]){
                    maxlen = maxlen < right - leftIndex ? right -leftIndex : maxlen;
                    //移动左起始索引至新的不重复字符串起始索引处
                    leftIndex = j + 1;
                    break;
                }
            }
        }
        //遍历完毕后检查一次最后一个不重复字符串的长度是否是最大长度
        maxlen = maxlen < chars.length - leftIndex ? chars.length -leftIndex : maxlen;
        return maxlen;
    }

    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 1;
        for (int i = 0; i < chars.length - 1; i++) {
            map.put(chars[i],i);
            for (int j = i + 1, len = 1; j < chars.length; j++) {
                if (map.containsKey(chars[j]))
                    break;
                map.put(chars[j],j);
                ++len;
                if (len > maxLen)
                    maxLen = len;
            }
            map.clear();
        }
        return maxLen;
    }

}

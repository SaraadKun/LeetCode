package com.saraad.leetcode.group01;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: 最长回文字串
 * @Package:com.saraad.leetcode.group01
 * @Description:
 * @author: saraad
 * @date: 2019/12/2 20:41
 * @Copyright: 2019  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class LongestPalindrome {

    public static void main(String[] args) {
//        String s = "111";
//        String s1 = longestPalindrome(s);
//        System.out.println(s1);
        int i = Integer.MAX_VALUE / 6 * 10;
        System.out.println(i);
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        //使用数组保存最长回文字符的首尾索引位置
        int[] index = new int[]{0,0};
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //当左侧索引距离终点距离的两倍不大于当前最大回文字符串length时,可判断不会出现更长的回文字符串,结束循环
            if ((chars.length - i) * 2 <= index[1] - index[0])
                break;
            //把回文看成中间的部分全是同一字符，左右部分相对称
            //找到下一个与当前字符不同的字符
            i = findLongest(index, i, chars);

        }
        return s.substring(index[0], index[1] + 1);
    }

    //尽量只操作指针(索引)而不操作实际对象
    private static int findLongest(int[] index, int left, char[] chars) {
        int right = left;
        //寻找中间部分重复的字符串
        //检测到中间部分重复字符串后,定位重复字符串右侧索引,跳过无意义的循环 bab baab baaab baaaab
        while (right < chars.length -1 && chars[left] == chars[right + 1])
            right++;
        int jump = right;
        //确定好回文中心后,左右扩散寻找
        while (left > 0 && right < chars.length - 1 && chars[left - 1] == chars[right + 1]){
            left--;
            right++;
        }
        // 记录最大回文字符串两侧索引
        if (index[1] - index[0] < right -left){
            index[0] = left;
            index[1] = right;
        }
        return jump;
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return "";
        String result = "";
        char[] chars = s.toCharArray();
        String tmp = null;
        int left, right;
        //回文字符串中心为双字符情况
        for (int i = 0; i < chars.length; i++) {
            right = i + 1;
            if (right < chars.length && chars[i] == chars[right]){
                tmp = s.substring(i,right + 1);
                if (result.length() < tmp.length())
                    result = tmp;
                left = i - 1;
                right += 1;
                for (int j = i; j < chars.length; j++,left--,right++) {
                    if (left < 0 || right >= chars.length || chars[left] != chars[right])
                        break;
                    tmp = s.substring(left, right + 1);
                    if (result.length() < tmp.length())
                        result = tmp;
                }
            }
        }
        //中心为单字符情况
        for (int i = 0; i < chars.length; i++) {
            right = i;
            tmp = s.substring(i,right + 1);
            if (result.length() < tmp.length())
                result = tmp;
            left = i - 1;
            right += 1;
            for (int j = i; j < chars.length; j++,left--,right++) {
                if (left < 0 || right >= chars.length || chars[left] != chars[right])
                    break;
                tmp = s.substring(left, right + 1);
                if (result.length() < tmp.length())
                    result = tmp;
            }
        }
        return result;
    }

}

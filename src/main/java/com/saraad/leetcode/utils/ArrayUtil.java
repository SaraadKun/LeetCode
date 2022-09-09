package com.saraad.leetcode.utils;

import com.saraad.util.JSONUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 04-09-2022 01:30
 */

public class ArrayUtil {

    /**
     * @Description: 二维数组字符串输入转换
     * @param input 二维数组json字符串
     * @return 二维int数组
     */
    public static int[][] mock2d(String input) {
        return StringUtils.isEmpty(input) ? new int[][]{} : JSONUtil.readValue(input, int[][].class);
    }
    /**
     * @Description: 一维数组字符串输入转换
     * @param input 一维数组json字符串
     * @return int数组
     */
    public static int[] mock1d(String input) {
        return StringUtils.isEmpty(input) ? new int[]{} : JSONUtil.readValue(input, int[].class);
    }
    /**
     * @Description: 一维String数组字符串输入转换
     * @param input 一维String数组json字符串
     * @return String数组
     */
    public static String[] mockStringArray(String input) {
        return StringUtils.isEmpty(input) ? new String[]{} : JSONUtil.readValue(input, String[].class);
    }

    public static void main(String[] args) {
        String input = "[[]]";
        System.out.println(JSONUtil.writeValueAsString(mock2d(input)));
    }

}

package com.saraad.leetcode.group01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Title: Z字形变换
 * @Package:com.saraad.leetcode.group01
 * @Description:
 * @author: saraad
 * @date: 2019/12/4 1:34 下午
 * @Copyright: 2019  Inc. All rights reserved.
 */
public class ZStringTrans {

    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        //LDREOEIIECIHNTSG
        int rows = 2;
        String convert = convert(s, rows);
        System.out.println(convert);
    }

    public static String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows < 2) return s;
        char[] chars = s.toCharArray();
        int length = chars.length;
        //判断循环次数 每轮循环的字符数为numrows * 2 - 2
        int circle = numRows * 2 - 2;
        int round = length%circle == 0 ? length/circle : length/circle + 1;
        char[] res = new char[length];
        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int r = 0; r < round; r++) {
                //判断行数
                if (i == 0) {
                    //第一行一定有值
                    res[index++] = chars[r * circle];
                }else if(i == numRows - 1){
                    //最后一行
                    if (r * circle + numRows - 1 > length - 1)
                        break;
                    res[index++] = chars[r * circle + numRows - 1];
                }else {
                    //除第一行与最后一行外,中间每一行在每轮循环中有两个元素(除最后一轮不完整的循环外)
                    if (r * circle + i > length - 1)
                        break;
                    res[index++] = chars[r * circle + i];
                    if ((r + 1) * circle - i > length - 1)
                        break;
                    res[index++] = chars[(r + 1) * circle - i ];
                }
            }
        }
        return new String(res);
    }
}

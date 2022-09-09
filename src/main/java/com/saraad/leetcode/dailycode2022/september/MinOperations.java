package com.saraad.leetcode.dailycode2022.september;

import com.saraad.leetcode.utils.ArrayUtil;
import org.junit.Assert;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 09-09-2022 04:14
 */

public class MinOperations {
    public int minOperations(String[] logs) {
        int ans = 0;
        for (String log : logs) {
            switch(log) {
                case "../" -> ans = ans == 0 ? 0 : ans - 1;
                case "./" -> {}
                default -> ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MinOperations obj = new MinOperations();
        Assert.assertEquals(3, obj.minOperations(ArrayUtil.mockStringArray("[\"d1/\",\"d2/\",\"./\",\"d3/\",\"../\",\"d31/\"]")));
    }

}

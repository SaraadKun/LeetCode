package com.saraad.leetcode.dailycode2022.october;

import com.saraad.leetcode.utils.ArrayUtil;
import com.saraad.util.JSONUtil;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 06-10-2022 13:35
 */

public class ThreeEqualParts {
    public int[] threeEqualParts(int[] arr) {
        //1.dp数组统计1的个数c1,判断c1能否被3整除,若不能[-1,-1],否则2.
        //2.从dp数组尾部统计出最后一个1后面的0的个数c0; 设c1=3x,根据dp数组找到对应的x+c0,2x+c0在原数组中的位置;从数组尾部开始,三指针从后向前遍历,至最右侧指针移动至2x+1个1处为止,若各指针指向的位置数字均相同,则返回[x+c0+1,2x+c0+1]
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + arr[i - 1];
        }
        int c1 = dp[n];
        if (c1 % 3 != 0) {
            return new int[]{-1, -1};
        } else if (c1 == 0) {
            return new int[]{0, 2};
        }
        int c0 = 0;
        for (int i = n; i > 0; i--) {
            if (dp[i - 1] != dp[i]) {
                break;
            }
            c0++;
        }
        int x1 = c1 / 3, x2 = x1 * 2, x3 = x2 + 1,
                p1 = 0, p2 = 0, p3 = 0;
        for (int i = n; i > 0; i--) {
            if (dp[i] == x3) {
                p3 = i;
            }
            if (dp[i] == x2) {
                p2 = i;
            }
            if (dp[i] == x1) {
                p1 = i;
            }
        }
        int i = p1 - 1 + c0, j = p2 - 1 + c0, k = n - 1;
        for (; i >= 0 && j >= 0 && k >= p3 - 1; i--, j--, k--) {
            if (arr[i] != arr[k] || arr[j] != arr[k]) {
                return new int[]{-1, -1};
            }
        }
        return k == p3 - 2 ? new int[] {p1 -1 + c0, p2 + c0} : new int[]{-1, -1};
    }

    public static void main(String[] args) {
        ThreeEqualParts obj = new ThreeEqualParts();
//        System.out.println(JSONUtil.writeValueAsString(obj.threeEqualParts(ArrayUtil.mock1d("[1,0,1,0,1]"))));
//        System.out.println(JSONUtil.writeValueAsString(obj.threeEqualParts(ArrayUtil.mock1d("[1,1,1]"))));
        char[] chs = {'1'};
        Character character = '1';
        Arrays.sort(chs);
        System.out.println(chs[0] == character);
        Deque<Character> stack = new ArrayDeque<>();
        stack.clear();
    }

}

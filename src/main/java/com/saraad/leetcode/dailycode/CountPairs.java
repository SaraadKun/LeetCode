package com.saraad.leetcode.dailycode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: CountPairs
 * @Package:com.saraad.leetcode.dailycode
 * @Description: 1711. 大餐计数 https://leetcode-cn.com/problems/count-good-meals/
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 * 你可以搭配 任意 两道餐品做一顿大餐。
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 * 示例 1：
 * 输入：deliciousness = [1,3,5,7,9]
 * 输出：4
 * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 * 示例 2：
 * 输入：deliciousness = [1,1,1,3,3,3,7]
 * 输出：15
 * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
 * 提示：
 * 1 <= deliciousness.length <= 105
 * 0 <= deliciousness[i] <= 220
 * @date: 2021/7/7 4:14 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class CountPairs {

    public int countPairs(int[] deliciousness) {
        //寻找最大值 max 大餐美味程度<= max<<1
        int mod = (int) 1e9 + 7;
        int max = -1;
        for (int d : deliciousness) {
            max = Math.max(max, d);
        }
        int bits = 0;
        while ((1 << bits) < max) {
            ++bits;
        }
        bits += 1;
        //构造数组保存出现的每一道餐品,数组下标为餐品美味值,数组值为该美味值餐品出现的次数
//        int[] dict = new int[(1 << bits) + 1];
        Map<Integer, Integer> dict = new HashMap<>();
        //先get后put,统计sum
        int res = 0;
        for (int d : deliciousness) {
            for (int i = 0; i <= bits; i++) {
                int idx = (1 << i) - d;
                if (idx >= 0) {
                    res += dict.getOrDefault(idx, 0);
                    if (res >= mod) {
                        res -= mod;
                    }
                }
            }
            dict.put(d, dict.getOrDefault(d, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {149,107,1,63,0,1,6867,1325,5611,2581,39,89,46,18,12,20,22,234};
//        int[] arr = {32,32,32,32,32,32,32};
        int[] arr = getData();
        CountPairs obj = new CountPairs();
        int i = obj.countPairs(arr);
        System.out.println(i);

        System.out.println((int)1e9 + 7);
        System.out.println(1000000007);
    }

    private static int[] getData() {
        int[] arr = new int[100000];
        Arrays.fill(arr, 32);
        return arr;
    }

}

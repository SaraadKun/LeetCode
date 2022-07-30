package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 30-07-2022 17:47
 */

public class LargestComponentSize {

    int N = 20010, ans = 1;
    int[] f = new int[N], cnts = new int[N];

    //初始化UF
    {
        for (int i = 0; i < N; i++) {
            f[i] = i;
            cnts[i] = 1;
        }
    }

    private int find(int x) {
        if (f[x] != x) {
            f[x] = find(f[x]);
        }
        return f[x];
    }

    private void union(int x, int y) {
        int idx = find(x), idy = find(y);
        if (idx == idy) {
            return;
        }
        //union时更新连通块的计数以及ans
        cnts[idy] += cnts[idx];
        f[idx] = idy;
        ans = Math.max(ans, cnts[idy]);
    }

    public int largestComponentSize(int[] nums) {
        //直接gcd枚举数字判断喜提TLE,故将每个数字分解质因数,维护 质因数 -> [数字下标集合] 的映射关系加速查找
        //用下标维护数据可以将数据范围从1e5降到2*1e4
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            //寻找质因数并更新映射
            for (int j = 2; j * j <= cur; j++) {
                if (nums[i] % j == 0) {
                    add(map, j, i);
                    while (cur % j == 0) {
                        cur /= j;
                    }
                }
            }
            //若操作完成后cur大于1,则cur必然是一个质数
            if (cur > 1) {
                add(map, cur, i);
            }
        }
        for (Integer key : map.keySet()) {
            List<Integer> list = map.get(key);
            //只需遍历到一遍list,list中的元素就全部联通了
            for (int i = 1; i < list.size(); i++) {
                union(list.get(0), list.get(i));
            }
        }
        return ans;
    }

    private void add(Map<Integer, List<Integer>> map, int key, int val) {
        List<Integer> list = map.getOrDefault(key, new ArrayList<>());
        list.add(val);
        map.put(key, list);
    }

    public static void main(String[] args) {
//        int[] nums = {2,3,6,7,4,12,21,39};
        int[] nums = {4, 6, 15, 35};
        LargestComponentSize lcs = new LargestComponentSize();
        System.out.println(lcs.largestComponentSize(nums));
    }
}

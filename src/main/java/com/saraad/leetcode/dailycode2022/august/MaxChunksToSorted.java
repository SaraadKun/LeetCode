package com.saraad.leetcode.dailycode2022.august;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: <a href="https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/">...</a>
 * @Date: 14-08-2022 02:40
 */

public class MaxChunksToSorted {
    public int maxChunksToSorted(int[] arr) {
        /* 数组长度为n,对于每一个i∈[1,n-1],若[i,n-1]区间内的所有数字均大于等于[0,i-1]的数字,则区间[i,n-1]可以作为一个"块";以max[i]表示区间[0,i]内的最大值,min[i]表示区间[i,n-1]内的最小值,则上述描述等价于若min[i]>=max[i-1],则[i,n-1]是一个"块"。容易想到,对于一个"块"[i,n-1],若存在j∈[i+1,n-1],且区间[j,n-1]是一个"块",则区间[i,j-1]也是一个"块"(min[j]>=min[j-1]且i∈[0,j-1]),即状态转移方程为cnt[i] = [i,n-1]是一个"块" ? cnt[i+1] + 1 : cnt[i+1]。数组整体可以视为一个块，故初始值为1。*/
        /*预处理数据,从前往后遍历arr,统计每个位置的max值用数组存储; 从后往前遍历arr,记录遍历到当前位置的最小值min,当i > 0时,若min[i] >= max[i-1],则代表[i,n-1]是一个块 */
        int n = arr.length, ans = 1, min = Integer.MAX_VALUE;
        int[] max = new int[n];
        max[0] = arr[0];
        for (int i = 1; i < n; i++) {
            max[i] = Math.max(max[i - 1], arr[i]);
        }
        for (int i = n - 1; i > 0; i--) {
            min = Math.min(min, arr[i]);
            if (min >= max[i - 1]) {
                ans++;
            }
        }
        return ans;
    }
}

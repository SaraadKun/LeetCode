package com.saraad.leetcode.dailycode2022.september;

import com.saraad.leetcode.utils.ArrayUtil;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 11-09-2022 07:13
 */

public class MinCostToHireWorkers {

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        //求出每个工人 p = w/q的比值,挑选的k个工人支付时,一定是按max(p)来进行支付的
        //记k个人总支付为ck,则ck = max(pi) * sum(qi), 在max(pi)固定时,ck值只与sum(pi)正相关, 对于每一个p,
        // 只需要找到k个最小的q[i]即可保证当前p对应的ck为最小值, 限制条件为p * q[i] >= w[i]
        // 具体实现为: 计算出每个人对应的p,对p进行升序排序, 使用优先队列维护pi对应的q[i]值,从第k个p开始计算当前ck并维护全局最小ck
        // 因为对p进行升序排序,所以第k个之前的p均找不出k个符合要求的最小q[i],即p[i] * q[i] < w[i], i < k - 1
        int n = quality.length;
        double ans = Double.MAX_VALUE, sq = 0d;
        double[][] p = new double[n][2];
        for (int i = 0; i < n; i++) {
            p[i][0] = (double)wage[i] / quality[i];
            p[i][1] = i;
        }
        Arrays.sort(p, Comparator.comparingDouble(o -> o[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k - 1; i++) {
            int cur = quality[(int)p[i][1]];
            sq += cur;
            pq.offer(cur);
        }
        for (int i = k - 1; i < n; i++) {
            int cur = quality[(int)p[i][1]];
            sq += cur;
            pq.offer(cur);
            ans = Math.min(p[i][0] * sq, ans);
            //sq中应始终保留当前的前k-1小的q[i]值,因为p[i+1] >= p[i],新入队列的元素一定要小于等于原来的第k小的元素才会导致ans可能被更新,
            //否则不影响结果,故只需保留k-1个元素即可
            sq -= pq.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        MinCostToHireWorkers obj = new MinCostToHireWorkers();
//        int[] quality = ArrayUtil.mock1d("[10,20,5]"), wage = ArrayUtil.mock1d("[70,50,30]"); int k = 2;
//        int[] quality = ArrayUtil.mock1d("[3,1,10,10,1]"), wage = ArrayUtil.mock1d("[4,8,2,2,7]"); int k = 3;
        int[] quality = ArrayUtil.mock1d("[37,32,14,14,23,31,82,96,81,96,22,17,68,3,88,59,54,23,22,77,61,16,46,22,94,50,29,46,7,33,22,99,31,99,75,67,95,54,31,48,44,96,99,20,51,54,18,85,25,84]"),
                wage = ArrayUtil.mock1d("[453,236,199,359,107,45,150,433,32,192,433,94,113,200,293,31,48,27,15,32,295,97,199,427,90,215,390,412,475,131,122,398,479,142,103,243,86,309,498,210,173,363,449,135,353,397,105,165,165,62]");
        int k = 20;
//        for (int i = 0; i < wage.length; i++) {
//            if (wage[i] == 15) {
//                System.out.println(quality[i]);
//            }
//        }
//        double diff = 1e-10;
//        double p = (double)15/22;
//        System.out.println(p * 22);
//        System.out.println(p * 22 < 15);
//        System.out.println((p + diff) * 22 < 15);
        double ans = obj.mincostToHireWorkers(quality, wage, k);
        System.out.println(ans);
    }
}

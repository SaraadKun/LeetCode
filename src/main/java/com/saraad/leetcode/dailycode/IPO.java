package com.saraad.leetcode.dailycode;


import com.saraad.leetcode.bean.Pair;

import java.util.*;

/**
 * @Title: IPO
 * @Package:com.saraad.leetcode.dailycode
 * @Description: 502
 * @author: saraad
 * @date: 2021/9/8 10:38 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class IPO {

    public static void main(String[] args) {
        IPO instance = new IPO();
        int k = 10;
        int w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 2};
        int res = instance.findMaximizedCapital(k, w, profits, capital);
        System.out.println(res);

    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        //每完成一个项目,w都是是单调增加的,总共可以完成k个项目,故每次都选取可以完成的收益最高的项目,k次之后w即为最大值
        //对项目按启动资本进行排序
//        List<Pair<Integer, Integer>> pc = new int[profits.length][];
        List<Pair<Integer, Integer>> pc = new ArrayList<>();
        for (int i = 0; i < profits.length; i++) {
            pc.add(new Pair<>(profits[i], capital[i]));
        }
        pc.sort(Comparator.comparingInt(Pair::getValue));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k; i++) {
            Iterator<Pair<Integer, Integer>> iter = pc.iterator();
            while (iter.hasNext()) {
                Pair<Integer, Integer> cur = iter.next();
                if (cur.getValue() > w) {
                    break;
                }
                pq.offer(cur.getKey());
                iter.remove();
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }
        return w;
    }

}

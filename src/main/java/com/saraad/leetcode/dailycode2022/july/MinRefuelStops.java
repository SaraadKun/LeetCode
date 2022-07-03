package com.saraad.leetcode.dailycode2022.july;

import java.util.PriorityQueue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/minimum-number-of-refueling-stops/
 * @Date: 03-07-2022 20:26
 */

public class MinRefuelStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        //根据所剩油量可以判断可以到达哪些加油站,对可到达的加油站的油量进行从大到小排序,每次取最大值,直到累积的里程数大于等于target,此时加油的次数即为最小次数
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int ans = 0, cnt = startFuel, idx = 0;
        while (cnt < target) {
            while (idx < stations.length && stations[idx][0] <= cnt) {
                pq.offer(stations[idx][1]);
                idx++;
            }
            if (pq.size() == 0) {
                break;
            }
            cnt += pq.poll();
            ans++;
        }
        return cnt < target ? -1 : ans;
    }

    public static void main(String[] args) {
        MinRefuelStops mrs = new MinRefuelStops();
        int target = 1;
        int startFuel = 1;
        int[][] stations = {{1, 1}};
        int ans = mrs.minRefuelStops(target, startFuel, stations);
        System.out.println(ans);
    }
}

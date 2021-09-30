package com.saraad.leetcode.dailycode.june;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 815. 公交路线
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * <p>
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * <p>
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * <p>
 * 示例 1：
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * 示例 2：
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出：-1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 105
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 105
 * 0 <= routes[i][j] < 106
 * 0 <= source, target < 106
 */
public class NumBusesToDestination {

    public static void main(String[] args) {

        NumBusesToDestination obj = new NumBusesToDestination();
        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int source = 1;
        int target = 9;
        int res = obj.numBusesToDestination(routes, source, target);
        System.out.println(res);
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Boolean> used = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                List<Integer> idxs = map.getOrDefault(routes[i][j], new ArrayList<>());
                idxs.add(i);
                if (!map.containsKey(routes[i][j])) {
                    map.put(routes[i][j], idxs);
                }
            }
        }
        Map<Integer, Boolean> targetIdxs = map.getOrDefault(target, new ArrayList<>()).stream().collect(Collectors.toMap(n -> n, n -> true));
        if (targetIdxs.isEmpty()) {
            return -1;
        }
        int res = 0;
        Set<Integer> cur = Collections.singleton(source);
        used.put(source, true);
        while (!cur.isEmpty()) {
            Set<Integer> curIdxs = cur.stream().flatMap(n -> map.get(n).stream()).collect(Collectors.toSet());
            Set<Integer> tmp = new HashSet<>();
            for (Integer curIdx : curIdxs) {
                if (targetIdxs.containsKey(curIdx)) {
                    return res + 1;
                }
                for (int node : routes[curIdx]) {
                    if (!used.containsKey(node)) {
                        tmp.add(node);
                        used.putIfAbsent(node, true);
                    }
                }
            }
            ++res;
            cur = tmp;
        }
        return -1;
    }
}

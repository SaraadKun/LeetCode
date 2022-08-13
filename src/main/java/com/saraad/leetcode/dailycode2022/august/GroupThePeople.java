package com.saraad.leetcode.dailycode2022.august;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: <a href="https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/">...</a>
 * @Date: 12-08-2022 02:39
 */

public class GroupThePeople {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int n = groupSizes.length;
        List<List<Integer>> ans = new ArrayList<>();
        //key为groupSizes[i],value为i集合,当value.size() == key时加入结果集
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> cur = map.getOrDefault(groupSizes[i], new ArrayList<>());
            cur.add(i);
            if (cur.size() == groupSizes[i]) {
                ans.add(cur);
                cur = new ArrayList<>();
            }
            map.put(groupSizes[i], cur);
        }
        return ans;
    }
}

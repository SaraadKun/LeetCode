package com.saraad.leetcode.dailycode2022.june;

import com.saraad.util.JSONUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 28-06-2022 20:55
 */

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i - 1] == nums[i])
                continue;
            int j = i + 1, p = n - 1, target = -nums[i];
            while (j < p) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    j++;
                    continue;
                }
                if (nums[j] + nums[p] == target) {
                    ans.add(List.of(nums[i], nums[j], nums[p]));
                    j++;
                    p--;
                } else if (nums[j] + nums[p] < target) {
                    j++;
                } else {
                    p--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(JSONUtil.writeValueAsString(ts.threeSum(nums)));
    }
}

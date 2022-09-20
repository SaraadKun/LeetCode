package com.saraad.leetcode.dailycode2022.september;

import com.saraad.leetcode.utils.ArrayUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 20-09-2022 10:40
 */

public class CanPartitionKSubsets {
    int n, target, k;
    int[] nums;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        //排序 + 回溯 + 剪枝, 优先使用大数
        int sum = 0;
        //先校验是否可以均分
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        this.nums = nums;
        this.n = nums.length;
        this.target = sum / k;
        this.k = k;
        boolean[] visited = new boolean[n];
        return backtrack(0, n - 1, 0, visited);
    }

    private boolean backtrack(int cnt, int idx, int cur, boolean[] visited) {
        if (cnt == k) {
            return true;
        }
        if (cur == target) {
            return backtrack(cnt + 1, n - 1, 0, visited);
        }
        if (idx < 0) {
            return false;
        }
        if (visited[idx]) {
            return backtrack(cnt, idx - 1, cur, visited);
        }
        for (int i = idx; i >= 0; i--) {
            if (visited[i] || cur + nums[i] > target) {
                continue;
            }
            visited[i] = true;
            if (backtrack(cnt, i - 1, cur + nums[i], visited)) {
                return true;
            }
            visited[i] = false;
            if (cur == 0) {
                return false; //当前位置回溯后无解,则后续元素也必然无解,当前元素未利用到
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CanPartitionKSubsets obj = new CanPartitionKSubsets();
        Assert.assertTrue(obj.canPartitionKSubsets(ArrayUtil.mock1d("[3522,181,521,515,304,123,2512,312,922,407,146,1932,4037,2646,3871,269]"), 5));
        Assert.assertTrue(obj.canPartitionKSubsets(ArrayUtil.mock1d("[4,5,9,3,10,2,10,7,10,8,5,9,4,6,4,9]"), 5));
    }
}

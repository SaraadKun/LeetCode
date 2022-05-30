package com.saraad.leetcode.dailycode2022.february;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 *  442. 数组中重复的数据
 *  https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 */
public class FindDuplicates {

    public static void main(String[] args) {
        FindDuplicates instance = new FindDuplicates();
        int[] nums  = {4,3,2,7,8,2,3,1};
//        List<Integer> ans = instance.findDuplicates(nums);
        List<Integer> ans = instance.findDuplicates2(nums);
        System.out.println(JSON.toJSONString(ans));
    }

    public List<Integer> findDuplicates2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int idx = Math.abs(nums[i]) - 1;
            nums[idx] *= -1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = (nums[i] - 1) % n;
            nums[x] += n;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > n<<1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

}

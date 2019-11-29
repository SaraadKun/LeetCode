package com.saraad.leetcode.group01;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Title: TwoNumsAdd
 * @Package:com.saraad.leetcode.group01
 * @Description:
 * @author: saraad
 * @date: 2019/11/28 4:27 下午
 * @Copyright: 2019  Inc. All rights reserved.
 */
public class TwoNumsAdd {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
//        int[] nums = {2};
        int target = 9;
        int[] ints = twoSum(nums, target);
        Arrays.stream(ints).forEach(System.out::println);
    }

//  时间O(n) 空间O(n)
    public static int[] twoSum(int[] nums, int target) {
        int[] ints = new int[2];
        if (nums == null || nums.length < 2) return ints;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
        if (map.containsKey(nums[i])){
            ints[0] = i;
            ints[1] = map.get(nums[i]);
            break;
        }
        //key为补数,value为对应数组中对应数的索引
        map.put(target - nums[i], i);
    }
        return ints;
}

//  时间O(n2)
    public static int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length < 2)
            return new int[]{};
        int index = 0;
        while (index < nums.length - 1) {
            for (int i = nums.length - 1; i > index; i--) {
                if (nums[i] + nums[index] == target)
                    return new int[]{index, i};
            }
            ++index;
        }
        return new int[]{};
    }

}

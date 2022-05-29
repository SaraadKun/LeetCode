package com.saraad.leetcode.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: Test2
 * @Package:com.saraad.leetcode.temp
 * @Description:
 * The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence
 * in an array or list of integers:
 *
 * Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
 * // should be 6: {4, -1, 2, 1}
 *
 * Easy case is when the list is made up of only positive numbers and the maximum sum is
 * the sum of the whole array. If the list is made up of only negative numbers, return 0 instead.
 *
 * Empty list is considered to have zero greatest sum. Note that the empty list or array
 * is also a valid sublist/subarray.
 * @author: saraad
 * @date: 2020/3/23 3:34 下午
 * @Copyright: 2020  Inc. All rights reserved.
 */
public class Test2 {

    public static void main(String[] args) {
        double sqrt = Math.sqrt(8.00);
        System.out.println((int)sqrt);

    }

    public int deleteAndEarn(int[] nums) {
        int max = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] cnt = new int[max + 1];
        for (int num : nums) {
            cnt[num] += num;
        }
        return rob(cnt);
    }

    private int rob(int[] cnt) {
        if (cnt.length == 1) {
            return cnt[0];
        }
        int p = cnt[0], ans = Math.max(cnt[0], cnt[1]);
        for (int i = 2; i < cnt.length; i++) {
            int pp = p;
            p = ans;
            ans = Math.max(p, pp + cnt[i]);
        }
        return ans;
    }

    public boolean judgeSquareSum(int c) {
        int lo = 0, hi = (int)Math.sqrt(c) + 1;
        while (lo <= hi) {
            int sum = lo * lo + hi * hi;
            if (sum == c) {
                return true;
            }
            if (sum > c) {
                hi--;
            } else {
                lo++;
            }
        }
        return false;
    }

    //count(arr[i] == 1) > k   len(arr) > K > 0;
    public static int split(String arr, int k) {
        int len = arr.length();
        int cnt = k, i = 0;
        while (cnt > 0) {
            if ('1' == arr.charAt(i)) {
                cnt--;
            }
            i++;
        }
        return len - i;
    }


    public static int solutionDP(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = arr[0];
        int maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxSum = Math.max(maxSum + arr[i], arr[i]);
            res = Math.max(res, maxSum);
        }
        return res;
    }

    public static int solution(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] subarr = {-1, -1};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[0] < 0 && arr[i] <= 0) continue;//从第一个正数的索引处开始筛选
            int tmp = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                tmp += arr[j];
                if (tmp > sum) {
                    sum = tmp;
                    subarr[0] = i;
                    subarr[1] = j;
                }
            }
            //特殊情况,序列最后一个值为目标值
            if (tmp > sum && arr[1] >= arr.length) {
                sum = tmp;
                subarr[0] = i;
                subarr[1] = i;
            }
        }
        System.out.println("maximum arr indexes is from " + subarr[0] + " to " + subarr[1]);
        return sum;
    }

    public int minDiffInBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        minDiff(root, list);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            res = Math.min(res, list.get(i) - list.get(i - 1));
        }
        return res;
    }

    private void minDiff(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        minDiff(root.left, list);
        list.add(root.val);
        minDiff(root.right, list);
    }


}
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }

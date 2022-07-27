package com.saraad.leetcode.dailycode2022.july;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 26-07-2022 23:34
 */

public class MaxLength {

    int ans, n;
    // Map<Integer, Integer> map = new HashMap<>();
    int[] nums;

    public int maxLength(List<String> arr) {
        //由于每个单词长度一定不大于26,所以可以使用一个二进制位来表示一个字母,一个int值表示一个单词
        //预处理所有单词,去重,去除不符合要求的单词,使用全局变量维护可用单词的int值
        //回溯遍历所有可能的组合情况,并进行剪枝
        Set<Integer> set = new HashSet<>();
        for (String word : arr) {
            int val = 0;
            for (char ch : word.toCharArray()) {
                int p = ch - 'a';
                if (((val >> p) & 1) != 0) { //同一单词有重复字母
                    val = -1;
                    break;
                }
                val |= (1 << p);
            }
            set.add(val);
        }
        n = set.size();
        nums = new int[n]; //全局变量存储每一个可以参与组合的单词
        int idx = 0, total = 0;
        for (int i : set) {
            nums[idx++] = i;
            total |= i;
        }
        int d = Integer.bitCount(total);
        dfs(0, 0, total);
        return ans;
    }

    private void dfs(int idx, int cur, int total) {
        //当前所有已选择的单词值cur与剩余可以选的单词的最大值(与cur不同的1的位数)只和不大于ans,则无需继续递归
        if (Integer.bitCount(total | cur) <= ans) {
            return;
        }
        if (idx == n) {
            ans = Math.max(ans, Integer.bitCount(cur));
            return;
        }
        //当前idx单词与已选择的单词不包含相同字母,可以选择
        if ((nums[idx] & cur) == 0) {
            //cur | nums[idx]: 选择当前idx单词后的位数;
            //total - (total & nums[idx]): 选择当前idx单词后total剩余的可选位数
            dfs(idx + 1, cur | nums[idx], total - (total & nums[idx]));
        }
        //不选择当前单词
        dfs(idx + 1, cur, total);
    }

    public static void main(String[] args) {
        MaxLength maxLength = new MaxLength();
        System.out.println(maxLength.maxLength(List.of("bb","aa")));
//        System.out.println(maxLength.maxLength(List.of("un","iq","ue")));
    }
}

package com.saraad.leetcode.dailycode.april;

import java.util.HashMap;
import java.util.Map;

/**
 * 	#781 森林中的兔子  2021-04-04
 *
 */
public class NumRabbits {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> nums = new HashMap<>();
        for (int i = 0; i < answers.length; i++) {
            nums.put(answers[i], nums.getOrDefault(answers[i], 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : nums.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            //向上取整  兔子总数=该答案兔子种类数量*答案值
            ans += (value + key) / (key + 1) * (key + 1);
        }
        return ans;
    }
}

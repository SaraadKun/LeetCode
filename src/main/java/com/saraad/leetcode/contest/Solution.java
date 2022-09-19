package com.saraad.leetcode.contest;

import com.saraad.leetcode.bean.TreeNode;
import com.saraad.leetcode.dailycode2022.august.LongestIdealString;
import com.saraad.util.JSONUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {

        return root;
    }

    private Trie trie = new Trie();

    public int[] sumPrefixScores(String[] words) {
        int n = words.length;
        int[] ans = new int[n];
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            trie.add(word.toCharArray(), 0);
        }
        for (int i = 0; i < n; i++) {
            ans[i] = getScore(words[i]);
        }
        return ans;
    }

    private int getScore(String prefix) {
        char[] chs = prefix.toCharArray();
        int ans = 0;
        Trie t = trie;
        for (int i = 0; i < chs.length; i++) {
            t = t.children[chs[i] - 'a'];
            ans += t.score;
        }
        return ans;
    }

    static class Trie {

        Trie[] children = new Trie[26];
        int score = 0;

        public void add(char[] chs, int idx) {
            if (idx == chs.length) {
                return;
            }
            int p = chs[idx] - 'a';
            if (children[p] == null) {
                children[p] = new Trie();
            }
            children[p].score++;
            children[p].add(chs, idx + 1);
        }

    }

    public static void main(String[] args) throws IOException {
        Solution obj = new Solution();
        String[] words = mock();
        long start = System.currentTimeMillis();
        int[] ans = obj.sumPrefixScores(words);
        System.out.println("time: " + (System.currentTimeMillis() - start));
        System.out.println(JSONUtil.writeValueAsString(ans));
    }

    private static String[] mock() throws IOException {
        String path = ClassLoader.getSystemResource("").getPath() + "SumPrefixScores";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String str = reader.readLine();
        return JSONUtil.readValue(str, String[].class);
    }

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return Arrays.stream(nums).boxed().sorted((o1, o2) -> map.get(o1) == map.get(o2) ? o2 - o1 : map.get(o1) - map.get(o2))
                .mapToInt(Integer::intValue).toArray();
    }
}
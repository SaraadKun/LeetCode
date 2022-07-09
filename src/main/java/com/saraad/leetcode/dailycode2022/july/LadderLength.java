package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/word-ladder/
 * @Date: 08-07-2022 16:18
 */

public class LadderLength {

    private List<List<Integer>> adj = new ArrayList<>();
    private Map<String, Integer> map = new HashMap<>();
    private int id = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //利用虚拟节点建图 原单词和派生出的虚拟节点间必然存在一条无向的边,对每一对[word, word*]执行边的添加操作
        // 添加边在邻接表中的操作: adj[v].add(w); adj[w].add(v); E++;(此处为每个节点的编号id++)
        for (String word : wordList) {
            //构造虚拟节点并加入邻接表
            addEdges(word);
        }
        addEdges(beginWord);
        if (!map.containsKey(endWord)) {
            return 0;
        }
        int endKey = map.get(endWord);
        //BFS
        int[] dp = new int[id];
        Arrays.fill(dp, -1);
        dp[map.get(beginWord)] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(map.get(beginWord));
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == endKey) {
                return dp[cur] / 2 + 1;
            }
            for (int next : adj.get(cur)) {
                if (dp[next] == -1) {
                    dp[next] = dp[cur] + 1;
                    q.offer(next);
                }
            }
        }
        return 0;
    }

    private void addEdges(String word) {
        //添加当前单词获取节点id,对当前单词构造虚拟节点并将[word, word*]加入邻接表
        int id = addWord(word);
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            //构造虚拟节点
            StringBuilder sb = new StringBuilder(word);
            sb.setCharAt(i, '*');
            int id2 = addWord(sb.toString());
            adj.get(id).add(id2);
            adj.get(id2).add(id);
        }
    }

    private int addWord(String word) {
        if (!map.containsKey(word)) {
            map.put(word, id);
            adj.add(new ArrayList<>());
            return id++;
        }
        return map.get(word);
    }

    public static void main(String[] args) {
        LadderLength ladderLength = new LadderLength();
        System.out.println(ladderLength.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }
}

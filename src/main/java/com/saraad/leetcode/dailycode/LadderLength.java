package com.saraad.leetcode.dailycode;

import java.util.*;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: LadderLength
 * @Package:com.saraad.leetcode.dailycode
 * @Description: 127 单词接龙
 *
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出: 5

解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
返回它的长度 5。
示例 2:

输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: 0

解释: endWord "cog" 不在字典中，所以无法进行转换。
 * @author: saraad
 * @date: 2020/11/5 22:40
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class LadderLength {

    private HashMap<String, Integer> dict = new HashMap<>();
    private List<List<Integer>> graph = new ArrayList<>();
    private int id = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        //边界条件
        if(!dict.containsKey(endWord)){
            return 0;
        }
        int[] dist = new int[id];
        Arrays.fill(dist, -1);
        int beginId = dict.get(beginWord);
        int endId = dict.get(endWord);
        dist[beginId] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(beginId);
        while (!queue.isEmpty()){
            Integer x = queue.poll();
            if (x == endId){
                return dist[x]/2 + 1;
            }
            for (Integer i : graph.get(x)) {
                if (dist[i] == -1){
                    dist[i] = dist[x] + 1;
                    queue.offer(i);
                }
            }

        }
        return 0;

    }

    private int addEdge(String word){
        int id1 = addWord(word); //添加当前word至字典,获取id
        char[] chs = word.toCharArray(); //遍历构造所有临节点并加入字典,以及graph中,构造图
        for (int i = 0; i < chs.length; i++) {
            char tmp = chs[i];
            chs[i] = '*';
            int id2 = addWord(new String(chs));
            graph.get(id1).add(id2);
            graph.get(id2).add(id1);
            chs[i] = tmp;//复原
        }
        return id1;
    }

    private int addWord(String word){
        if (!dict.containsKey(word)){
            dict.put(word, id++);
            graph.add(new LinkedList<>());
        }
        return dict.get(word);
    }

}

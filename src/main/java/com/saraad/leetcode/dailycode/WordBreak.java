package com.saraad.leetcode.dailycode;

import com.alibaba.fastjson.JSON;

import java.awt.peer.WindowPeer;
import java.util.*;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: WordBreak
 * @Package:com.saraad.leetcode.dailycode
 * @Description: 140.单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 *
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 *
 * @author: saraad
 * @date: 2020/11/1 20:33
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class WordBreak {


    public List<String> wordBreak(String s, List<String> wordDict) {
        if (wordDict==null || wordDict.size()<1 || s==null || s.length()<1){
            return Collections.emptyList();
        }
        //字典
        HashSet<String> dict = new HashSet<>(wordDict);
        HashMap<Integer,List<List<String>>> map = new HashMap<>();
        //结果集

        List<List<String>> list = wordBreakRecursion(s, 0, dict, map);
        List<String> res = new ArrayList<>();
        for (List<String> sentence : list) {
            res.add(String.join(" ", sentence));
        }
        return res;
    }

    private List<List<String>> wordBreakRecursion(String str, int beginIndex, HashSet<String> dict, HashMap<Integer,List<List<String>>> map){
        if (!map.containsKey(beginIndex)) {
            LinkedList<List<String>> wordBreaks = new LinkedList<>();
            //beginIndex==str.length,说明成功组成一个句子,添加到结果集中
            if (beginIndex == str.length()) {
                wordBreaks.add(new LinkedList<>());
            }else {
                for (int i = beginIndex+1; i <= str.length(); i++) {
                    //单词合法,则拼接到当前句子中,继续往后找
                    String word = str.substring(beginIndex, i);
                    if (dict.contains(word)) {
                        //寻找下一个索引对应的wordbreaks
                        List<List<String>> nextWordBreak = wordBreakRecursion(str, i, dict, map);
                        for (List<String> list : nextWordBreak) {
                            LinkedList<String> wordBreak = new LinkedList<>(list);
                            wordBreak.offerFirst(word);
                            wordBreaks.add(wordBreak);
                        }
                    }
                }
            }
            map.put(beginIndex,wordBreaks);
        }
        return map.get(beginIndex);
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        //"catsanddog"
        //["cat","cats","and","sand","dog"]
        String s = "catsanddog";
        String dictstr="[\"cat\",\"cats\",\"and\",\"sand\",\"dog\"]";
        List<String> wordDict = JSON.parseArray(dictstr).toJavaList(String.class);
        List<String> list = wordBreak.wordBreak(s, wordDict);
        Set<Integer> res = new HashSet<>();
        for(int i=0,j=0,index=0;i<1&&j<1; ){

        }
        System.out.println(list);
    }
}

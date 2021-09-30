package com.saraad.leetcode.dailycode;

/**
 * @Title: Trie
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/4/14 12:19 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class Trie {

    //子节点
    private Trie[] children;
    //是否有子节点
    private boolean isEnd;

    private static char a = 'a';

    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        isEnd = false; //根节点不为false
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        Trie curr = this;
        for (char ch : chars) {
            Trie[] chs = curr.children;
            int idx = ch - a;
            if (chs[idx] == null) {
                chs[idx] = new Trie();
            }
            curr = chs[idx];
        }
        curr.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        char[] chars = prefix.toCharArray();
        Trie curr = this;
        for (char ch : chars) {
            Trie[] chs = curr.children;
            int idx = ch - a;
            if (chs[idx] == null) {
                return null;
            }
            curr = chs[idx];
        }
        return curr;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayList;
import java.util.List;

class WordFilter {

    Trie preTrie = new Trie();
    Trie sufTrie = new Trie();

    //正反向构建两颗Trie,查找时按前后缀分别查找两棵树,取交集中最大的idx
    public WordFilter(String[] words) {
        int n = words.length;
        for (int i = 0; i < n; i++) {
            char[] chs = words[i].toCharArray();
            preTrie.add(chs, i, true);
            sufTrie.add(chs, i, false);
        }
    }

    public int f(String pref, String suff) {
        List<Integer> idx1 = search(pref.toCharArray(), preTrie, true);
        List<Integer> idx2 = search(suff.toCharArray(), sufTrie, false);
        int n = idx1.size(), m = idx2.size();
        if (n == 0 || m == 0) {
            return -1;
        }
        int i = n - 1, j = m - 1;
        while (i >= 0 && j >= 0) {
            if (idx1.get(i) < idx2.get(j)) {
                j--;
            } else if (idx1.get(i) > idx2.get(j)) {
                i--;
            } else {
                return idx1.get(i);
            }
        }
        return -1;
    }

    private List<Integer> search(char[] chs, Trie cur, boolean dir) {
        int n = chs.length;
        for (int i = dir ? 0 : n - 1; i < n && i >= 0; i += dir ? 1 : -1){
            cur = cur.children[chs[i] - 'a'];
            if (cur == null) {
                return List.of();
            }
        }
        return cur.values;
    }

    static class Trie {

        Trie[] children = new Trie[26];
        boolean isEnd = false;
        List<Integer> values = new ArrayList<>();

        public void add(char[] chs, int index, boolean dir) {
            Trie cur = this;
            int n = chs.length;
            for (int i = dir ? 0 : n - 1; i < n && i >= 0; i += dir ? 1 : -1){
                int idx = chs[i] - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new Trie();
                }
                cur = cur.children[idx];
                cur.values.add(index);
            }
            cur.isEnd = true;
            cur.values.add(index);
        }

    }

}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */
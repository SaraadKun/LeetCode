package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/implement-magic-dictionary/
 * @Date: 11-07-2022 12:44
 */
class MagicDictionary {
    private Trie dict = new Trie();

    public MagicDictionary() {
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            dict.add(word);
        }
    }

    public boolean search(String searchWord) {
        return search(dict, searchWord.toCharArray(), 0, false);
    }

    private boolean search(Trie trie, char[] word, int index, boolean flag) {
        if (index == word.length) {
            return flag && trie.isEnd;
        }
        int idx = word[index] - 'a';
        //加一下非空判断,优先dfs当前位置单词非空的路径
        if (trie.children[idx] != null) {
            if (search(trie.children[idx], word, index + 1, flag)) {
                return true;
            }
        }
        //跳过i = idx的循环,上面已经搜过了
        for (int i = 0; i < 26; i++) {
            if (i == idx || trie.children[i] == null) {
                continue;
            }
            boolean res = search(trie.children[i], word, index + 1, true);
            if (res == true) {
                return true;
            }
        }
        return false;
    }

    static class Trie {

        private Trie[] children = new Trie[26];
        private boolean isEnd = false;

        public Trie(){}

        public void add(String word) {
            Trie cur = this;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new Trie();
                }
                cur = cur.children[idx];
            }
            cur.isEnd = true;
        }

    }

    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary();
//        md.buildDict(new String[] {"hello", "helle"});
        md.buildDict(new String[] {"hello","hallo","leetcode","judge"});
        System.out.println(md.search("judge"));
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
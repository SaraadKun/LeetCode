package com.saraad.leetcode.dailycode2022.july;

import java.util.List;
import java.util.Objects;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 07-07-2022 15:49
 */

public class ReplaceWords {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        dictionary.forEach(trie::add);
        StringBuffer sb = new StringBuffer();
        for (String word : sentence.split(" ")) {
            sb.append(trie.search(word)).append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static void main(String[] args) {
        List<String> dictionary = List.of("cat", "bat", "rat");
        String sentence = "the cattle was rattled by the battery";
        ReplaceWords obj = new ReplaceWords();
        assert Objects.equals("the cat was rat by the bat", obj.replaceWords(dictionary, sentence));
    }

    static class Trie {

        private static final char a = 'a';

        private Trie[] children = new Trie[26];

        private boolean isEnd = false;

        public void add(String root) {
            char[] chs = root.toCharArray();
            Trie cur = this;
            for (char ch : chs) {
                Trie[] children = cur.children;
                int idx = ch - a;
                if (children[idx] == null) {
                    children[idx] = new Trie();
                }
                cur = children[idx];
            }
            cur.isEnd = true;
        }

        //若不存在词根则返回原单词
        public String search(String word) {
            char[] chs = word.toCharArray();
            Trie cur = this;
            StringBuffer sb = new StringBuffer();
            for (char ch : chs) {
                if (cur.isEnd) {
                    return sb.toString();
                }
                Trie[] children = cur.children;
                int idx = ch - a;
                if (children[idx] == null) {
                    break;
                }
                sb.append(ch);
                cur = children[idx];
            }
            return cur.isEnd ? sb.toString() : word;
        }

    }
}

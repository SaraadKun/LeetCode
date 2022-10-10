package com.saraad.leetcode.dailycode2022.october;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 05-10-2022 00:41
 */

public class SubdomainVisits {

    Trie<String, Trie> trie = new Trie<String, Trie>();
    List<String> ans = new ArrayList<>();

    public List<String> subdomainVisits(String[] cpdomains) {
        for (String cpdomain : cpdomains) {
            String[] arr = cpdomain.split(" ");
            int n = Integer.parseInt(arr[0]);
            String[] words = arr[1].split("\\.");
            add(trie, words, words.length - 1, n);
        }
        statistic(trie, "");
        return ans;
    }

    private void statistic(Trie<String, Trie> t, String suffix) {
        if (t.size() == 0) {
            return;
        }
        for (Map.Entry<String, Trie> entry : t.entrySet()) {
            String str = "".equals(suffix) ? entry.getKey() : entry.getKey() + "." + suffix;
            Trie tt = entry.getValue();
            ans.add(tt.cnt + " " + str);
            statistic(tt, str);
        }
    }

    public void add(Trie<String, Trie> t, String[] words, int idx, int n) {
        if (idx < 0) { return; }
        String key = words[idx];
        Trie<String, Trie> tt = t.get(key);
        if (tt == null) {
            tt = new Trie<>();
            t.put(key, tt);
        }
        tt.cnt += n;
        add(tt, words, --idx, n);
    }

    static class Trie<K, V> extends HashMap<K, V> {

        public int cnt = 0;

    }
}

package com.saraad.leetcode.dailycode2022.may;

import java.util.HashMap;
import java.util.Map;

class IsAlienSorted {

    public static void main(String[] args) {
        Map.of("", 1);
        IsAlienSorted instance = new IsAlienSorted();
        String[] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        boolean ans = instance.isAlienSorted(words, order);
        System.out.println(ans);
    }

    Map<Character, Integer> dict = new HashMap<>();
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++) {
            dict.put(order.charAt(i), i);
        }
        for (int i = 1; i < words.length; i++) {
            if (!valid(words[i - 1], words[i])) {
                return false;
            }
        }
        return true;

    }

    private boolean valid(String first, String second) {
        int n1 = first.length(), n2 = second.length();
        for (int i = 0; i < n1 && i < n2; i++) {
            char ch1 = first.charAt(i);
            char ch2 = second.charAt(i);
            if (ch1 == ch2) {
                continue;
            }
            return dict.get(ch1) <= dict.get(ch2);
        }
        return n1 <= n2;
    }
}
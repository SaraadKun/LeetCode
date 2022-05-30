package com.saraad.leetcode.dailycode;

import java.util.*;
import java.util.stream.Stream;

/**
 * 678
 */
public class CheckValidString {

    public boolean checkValidString(String s) {
        Deque<Integer> lstack = new LinkedList<>();
        Deque<Integer> rstack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ('(' == ch) {
                lstack.push(i);
            } else if ('*' == ch) {
                rstack.push(i);
            } else {
                if (!lstack.isEmpty()) {
                    lstack.pop();
                } else if (!rstack.isEmpty()) {
                    rstack.pop();
                } else {
                    return false;
                }
            }
        }
        while (!lstack.isEmpty() && !rstack.isEmpty()) {
            if (lstack.pop() > rstack.pop()) {
                return false;
            }
        }
        Map<Integer, Set<Integer>>[] arr = new Map[7];
        int sum = Arrays.stream(arr).map(Map::values).flatMap(Collection::stream).map(Set::size).filter(n -> n > 1).mapToInt(n -> n * (n - 1)).sum();
        Stream<Collection<Set<Integer>>> stream = Arrays.stream(arr).map(Map::values);
        Stream<Set<Integer>> setStream = stream.flatMap(Collection::stream);
        List<int[]> list;
        return lstack.isEmpty();
    }

}

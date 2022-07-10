package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 10-07-2022 22:18
 */

public class IsValid {

    public boolean isValid(String s) {
        Map<Character, Character> map = Map.of('(', ')', '[', ']', '{', '}');
        Deque<Character> stack = new ArrayDeque<>();
        for(Character ch : s.toCharArray()) {
            if (!stack.isEmpty() && map.get(stack.peek()) == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    private Map<Character, Character> initDict() {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        return map;
    }


    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        System.out.println(isValid.isValid("()"));
        System.out.println(isValid.isValid("()[]{}"));
        System.out.println(isValid.isValid("(]"));
        System.out.println(isValid.isValid("([)]"));
        System.out.println(isValid.isValid("{[]}"));
        System.out.println(isValid.isValid(""));
    }

}

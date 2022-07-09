package com.saraad.leetcode.temp;


import com.saraad.util.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 *  Given a "flatten" dictionary object, whose keys are dot-separated.
 *  For example, {"A":1,"B.A":2,"B.B":3,"CC.D.E":4,"CC.D.F":5}.
 *  Implement a function to transform it to a "nested" dictionary object.
 *  In the above case,the nested version is like:
 *  {
 *      "A": 1,
 *      "B":
 *      {
 *          "A": 2,
 *          "B": 3
 *      },
 *      "CC":
 *      {
 *          "D":
 *          {
 *              "E": 4,
 *              "F": 5
 *          }
 *      }
 *  }
 *  It's guaranteed that no keys in dictionary are prefixes of other keys.
 *
 * @Author: Saraad
 * @Link: url
 * @Date: 09-07-2022 13:27
 */

public class CodeFlatJson {

    public String transformJson(Map<String, Object> dict) {
        //build the trie
        Trie trie = new Trie();
        dict.forEach((key, value) -> trie.add(key.split("\\."), 0, value));
        //format the json
        Map<String, Object> map = trie.toMap();
        return JSONUtil.writeValueAsString(map);
    }

    static class Trie {

        private Map<String, Trie> children = new HashMap<>();

        private Object value; //only if isEnd == true, value is not null

        private boolean isEnd = false; //mark the end of the keys

        public Trie(){}

        public void add(String[] keys, int index, Object value) {
            if (index == keys.length) {
                isEnd = true;
                this.value = value;
                return;
            }
            String key = keys[index];
            Trie node = children.get(key);
            if (node == null) {
                node = new Trie();
                children.put(key, node);
            }
            node.add(keys, index + 1, value);
        }

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            children.forEach((key, node) -> {
                if (node.isEnd) {
                    map.put(key, node.value);
                } else {
                    map.put(key, node.toMap());
                }
            });
            return map;
        }

    }

    public static void main(String[] args) {
        Map<String, Object> dict = mock();
        System.out.println(JSONUtil.writeValueAsString(dict));
        CodeFlatJson obj = new CodeFlatJson();
        System.out.println(obj.transformJson(dict));
    }

    private static Map<String, Object> mock() {
        Map<String, Object> dict = new HashMap<>();
        dict.put("A", 1);
        dict.put("B.A", 2);
        dict.put("B.B", 3);
        dict.put("CC.D.E", 4);
        dict.put("CC.D.F", 5);
        return dict;
    }

}

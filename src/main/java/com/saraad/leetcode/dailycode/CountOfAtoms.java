package com.saraad.leetcode.dailycode;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Title: CountOfAtoms
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/7/5 2:40 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class CountOfAtoms {

    /**
     * 给定一个化学式formula（作为字符串），返回每种原子的数量。
     *
     * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
     * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
     * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
     * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
     * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，
     * 跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
     * @param formula
     * @return
     */
    public String countOfAtoms(String formula) {
        Map<String, Integer> res = new HashMap<>(); //结果集map
        Map<String, Integer> cur = new HashMap<>(); //当前基团的map
        Deque<Map<String, Integer>> stack = new LinkedList<>(); //计算基团的栈
        int n = 0, pre = 0; //当前原子/基团数字计数
        StringBuilder curAtom = new StringBuilder(); //当前原子
        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            if (ch >= 65 && ch <= 90) { //大写字符
                if((pre >= 65 && pre <= 90) || (pre >= 97 && pre <= 122)) { //前一个为单原子且n=1
                    String preAtom = curAtom.toString();
                    cur.put(preAtom, cur.getOrDefault(preAtom, 0) + 1);
                    curAtom.delete(0, curAtom.length());
                } else if (pre >= 49 && pre <= 57){  //前一个为数字
                    String preAtom = curAtom.toString();
                    cur.put(preAtom, cur.getOrDefault(preAtom, 0) + n);
                    curAtom.delete(0, curAtom.length());
                } else if (pre == 40){  /// '('
                    curAtom.append(ch);
                } else if (pre == 41) {
                    Map<String, Integer> tmp = stack.pop();
                    int k = n == 0 ? 1 : n;
                    cur.forEach((key, val) -> {
                        Integer out = tmp.getOrDefault(key, 0);
                        tmp.put(key, out + val * k);
                    });
                    cur = tmp;
                    curAtom.append(ch);
                }
            } else if (ch >= 97 && ch <= 122) { //小写字符
                curAtom.append(ch);
                pre = curAtom.charAt(curAtom.length() - 1);
            } else if (ch >= 49 && ch <= 57) {//数字
                n = n * 10 + ch - 48;
            } else if (ch == 40) { // '('
                if((pre >= 65 && pre <= 90) || (pre >= 97 && pre <= 122)) { //前一个为单原子且n=1
                    String preAtom = curAtom.toString();
                    int k = n == 0 ? 1 : n;
                    cur.put(preAtom, cur.getOrDefault(preAtom, 0) + k);
                    curAtom.delete(0, curAtom.length());
                    stack.push(cur); //外层map压栈
                    cur = new HashMap<>();
                } else if (pre == 40){  /// '('
                    stack.push(cur); //外层map压栈
                    cur = new HashMap<>();
                } else  if (pre == 41) {
                    int k = n == 0 ? 1 : n;
//                    stack.
                }
                pre = 40;

            } else if (ch == 41) { // ')'

            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println((int)'(');
        System.out.println((int)')');
        System.out.println(('9' - 48));
        System.out.println((char) 97);
    }

}

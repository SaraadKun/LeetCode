package com.saraad.structure.test;

import com.saraad.structure.RedBlackBST;

import java.util.ArrayList;
import java.util.List;

public class RedBlackBSTTest {

    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> bst = new RedBlackBST<>();
        for (int i = 1; i < 200; i++) {
            bst.put(i, i);
        }
        System.out.println(bst);
        System.out.println("======================================");
        ArrayList<Integer> list = new ArrayList<Integer>();
        System.out.println(bst.size());
        for (int i = 0; i < 50; i++) {
            int n = (int)Math.floor(Math.random() * 200);
            list.add(n);
            bst.delete(n);
        }
        System.out.println(bst);
        System.out.println(bst.size());
        System.out.println(list.toString());
    }



}

package com.saraad.structure.test;

import com.saraad.structure.RedBlackBST;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RedBlackBSTTest {

    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> bst = new RedBlackBST<>();
        for (int i = 1; i < 200; i++) {
            bst.put(i, i);
        }
        System.out.println(bst);
        System.out.println("======================================");
        HashSet<Integer> set = new HashSet<Integer>();
        int preSize = bst.size();
        System.out.println(preSize);
        System.out.println("======================================");
        for (int i = 0; i < 50; i++) {
            int n = (int)Math.floor(Math.random() * 200);
            set.add(n);
            bst.delete(n);
        }
        System.out.println(bst);
        System.out.println(bst.size());
        System.out.println(set.toString());
        System.out.println(set.size());
        Assert.assertEquals(preSize - set.size(), bst.size());
    }



}

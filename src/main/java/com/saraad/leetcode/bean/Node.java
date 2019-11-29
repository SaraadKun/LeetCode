package com.saraad.leetcode.bean;

import lombok.Data;

/**
 * @Title: Node
 * @Package:com.saraad.leetcode.bean
 * @Description:
 * @author: saraad
 * @date: 2019/11/28 5:42 下午
 * @Copyright: 2019  Inc. All rights reserved.
 */
@Data
public class Node {

    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }
}

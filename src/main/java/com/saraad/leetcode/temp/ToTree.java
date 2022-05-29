package com.saraad.leetcode.temp;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
class Node {
    String from;
    String to;

    public static Node newNode(String from, String to) {
        return new Node(from, to);
    }
}

@Data
@NoArgsConstructor
class Tree {
    String val;
    List<Tree> children;

    public Tree(String val) {
        this.val = val;
    }
}

public class ToTree {

    public static void main(String[] args) {
        List<Node> data = getData();
        Map<String, List<Node>> fMap = data.stream().collect(Collectors.groupingBy(Node::getFrom));
        List<String> right = data.stream().map(Node::getTo).collect(Collectors.toList());
        String rootVal = fMap.keySet().stream().filter(o -> !right.contains(o)).findFirst().get();
        Tree root = new Tree();
        root.setVal(rootVal);
        list2Tree(root, fMap);
        System.out.println(JSON.toJSONString(root));
    }

    private static void list2Tree(Tree root, Map<String, List<Node>> fMap) {
        //省略判空
        String rootVal = root.getVal();
        List<Node> nodes = fMap.get(rootVal);
        if (nodes == null){
            return;
        }
        List<Tree> children = new ArrayList<>();
        for (Node node : nodes) {
            Tree tree = new Tree(node.getTo());
            list2Tree(tree, fMap);
            children.add(tree);
        }
        root.setChildren(children);
    }

    static List<Node> getData() {
        Node node1 = Node.newNode("1", "2");
        Node node2 = Node.newNode("2", "4");
        Node node3 = Node.newNode("1", "5");
        return Arrays.asList(node1, node2, node3);
    }

}


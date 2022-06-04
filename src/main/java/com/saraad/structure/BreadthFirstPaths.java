package com.saraad.structure;

import java.util.*;

public class BreadthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s; //the beginning of the path

    public BreadthFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        marked[s] = true; //标记起始节点
        queue.offer(s); //当前节点放入队列
        while (!queue.isEmpty()) {
            int cur = queue.poll(); //取出队列中的节点
            //取出当前节点的所有下一个节点
            for (Integer next : G.adj(cur)) {
                if (!marked[next]) {
                    edgeTo[next] = cur;//标记边
                    marked[next] = true; //标记已访问过的节点
                    queue.offer(next); //将未被标记的下一个节点放入队列
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    //返回的为最短路径
    public Iterable<Integer> pathTo(int v) {
        //不存在可达路径直接返回null
        if (!hasPathTo(v)) {
            return null;
        }
        //存在可达路径 直接从v开始遍历edgeTo数组
        Stack<Integer> ans = new Stack<>();
        for (int cur = v; cur != s; cur = edgeTo[cur]) {
            ans.push(cur);
        }
        ans.push(s);
        return ans;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3};
        Arrays.sort(arr, Comparator.reverseOrder());
        HashSet<Object> set = new HashSet<>();
        set.add(1);
        set.contains(1);
    }

}

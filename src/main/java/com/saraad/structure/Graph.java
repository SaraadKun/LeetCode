package com.saraad.structure;

public class Graph {

    private final int V;

    private int E;

    private Bag<Integer>[] adj;

    public Graph(int v) {
        this.V = v;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}

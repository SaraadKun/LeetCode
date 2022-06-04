package com.saraad.structure;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {

    private Node<T> first; // beginning of bag
    private int n; //size of bag

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Bag() {
    }

    public void add(T item) {
        Node<T> oldfirst = first;
        first = new Node<T>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }


    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(first);
    }

    private static class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}

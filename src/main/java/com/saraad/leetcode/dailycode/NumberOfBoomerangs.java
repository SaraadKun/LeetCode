package com.saraad.leetcode.dailycode;

import java.util.*;

public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer, Integer>[] arr = new Map[points.length];
        for (int i = 0; i < points.length; i++) {
            arr[i] = new HashMap<>();
        }
        for(int i = 0; i < points.length; i++) {
            int[] p1 = points[i];
            for (int[] p2 : points) {
                int distance = distanceSquare(p1, p2);
                arr[i].put(distance, arr[i].getOrDefault(distance, 0) + 1);
            }
        }
        return Arrays.stream(arr).map(Map::values).flatMap(Collection::stream).filter(n -> n > 1).mapToInt(n -> n * (n - 1)).sum();
    }

    private int distanceSquare(int[] arr1, int[] arr2) {
        int dx = arr1[0] - arr2[0];
        int dy = arr1[1] - arr2[1];
        return dx * dx + dy * dy;
    }

    public int numberOfBoomerangs2(int[][] points) {
        Map<Integer, Set<Integer>>[] arr = new Map[points.length];
        for (int i = 0; i < points.length; i++) {
            arr[i] = new HashMap<>();
        }
        for(int i = 0; i < points.length - 1; i++) {
            for(int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                int distance = distanceSquare(p1, p2);
                Set<Integer> pi = arr[i].getOrDefault(distance, new HashSet<>());
                pi.add(j);
                arr[i].put(distance, pi);
                Set<Integer> pj = arr[j].getOrDefault(distance, new HashSet<>());
                pj.add(i);
                arr[j].put(distance, pj);
            }
        }
        return  Arrays.stream(arr).map(Map::values).flatMap(Collection::stream).map(Set::size).filter(n -> n > 1).mapToInt(n -> n * (n - 1)).sum();
    }

    public static void main(String[] args) {
        NumberOfBoomerangs instance = new NumberOfBoomerangs();
        int[][] points = {{0,0},{1,0},{-1,0},{0,1},{0,-1}};
        int i = instance.numberOfBoomerangs(points);
        System.out.println(i);
        int j = instance.numberOfBoomerangs2(points);
        System.out.println(j);
    }
//        String str = "[[0,0],[1,0],[-1,0],[0,1],[0,-1]]";
//        String replace = str.replace("[", "{").replace("]", "}");
//        System.out.println(replace);

}

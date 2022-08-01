package com.saraad.leetcode.dailycode2022.august;

import com.saraad.util.JSONUtil;

public class MyCircularQueue {

    int[] q;
    int N, head, tail, size;
    public MyCircularQueue(int k) {
        this.N = k;
        this.q = new int[N];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    
    public boolean enQueue(int value) {
        if (isFull()) { 
            return false;
        }
        if (!isEmpty()) {
            tail = (tail + 1) % N;
        }
        q[tail] = value;
        size++;
        return true;
    }
    
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        size--;
        if (!isEmpty()) {
            head = (head + 1) % N;
        }
        return true;
    }
    
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return q[head];
    }
    
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return q[tail];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == N;
    }

    public static void main(String[] args) {
//        ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","deQueue","deQueue","isEmpty","isEmpty","Rear","Rear","deQueue"]
//        [[8],[3],[9],[5],[0],[],[],[],[],[],[],[]]
        String[] operations = mockOps();
        int[][] params = mockArgs();
        test(operations, params);

    }

    private static String[] mockOps() {
//        String json = "[\"MyCircularQueue\",\"enQueue\",\"enQueue\",\"enQueue\",\"enQueue\",\"deQueue\",\"deQueue\",\"isEmpty\",\"isEmpty\",\"Rear\",\"Rear\",\"deQueue\"]";
        String json = "[\"MyCircularQueue\",\"enQueue\",\"deQueue\",\"enQueue\",\"enQueue\",\"deQueue\",\"isFull\",\"isFull\",\"Front\",\"deQueue\",\"enQueue\",\"Front\",\"enQueue\",\"enQueue\",\"Rear\",\"Rear\",\"deQueue\",\"enQueue\",\"enQueue\",\"Rear\",\"Rear\",\"Front\",\"Rear\",\"Rear\",\"deQueue\",\"enQueue\",\"Rear\",\"deQueue\",\"Rear\",\"Rear\",\"Front\",\"Front\",\"enQueue\",\"enQueue\",\"Front\",\"enQueue\",\"enQueue\",\"enQueue\",\"Front\",\"isEmpty\",\"enQueue\",\"Rear\",\"enQueue\",\"Front\",\"enQueue\",\"enQueue\",\"Front\",\"enQueue\",\"deQueue\",\"deQueue\",\"enQueue\",\"deQueue\",\"Front\",\"enQueue\",\"Rear\",\"isEmpty\",\"Front\",\"enQueue\",\"Front\",\"deQueue\",\"enQueue\",\"enQueue\",\"deQueue\",\"deQueue\",\"Front\",\"Front\",\"deQueue\",\"isEmpty\",\"enQueue\",\"Rear\",\"Front\",\"enQueue\",\"isEmpty\",\"Front\",\"Front\",\"enQueue\",\"enQueue\",\"enQueue\",\"Rear\",\"Front\",\"Front\",\"enQueue\",\"isEmpty\",\"deQueue\",\"enQueue\",\"enQueue\",\"Rear\",\"deQueue\",\"Rear\",\"Front\",\"enQueue\",\"deQueue\",\"Rear\",\"Front\",\"Rear\",\"deQueue\",\"Rear\",\"Rear\",\"enQueue\",\"enQueue\",\"Rear\",\"enQueue\"]";

        return JSONUtil.readValue(json, String[].class);
    }

    private static int[][] mockArgs() {
//        String json = "[[8],[3],[9],[5],[0],[],[],[],[],[],[],[]]";
        String json = "[[81],[69],[],[92],[12],[],[],[],[],[],[28],[],[13],[45],[],[],[],[24],[27],[],[],[],[],[],[],[88],[],[],[],[],[],[],[53],[39],[],[28],[66],[17],[],[],[47],[],[87],[],[92],[94],[],[59],[],[],[99],[],[],[84],[],[],[],[52],[],[],[86],[30],[],[],[],[],[],[],[45],[],[],[83],[],[],[],[22],[77],[23],[],[],[],[14],[],[],[90],[57],[],[],[],[],[34],[],[],[],[],[],[],[],[49],[59],[],[71]]";
        return JSONUtil.readValue(json, int[][].class);
    }

    private static void test(String[] operations, int[][] args) {
        MyCircularQueue obj = null;
        for (int i = 0; i < operations.length; i++){
            String operation = operations[i];
            switch (operation) {
                case Operation.INIT -> obj = new MyCircularQueue(args[i][0]);
                case Operation.ENQUEUE -> System.out.println(obj.enQueue(args[i][0]));
                case Operation.DEQUEUE -> System.out.println(obj.deQueue());
                case Operation.FRONT -> System.out.println(obj.Front());
                case Operation.REAR -> System.out.println(obj.Rear());
                case Operation.IS_EMPTY -> System.out.println(obj.isEmpty());
                case Operation.IS_FULL -> System.out.println(obj.isFull());
                default -> System.out.println("Invalid operation");
            }
        }
    }

    interface Operation {
        String INIT = "MyCircularQueue",
                ENQUEUE = "enQueue",
                DEQUEUE = "deQueue",
                FRONT = "Front",
                REAR = "Rear",
                IS_EMPTY = "isEmpty",
                IS_FULL = "isFull";
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
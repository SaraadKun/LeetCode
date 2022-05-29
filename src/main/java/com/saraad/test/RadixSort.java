package com.saraad.test;

import com.alibaba.fastjson.JSON;

import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Title: RadixSort
 * @Package:com.saraad.test
 * @Description:
 * @author: saraad
 * @date: 2021/6/23 1:10 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class RadixSort {

    public static void main(String[] args) throws Exception {
//        RadixSort sort = new RadixSort();
//        int[] arr = {1,21,1543,12,14,22,5,251,1542};
//        int[] sort1 = sort.sort(arr);
//        System.out.println(JSON.toJSONString(arr));
//        System.out.println(JSON.toJSONString(sort1));


    }


    //        int[] a = {2,1,5,6,2,3};
    //        int k = 13;
    //        Integer res  = new RadixSort().resolve(a, k);
    //        System.out.println(res);
    public int resolve(int[] a, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int sum = 0;
        for (int i : a) {
            sum += i;
            queue.offer(i);
        }
        int diff = sum - k;
        Integer res = Integer.MAX_VALUE;
        for (int i = 0; i < diff; i++) {
            Integer cur = queue.poll();
            cur -= 1;
            queue.offer(cur);
            res = queue.peek();
        }
        //res=Integer.MAX_VALUE 代表没有符合要求的解
        return res;
    }

    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxDigit = getMaxDigit(arr);
        return radixSort(arr, maxDigit);
    }

    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLenght(maxValue);
    }

    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    protected int getNumLenght(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

    private int[] radixSort(int[] arr, int maxDigit) {
        int nmod = 10;
        int dev = 1;
        int mod = nmod;

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
//            int[][] counter = new int[mod * 2][0];
            int[][] counter = new int[nmod * 2][0];

            for (int j = 0; j < arr.length; j++) {
//                int bucket = ((arr[j] % mod) / dev) + mod;
                int bucket = ((arr[j] % mod) / dev);
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }

        return arr;
    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

}

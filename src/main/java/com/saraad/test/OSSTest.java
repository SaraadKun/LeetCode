package com.saraad.test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Title: OSSTest
 * @Package:com.saraad.test
 * @Description:
 * @author: saraad
 * @date: 2021/7/29 2:55 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class OSSTest {

    public static void main(String[] args) throws Exception {
//        String ossfile = "https://upload.techgp.cn/production/2021/7/29/16275416553442.txt";
////        String ossfile = "/opt/data/files/1.txt";
//        File file = new File(ossfile);
//        FileInputStream in = new FileInputStream(file);
//        InputStreamReader read = new InputStreamReader(in);
//        BufferedReader reader=new BufferedReader(read);
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null){
//            sb.append(line);
//        }
//        read.close();
//        System.out.println(sb.toString());
        int[] arr = {1,1,1,1,0,0,0};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(list.indexOf(0));
        HashMap<Integer, int[]> map = new HashMap<>();
//        int[] ints = Arrays.copyOf(arr, 1);
    }
    public int[] kWeakestRows(int[][] mat, int k) {
        Map<Integer, Integer> cnts = new HashMap<>();
        for (int i = 0; i < mat.length; i++) {
            cnts.put(i, count(mat[i]));
        }

        int[] res = cnts.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue))
                .limit(k).mapToInt(Map.Entry::getKey).toArray();
        return res;
    }

    public int count(int[] arr) {
        int cnt = 0;
        for (int i : arr) {
            if (i == 0) {
                return cnt;
            }
            cnt++;
        }
        return cnt;
    }

}

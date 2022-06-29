package com.saraad.leetcode.dailycode2022.june;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/encode-and-decode-tinyurl/
 * @Date: 29-06-2022 17:55
 */

public class EncodeAndDecodeTinyurl {

//    private volatile int seq = 0;
//    Map<String, String> map = new HashMap<>();
//    final String base = "http://tinyurl.com/";
//    final char[] dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
//
//    // Encodes a URL to a shortened URL.
//    public String encode(String longUrl) {
//        int id = seq++;
//        String suffix = getSuffix(id);
//        map.put(suffix, longUrl);
//        return base + suffix;
//    }
//
//    // Decodes a shortened URL to its original URL.
//    public String decode(String shortUrl) {
//        return map.get(shortUrl.replace(base, ""));
//    }
//
//    private String getSuffix(int id) {
//        //不考虑溢出情况
//        char[] chs = new char[6];
//        int idx = 5;
//        while (idx >= 0 && id > 0) {
//            chs[idx--] = dict[id % 61];
//            id /= 61;
//        }
//        for (; idx >= 0; idx--) {
//            chs[idx] = '0';
//        }
//        return new String(chs);
//    }

    //随机hash实现
    Map<String, String> db = new HashMap<>();
    Map<String, String> cache = new HashMap<>();
    Random rd = new Random();
    final int k = 6;
    final String base = "http://tinyurl.com/";
    final char[] dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (cache.containsKey(longUrl))
            return base + cache.get(longUrl);
        String shortUrl;
        do {
            char[] chs = new char[k];
            for (int i = 0; i < k; i++) {
                chs[i] = dict[rd.nextInt(dict.length)];
            }
            shortUrl = new String(chs);
        } while(db.containsKey(shortUrl));
        db.put(shortUrl, longUrl);
        cache.put(longUrl, shortUrl);
        return base + shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return db.get(shortUrl.substring(shortUrl.lastIndexOf("/") + 1));
    }

    public static void main(String[] args) {
        Integer n = 0x3fffffff;
        System.out.println(n);
        String str = Integer.toBinaryString(n);
        int j = 0;
        for (int i = 1; i < str.length(); i++) {
            if ((i & 3) == 0) {
                System.out.printf("%s ", str.substring(j, i));
                j = i;
            }
        }
        System.out.println(str.substring(j));
    }
}

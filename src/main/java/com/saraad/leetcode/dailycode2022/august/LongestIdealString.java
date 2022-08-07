package com.saraad.leetcode.dailycode2022.august;

import java.io.*;
import java.util.Arrays;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 07-08-2022 12:30
 */

public class LongestIdealString {
    public int longestIdealString(String s, int k) {
        char[] chs = s.toCharArray();
        int n = chs.length, max = 0;
        int[] cnt = new int[26];
        for (char c : chs) {
            for (int i = Math.max(0, c - 'a' - k); i <= Math.min(25, c - 'a' + k); i++) {
                cnt[c - 'a'] = Math.max(cnt[c - 'a'], cnt[i]);
            }
            max = Math.max(max, ++cnt[c - 'a']);
        }
        return max;
    }
    //		int count[] = new int[26], max = 0;
    //		for (char c : s.toCharArray()) {
    //			for (int i = Math.max('a', c - k) - 'a'; i <= Math.min('z', c + k) - 'a'; i++) {
    //				count[c - 'a'] = Math.max(count[c - 'a'], count[i]);
    //			}
    //			max = Math.max(max, ++count[c - 'a']);
    //		}
    //		return max;

    public static void main(String[] args) throws IOException {
        LongestIdealString lis = new LongestIdealString();
        Data data = mock();
        System.out.println(data.str);
        System.out.println(data.k);
        System.out.println(lis.longestIdealString(data.str, data.k));
    }

    private static Data mock() throws IOException {
        String path = ClassLoader.getSystemResource("").getPath() + LongestIdealString.class.getSimpleName();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String str = reader.readLine();
        str = str.substring(1, str.length() - 1);
        int k = Integer.parseInt(reader.readLine());
        return Data.of(str, k);
    }

    static class Data {
        String str;
        int k;

        static Data of(String str, int k) {
            return new Data(str, k);
        }

        Data(String str, int k) {
            this.str = str;
            this.k = k;
        }
    }
}

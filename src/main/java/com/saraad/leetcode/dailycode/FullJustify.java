package com.saraad.leetcode.dailycode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: FullJustify
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/9/9 9:50 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class FullJustify {

    public static void main(String[] args) {
        FullJustify instance = new FullJustify();
//        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
//        String[] words = {"What","must","be","acknowledgment","shall","be"};
        String[] words = {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
        int maxWidth = 16;
        List<String> res = instance.fullJustify(words, maxWidth);
        res.forEach(System.out::println);
    }

    /**
     * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
     * 单词是指由非空格字符组成的字符序列。
     * 每个单词的长度大于 0，小于等于maxWidth。
     * 输入单词数组 words至少包含一个单词
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<String>> text = new ArrayList<>();
        int curLen = 0;
        List<String> line = new ArrayList<>();
        for (String word : words) {
            int n = word.length();
            if (curLen + n > maxWidth) {
                text.add(line);
                line = new ArrayList<>();
                curLen = n + 1;
            } else {
                //单词之间至少留一个的空格
                curLen = curLen + n + 1;
            }
            line.add(word);
        }
        //最后一行
        text.add(line);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < text.size() - 1; i++) {
            List<String> curLine = text.get(i);
            int wordWidth = curLine.stream().mapToInt(String::length).sum();
            int n = curLine.size();
            int remain = n > 1 ? (maxWidth - wordWidth) % (n - 1) : maxWidth - wordWidth;
            int spaceCnt = n > 1 ? (maxWidth - wordWidth) / (n - 1) : 0;
            StringBuilder sb = new StringBuilder(curLine.get(0));
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < spaceCnt; k++) {
                    sb.append(" ");
                }
                if (remain > 0) {
                    sb.append(" ");
                    remain--;
                }
                sb.append(curLine.get(j));
            }
            for (; remain > 0; remain--) {
                sb.append(" ");
            }
            res.add(sb.toString());
        }
        //处理最后一行
        List<String> lastLine = text.get(text.size() - 1);
        int spaceCnt = maxWidth;
        StringBuilder sb = new StringBuilder();
        for (String word : lastLine) {
            sb.append(word);
            spaceCnt -= word.length();
            if (spaceCnt > 0) {
                sb.append(" ");
                spaceCnt -= 1;
            }
        }
        for (int i = 0; i < spaceCnt; i++) {
            sb.append(" ");
        }
        res.add(sb.toString());
        return res;
    }

}

package test;

import java.util.*;

/**
 * @Title: TestDemo
 * @Package:test
 * @Description:
 * @author: saraad
 * @date: 2019/11/29 6:00 下午
 * @Copyright: 2019  Inc. All rights reserved.
 */
public class TestDemo {



    final static String KEY_SEPARATOR = "|";
    final static String KEY_SEPARATOR_SPLIT = "\\|";
    public static void main(String[] args) {

        String a = "123";
        String b = "2asfasfa";
        String c = "2012-02-04";
        String s = a + KEY_SEPARATOR + b+ KEY_SEPARATOR + c;
        System.out.println(s);
        Arrays.stream(s.split(KEY_SEPARATOR_SPLIT)).forEach(System.out::println);

    }

    public static int test(String s){
        if (s != null && s.length() > 0) {
            char[] chars = s.toCharArray();
            int length = chars.length;
            List<Integer> list = new ArrayList<>();
            HashMap<Object, Integer> map = new HashMap<>();
            int lastIndex = 0;//记录最后一个相同的时候的下标
            int index = 0;//用于记录每一个的下标
            int max = -1;
            for (int i = 0; i < chars.length; i++) {
                if (map.containsKey(chars[i])) {
                    int oldIndex = map.get(chars[i]);
                    int candidate = index - oldIndex;
                    //记录一下最后一个相同的index
                    lastIndex = index;
                    max = max > candidate ? max : candidate;
                }
                map.put(chars[i], index);
                index++;
                //i是最后一个的时候
                if (i == chars.length - 1) {
                    int candidate = index - lastIndex;
                    max = max > candidate ? max : candidate;
                }
            }
            return max;
        }
        return 0;

    }

}

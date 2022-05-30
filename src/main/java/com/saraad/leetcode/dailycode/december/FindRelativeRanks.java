package com.saraad.leetcode.dailycode.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindRelativeRanks {
    public String[] findRelativeRanks(int[] score) {
        String[] dict = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            list.add(new int[]{i, score[i]});
        }
        list.sort((arr1, arr2) -> arr2[1] - arr1[1]);
        for (int i = 0; i < list.size(); i++) {
            list.get(i)[1] = i;
        }
        return list.stream().sorted(Comparator.comparingInt(arr -> arr[0]))
                .map(arr -> {
                    if (arr[1] < 3) {
                        return dict[arr[1]];
                    }
                    return String.valueOf(arr[1] + 1);
                }).collect(Collectors.toList()).toArray(new String[]{});

    }
}

package com.saraad.leetcode.bean;

import lombok.Data;

import java.util.List;

/**
 * @Title: Demo
 * @Package:com.saraad.leetcode.bean
 * @Description:
 * @author: saraad
 * @date: 2019/12/4 4:36 下午
 * @Copyright: 2019  Inc. All rights reserved.
 */
@Data
public class Demo {

    private Long id;
    private String column1;
    private String column2;
    private List<data> channel;

}

@Data
class data {

    private String name;

    private List<String> number;

}

package com.qihao.beans;

import javax.inject.Inject;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/25 16:49
 */
public class Student {

    @Inject
    User user;


    private String name = "我叫小明……";

    @Inject
    public Student() {
    }

    public String getName() {
        return name+"===="+user.getName();
    }
}

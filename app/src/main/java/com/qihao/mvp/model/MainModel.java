package com.qihao.mvp.model;


import com.qihao.mvp.contract.MainContract;

import javax.inject.Inject;


public class MainModel implements MainContract.Model {

    @Inject
    public MainModel() {
    }

    public String returnMessage() {
        return "shuai_hao";
    }
}

package com.qihao.mvp.activity;

import android.os.Bundle;

import com.qihao.beans.Student;
import com.qihao.mvp.base.BaseActivity;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import qi.school.R;

public class SplashActivity extends BaseActivity {

    @Inject
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Logger.i("对象2:"+student.toString());
    }

    @Override
    public int setLayout() {
        return 0;
    }

    @Override
    public void init() {

    }

    @Override
    public void toast(CharSequence message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showErrors(String msg) {

    }

    @Override
    public void dismissLoading() {

    }
}

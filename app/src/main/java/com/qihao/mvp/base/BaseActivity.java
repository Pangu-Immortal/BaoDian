package com.qihao.mvp.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dagger.android.AndroidInjection;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/25 16:40
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }
}

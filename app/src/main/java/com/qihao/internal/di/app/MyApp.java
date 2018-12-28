package com.qihao.internal.di.app;


import com.qihao.tools.LoggerUtil;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


/**
 * (此类核心功能)_:
 *
 * @author: qihao
 * @date: on 2018/12/18 17:19
 */
// 实现Activity、Fragment注入接口
public class MyApp extends DaggerApplication {

    private static MyApp myApp;

    @Inject
    LoggerUtil loggerUtil;

    public static MyApp getInstance() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}

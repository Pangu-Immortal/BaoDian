package com.android.internal.di.app;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import qi.school.BuildConfig;


/**
 * (此类核心功能)_:
 *
 * @author: qihao
 * @date: on 2018/12/18 17:19
 */
// 实现Activity、Fragment注入接口
public class MyApp extends DaggerApplication {

    private static MyApp myApp;

    public static MyApp getInstance() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        initLogUtils();
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    private void initLogUtils() {
        // log 日志管理
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。默认值true
                .methodCount(0)         //（可选）要显示的方法行数。默认值2
                .methodOffset(7)        //（可选）隐藏内部方法调用到偏移量。默认值5
                .tag("qihao")           //（可选）每个日志的全局标记。默认PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}

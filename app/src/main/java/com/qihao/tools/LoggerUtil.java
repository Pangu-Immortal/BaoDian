package com.qihao.tools;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import javax.inject.Inject;

import qi.school.BuildConfig;

/**
 * (核心功能)：日志打印工具类：对Logger再进行封装
 *  因为Logger类系统重名太多，避免不必要的导包错误，简单封装，并顺手注入了初始化参数。
 * @author qihao
 * @date on 2018/12/26 14:16
 */

public class LoggerUtil {

    @Inject
    public LoggerUtil() {
        // log 日志管理
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。
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

    public static void v(String msg) {
        Logger.v(msg + "");
    }

    /**
     * 用Log.d()能输出Debug、Info、Warning、Error级别的Log信息。
     */

    public static void d(String msg) {
        Logger.d(msg + "");
    }

    public static void d(Object... args) {
        Logger.d(args);
    }

    public static void d(Object object) {
        Logger.d(object);
    }

    /**
     * 用Log.i()能输出Info、Warning、Error级别的Log信息。
     */
    public static void i(String msg) {
        Logger.i(msg + "");
    }

    /**
     * Warning表示警告：开发时有时用来表示特别注意的地方。用Log.w()能输出Warning、Error级别的Log信息。
     */
    public static void w(String msg) {
        Logger.w(msg + "");
    }

    /**
     * Error表示出现错误：是最需要关注解决的。用Log.e()输出，能输出Error级别的Log信息。
     */
    public static void e(@NonNull String message, @Nullable Object... args) {
        Logger.e(message, args);
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        Logger.e(null, message, args);
    }

    public static void json(@Nullable String json) {
        Logger.json(json + "");
    }

    public static void xml(@Nullable String xml) {
        Logger.xml(xml + "");
    }

    public static void wtf(@NonNull String message, @Nullable Object... args){
        Logger.wtf(message ,args);
    }
}

package com.qihao.aop;

import android.content.Context;

import com.qihao.internal.di.app.MyApp;
import com.qihao.tools.LoggerUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * (核心功能)：初始化反Hook
 *
 * @author qihao
 * @date on 2018/12/26 15:49
 */
@Aspect
public class AppHook {

    private Context context;
    /**
     * 初始化位置
     */
    @After("execution(* MyApp.onCreate())")
    public void getAppContext(JoinPoint joinPoint) {
        this.context = ((MyApp) joinPoint.getThis());
        LoggerUtil.d("初始化Point--> "+joinPoint.toShortString());
    }

    @Before("execution(* LoginActivity.**(..))")
    public void executeAspectJ(JoinPoint joinPoint) {
        LoggerUtil.d("beforeAspectJ-->"+joinPoint.toShortString());
    }


}

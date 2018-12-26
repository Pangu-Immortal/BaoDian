package com.android.aop;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/26 11:01
 */
@Aspect
public class AspectTest {

    private static final String TAG = "qihao";

    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.d(TAG, "onActivityMethodBefore: " + key);
    }

    @After("execution(* com.xys.aspectjxdemo.MainActivity.on*(android.os.Bundle))")
    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.d(TAG, "onActivityMethodAfter: " + key);
    }

    @Around("execution(* com.xys.aspectjxdemo.MainActivity.testAOP())")
    public void onActivityMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String key = proceedingJoinPoint.getSignature().toString();
        Log.d(TAG, "onActivityMethodAroundFirst: " + key);
        proceedingJoinPoint.proceed();
        Log.d(TAG, "onActivityMethodAroundSecond: " + key);
    }
}

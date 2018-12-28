package com.qihao.internal.di.modules;

import com.qihao.internal.di.scope.ActivityScope;
import com.qihao.mvp.activity.LoginActivity;
import com.qihao.mvp.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * (核心功能)：注入式Activity的封装类，新建Activity必须来这里注入。
 *
 * @author qihao
 * @date on 2018/12/25 17:13
 */
@Module
public abstract class BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract LoginActivity loginActivityInject();

    @ActivityScope
    @ContributesAndroidInjector
    abstract SplashActivity splashActivityInject();

}

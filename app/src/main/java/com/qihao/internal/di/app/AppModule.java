package com.qihao.internal.di.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/25 17:17
 */
@Module(includes = {AndroidSupportInjectionModule.class, AndroidInjectionModule.class})
public abstract class AppModule {

    @Singleton
    @Provides
    static Context provideContext(Application application) {
        return application;
    }

}

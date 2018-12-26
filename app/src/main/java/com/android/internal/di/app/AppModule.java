package com.android.internal.di.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/25 17:17
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(Application application) {
        return application;
    }
}

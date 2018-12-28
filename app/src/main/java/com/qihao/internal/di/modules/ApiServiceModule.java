package com.qihao.internal.di.modules;

import com.qihao.tools.LoggerUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/27 16:26
 */

@Module
public class ApiServiceModule {

    @Singleton
    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor((message) -> LoggerUtil.i(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}

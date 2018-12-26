package com.android.internal.di.modules;

import com.android.beans.Student;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/25 17:56
 */
@Module
public abstract class ConfigModule {

    @Provides
    @Singleton
    static Student provideStudent() {
        return new Student();
    }
}

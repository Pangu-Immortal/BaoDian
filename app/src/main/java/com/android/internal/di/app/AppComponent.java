package com.android.internal.di.app;

import android.app.Application;

import com.android.internal.di.modules.BuildersModule;
import com.android.internal.di.modules.ConfigModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/25 11:52
 */
@Singleton
@Component(
        modules = {AppModule.class,
                BuildersModule.class,
                ConfigModule.class,
                AndroidSupportInjectionModule.class}
)
public interface AppComponent extends AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

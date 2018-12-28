package com.qihao.internal.di.modules;

import com.qihao.api.ApiService;
import com.qihao.beans.Student;
import com.qihao.tools.NetUtil;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/25 17:56
 */
@Module(includes = {ApiServiceModule.class})
public class ConfigModule {

    @Singleton
    @Provides
    public Student provideStudent() {
        return new Student();
    }

    @Singleton
    @Provides
    public ApiService provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        ApiService request = retrofit.create(ApiService.class);

        return request;
    }

    /**
     * 拦截器Interceptors
     * 统一的请求参数
     */
    private Interceptor commonParamsInterceptor() {
        Interceptor commonParams = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originRequest = chain.request();
                Request request;
                HttpUrl httpUrl = originRequest.url().newBuilder().
                        addQueryParameter("paltform", "android").
                        addQueryParameter("version", "1.0.0").build();
                request = originRequest.newBuilder().url(httpUrl).build();
                return chain.proceed(request);
            }
        };

        return commonParams;
    }

    /**
     * 拦截器Interceptors
     * 通过响应拦截器实现了从响应中获取服务器返回的time
     *
     * @return
     */
    public static Interceptor getResponseHeader() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed(chain.request());
                String timestamp = response.header("time");
                if (timestamp != null) {
                    //获取到响应header中的time
                }
                return response;
            }
        };
        return interceptor;
    }

    /**
     * 在无网络的情况下读取缓存，有网络的情况下根据缓存的过期时间重新请求,
     */
    public Interceptor getCacheInterceptor2() {
        Interceptor commonParams = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtil.isConnected()) {
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                okhttp3.Response response = chain.proceed(request);
                if (NetUtil.isConnected()) {//有网络情况下，根据请求接口的设置，配置缓存。
                    //这样在下次请求时，根据缓存决定是否真正发出请求。
                    String cacheControl = request.cacheControl().toString();
                    //当然如果你想在有网络的情况下都直接走网络，那么只需要
                    //将其超时时间这是为0即可:String cacheControl="Cache-Control:public,max-age=0"
                    int maxAge = 60 * 60; // read from cache for 1 minute
                    return response.newBuilder()
//                            .header("Cache-Control", cacheControl)
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    //无网络
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return response.newBuilder()
//                            .header("Cache-Control", "public,only-if-cached,max-stale=360000")
                            .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }

            }
        };
        return commonParams;
    }


}

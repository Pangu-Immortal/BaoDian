package com.qihao.api;

import com.qihao.beans.AppInfo;
import com.qihao.beans.PageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Description (此类核心功能):接口url
 * 如果接口url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略。
 * @author qihao
 * @date on 2019/1/2 16:55
 */
public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);
}

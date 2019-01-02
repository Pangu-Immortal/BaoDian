package com.qihao.api;

import com.qihao.beans.AppInfo;
import com.qihao.beans.PageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * (核心功能)：接口url
 * 如果接口url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略。
 * @author qihao
 * @date on 2018/12/27 13:34
 */
public interface ApiService {
    // https://suggest.taobao.com/sug?code=utf-8&q=卫衣

    // 参数说明：
    // a：固定值 fy
    // f：原文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
    // t：译文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
    // w：查询内容
    // http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);
}

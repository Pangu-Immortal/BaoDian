package com.qihao.api;

import com.qihao.beans.Translation;
import com.qihao.beans.YdTranslation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    // 金山词霸API
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();

    // 有道翻译API
    @POST("http://fanyi.youdao.com/translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<YdTranslation> postCall(@Field("i") String targetSentence);

}

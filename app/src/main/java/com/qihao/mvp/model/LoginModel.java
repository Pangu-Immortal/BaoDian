package com.qihao.mvp.model;

import com.qihao.api.ApiService;
import com.qihao.api.HttpManager;
import com.qihao.beans.AppInfo;
import com.qihao.beans.PageBean;
import com.qihao.mvp.contract.LoginContract;

import retrofit2.Callback;


/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/29 17:06
 */
public class LoginModel implements LoginContract.Model {
    @Override
    public void getApp(Callback<PageBean<AppInfo>> callback) {
        HttpManager manager = new HttpManager();
        ApiService apiService =manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        apiService.getApps("{'page':0}").enqueue(callback);
    }

}

package com.qihao.mvp.presenter;

import com.qihao.beans.AppInfo;
import com.qihao.beans.PageBean;
import com.qihao.mvp.activity.LoginActivity;
import com.qihao.mvp.contract.LoginContract;
import com.qihao.mvp.model.LoginModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/25 17:03
 */
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginActivity mView;
    private final LoginModel mModel;

    @Inject
    public LoginPresenter(LoginActivity view) {
        this.mView = view;
        mModel = new LoginModel();
    }

    @Override
    public void requestDatas() {
        mView.showLodading();
        mModel.getApp(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if (response != null) {
                    mView.showResult(response.body().getDatas());
                } else {
                    mView.showNodata();
                }
                mView.dimissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dimissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}

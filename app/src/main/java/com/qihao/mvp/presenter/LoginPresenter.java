package com.qihao.mvp.presenter;

import com.qihao.api.ApiService;
import com.qihao.beans.Student;
import com.qihao.beans.Translation;
import com.qihao.beans.YdTranslation;
import com.qihao.internal.di.scope.ActivityScope;
import com.qihao.mvp.activity.LoginActivity;
import com.qihao.mvp.base.BasePresenter;
import com.qihao.mvp.contract.MainContract;
import com.qihao.mvp.model.MainModel;
import com.qihao.tools.LoggerUtil;

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
public class LoginPresenter extends BasePresenter implements MainContract.Presenter {

    private final LoginActivity view;
    private final MainModel model;

    @Inject
    Student student;

    @Inject
    public LoginPresenter(LoginActivity view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void log(){
        // 验证 @Inject 能不能这样在任意类中，随意构建对象
        LoggerUtil.i("LoginPresenter student:"+student.getName());
    }

    public void requestGet(ApiService service) {
        Call<Translation> call = service.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                LoggerUtil.i(response.body().toString());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                LoggerUtil.e(throwable.getMessage());
            }
        });
    }

    public void requestPost(ApiService service) {
        Call<YdTranslation> call = service.postCall("I love you");
        call.enqueue(new Callback<YdTranslation>() {

            @Override
            public void onResponse(Call<YdTranslation> call, Response<YdTranslation> response) {
                LoggerUtil.i(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<YdTranslation> call, Throwable throwable) {
                LoggerUtil.e(throwable.getMessage());
            }
        });
    }
}

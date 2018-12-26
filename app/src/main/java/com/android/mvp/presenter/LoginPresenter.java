package com.android.mvp.presenter;

import com.android.mvp.activity.LoginActivity;
import com.android.mvp.base.BasePresenter;
import com.android.mvp.contract.MainContract;
import com.android.mvp.model.MainModel;

import javax.inject.Inject;

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
    public LoginPresenter(LoginActivity view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void requestHttp() {
        view.showMessage(model.returnMessage());
    }
}

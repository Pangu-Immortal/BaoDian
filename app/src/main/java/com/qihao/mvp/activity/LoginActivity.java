package com.qihao.mvp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.qihao.api.ApiService;
import com.qihao.beans.Student;
import com.qihao.mvp.base.BaseActivity;
import com.qihao.mvp.contract.MainContract;
import com.qihao.mvp.presenter.LoginPresenter;
import com.qihao.tools.LoggerUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qi.school.R;

public class LoginActivity extends BaseActivity implements MainContract.View {

    @Inject
    Student student;
    @Inject
    LoginPresenter presenter;
    @Inject
    ApiService service;

    @BindView(R.id.login_button)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.login_button)
    public void onViewClicked() {
        LoggerUtil.i("student:"+student.getName());
        LoggerUtil.i("对象:"+student.toString());
        presenter.requestGet(service);
        presenter.requestPost(service);
//        startActivity(new Intent(this, SplashActivity.class));
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

package com.android.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.beans.Student;
import com.android.mvp.base.BaseActivity;
import com.android.mvp.contract.MainContract;
import com.android.mvp.presenter.LoginPresenter;
import com.orhanobut.logger.Logger;

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
        Logger.i("student:"+student.getName());
        Logger.i("对象:"+student.toString());
        presenter.requestHttp();
        startActivity(new Intent(this, SplashActivity.class));
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

package com.qihao.mvp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;

import com.qihao.beans.AppInfo;
import com.qihao.beans.Student;
import com.qihao.mvp.base.BaseActivity;
import com.qihao.mvp.contract.LoginContract;
import com.qihao.mvp.presenter.LoginPresenter;
import com.qihao.tools.LoggerUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qi.school.R;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    Student student;
    @Inject
    LoginPresenter presenter;

    @BindView(R.id.login_button)
    Button login;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
    }


    @OnClick(R.id.login_button)
    public void onViewClicked() {
        LoggerUtil.i("student:"+student.getName());
        LoggerUtil.i("对象:"+student.toString());

        presenter.requestDatas();
    }

    @Override
    public void showResult(List<AppInfo> datas) {
        for (int i = 0; i < datas.size(); i++) {
            LoggerUtil.d(datas.get(i).toString());
        }
    }

    @Override
    public void showNodata() {
        LoggerUtil.d("showNodata");
    }

    @Override
    public void showError(String msg) {
        LoggerUtil.d("showError="+msg);
    }

    @Override
    public void toast(CharSequence message) {
        LoggerUtil.d("message="+message);
    }

    @Override
    public void showLodading() {
        mProgressDialog.show();
    }

    @Override
    public void dimissLoading() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}

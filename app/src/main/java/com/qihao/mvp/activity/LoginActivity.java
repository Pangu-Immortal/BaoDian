package com.qihao.mvp.activity;

import android.app.ProgressDialog;
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
import butterknife.OnClick;
import qi.school.R;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @Inject
    Student student;
    @Inject
    LoginPresenter presenter;

    @BindView(R.id.login_button)
    Button login;

    private ProgressDialog mProgressDialog;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
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
    public void showErrors(String msg) {
        LoggerUtil.d("showError="+msg);
    }

    @Override
    public void dismissLoading() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void toast(CharSequence message) {
        LoggerUtil.d("message="+message);
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }
}

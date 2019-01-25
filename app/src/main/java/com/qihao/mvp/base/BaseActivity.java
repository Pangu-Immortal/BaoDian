package com.qihao.mvp.base;

import android.os.Bundle;

import com.qihao.internal.di.app.MyApp;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/25 16:40
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity   implements BaseView{

    private Unbinder mUnbinder;

    protected MyApp mApplication;

    @Inject
    T mPresenter ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        setContentView(setLayout());

        mUnbinder = ButterKnife.bind(this);
        this.mApplication = (MyApp) getApplication();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mUnbinder !=Unbinder.EMPTY){

            mUnbinder.unbind();
        }
    }

    public abstract int setLayout();

    public abstract void  init();
}

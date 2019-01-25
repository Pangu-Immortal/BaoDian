package com.qihao.mvp.base;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/26 09:42
 */
public class BasePresenter<M, V extends BaseView> {

    protected M mModel;
    protected V mView;
    protected Context mContext;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;

        initContext();
    }

    public BasePresenter() {
    }

    private void initContext() {
        if (mView instanceof Fragment) {
            mContext = ((Fragment) mView).getActivity();
        } else {
            mContext = (Activity) mView;
        }
    }

}

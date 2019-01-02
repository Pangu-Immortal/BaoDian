package com.qihao.mvp.contract;


import com.qihao.beans.AppInfo;
import com.qihao.beans.PageBean;
import com.qihao.mvp.base.BaseModel;
import com.qihao.mvp.base.BasePresenter;
import com.qihao.mvp.base.BaseView;

import java.util.List;

import retrofit2.Callback;

/**
 * (此类核心功能)_: 简单封装MVP接口
 *
 * @author: qihao
 * @date: on 2018/12/25 16:34
 */

public interface LoginContract {

    interface View extends BaseView {
        void  showResult(List<AppInfo> datas);
        void showNodata();
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void requestDatas();
    }

    interface Model extends BaseModel {
        void getApp(Callback<PageBean<AppInfo>> callback);
    }
}

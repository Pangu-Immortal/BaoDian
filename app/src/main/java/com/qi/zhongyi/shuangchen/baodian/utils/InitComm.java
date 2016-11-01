package com.qi.zhongyi.shuangchen.baodian.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;

import com.qi.zhongyi.shuangchen.baodian.MyView.MyProgressDialog;
import com.qi.zhongyi.shuangchen.baodian.R;
import com.qi.zhongyi.shuangchen.baodian.ZYiMainActivity;


public class InitComm {
    private static InitComm initComm;
    private MyProgressDialog dialog;
    private PopupWindow popupWindow;
    private Button bt_know;
    private Animation flip_vertical_in;


    public static InitComm init() {
        if (initComm == null) {
            initComm=new InitComm();
        }
        return initComm;
    }

    /* 显示遮罩层 */
    public void showView(Context a, String text, boolean b) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog=new MyProgressDialog(a, text, R.drawable.loading, b);
        dialog.show();
    }

    public void closeView() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    public void showPop(View v,Activity activity,int width,int height) {
        InitComm.init().closeView();
        flip_vertical_in = AnimationUtils.loadAnimation(activity, R.anim.flip_vertical_in);// 公告动画
        View popView = LayoutInflater.from(activity).inflate(R.layout.view_xitongpop,
                null);
        popupWindow = new PopupWindow(popView, width, height);
        bt_know = (Button) popView.findViewById(R.id.bt_know);
        bt_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //popupwindow要依附于一个activity，而activity的onCreate()还没执行完没有view给此函数用。
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popView.startAnimation(flip_vertical_in); // 公告开始动画
    }
    /* 刷新页面 */
    public void refreshCurrentActivity(Context context) {
        ((Activity) context).finish();
        Intent intent=new Intent(context, ZYiMainActivity.class);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.fade, R.anim.hold);
    }
}

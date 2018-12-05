package com.baodian;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.baodian.utils.ActivityUtil;
import com.baodian.utils.MyDialogView;

import java.lang.reflect.Field;

public class BaseActivity extends FragmentActivity {

    MyDialogView finalDialog=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finalDialog=new MyDialogView(BaseActivity.this, "1", "退出应用", "您确定要退出应用吗？", "确定", "取消", new MyDialogView.MyDialogListener() {
                @Override
                public void onClick(int index) {
                    if (index == 0) {
                        finalDialog.dismiss();
                        ActivityUtil.getInstance().finishAllActivity();
                    } else {
                        finalDialog.dismiss();
                    }
                }
            });
            finalDialog.show();
        }

        return super.onKeyDown(keyCode, event);
    }

    protected int getTitleTop() {
        Class<?> c=null;
        Object obj=null;
        Field field=null;
        int x=0, sbar=0;
        try {
            c=Class.forName("com.android.internal.R$dimen");
            obj=c.newInstance();
            field=c.getField("status_bar_height");
            x=Integer.parseInt(field.get(obj).toString());
            sbar=getResources().getDimensionPixelSize(x);// 状态栏高度
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Log.e("状态栏高度:", sbar + ":" + x);
        return sbar;
    }
}

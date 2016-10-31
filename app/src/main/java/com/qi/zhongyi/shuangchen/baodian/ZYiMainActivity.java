package com.qi.zhongyi.shuangchen.baodian;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.qi.zhongyi.shuangchen.baodian.adapters.ListMunuAdapter;
import com.qi.zhongyi.shuangchen.baodian.shake.ShakeActivity;
import com.qi.zhongyi.shuangchen.baodian.utils.ActivityUtil;
import com.qi.zhongyi.shuangchen.baodian.utils.ToastUtils;

import java.lang.reflect.Field;

public class ZYiMainActivity extends BaseActivity {
    private SlidingMenu mMenu=null;
    private int top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //这里使用的是slidingmenu的第三种用法。
        setContentView(R.layout.slidingmenu_main);
        View view=getLayoutInflater().inflate(R.layout.main_menu_login, null, false);
        // 管理activity
        ActivityUtil.getInstance().addActivity(this);
        //初始化菜单
        initMenu(view);
        top=getTitleTop();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ((LinearLayout) findViewById(R.id.sou_s)).setPadding(0, top, 0, 0);
        }
    }

    private int getTitleTop() {
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

    private void initMenu(View view) {
        mMenu=(SlidingMenu) findViewById(R.id.slidingmenulayout);
        mMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //获取屏幕宽高
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 设置滑动菜单视图的宽度
        mMenu.setBehindWidth(dm.widthPixels * 4 / 5);
        // 设置渐入渐出效果的值
        mMenu.setFadeDegree(0.35f);
        mMenu.setMenu(view);
        initView(view);
    }

    private void initView(View view) {
        ListView listView=(ListView) view.findViewById(R.id.listview_menu);
        listView.setAdapter(new ListMunuAdapter(ZYiMainActivity.this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        ToastUtils.show(ZYiMainActivity.this, "点击了 id=" + i);
                        break;
                    case 1:
                        ToastUtils.show(ZYiMainActivity.this, "点击了 id=" + i);
                        break;
                    case 2:
                        ToastUtils.show(ZYiMainActivity.this, "点击了 id=" + i);
                        break;
                    case 3:
                        ToastUtils.show(ZYiMainActivity.this, "点击了 id=" + i);
                        Intent intent=new Intent(ZYiMainActivity.this, ShakeActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        ToastUtils.show(ZYiMainActivity.this, "点击了 id=" + i);
                        break;
                    case 5:
                        ToastUtils.show(ZYiMainActivity.this, "点击了 id=" + i);
                        break;
                }
            }
        });
    }
}

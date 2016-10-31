package com.qi.zhongyi.shuangchen.baodian;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.qi.zhongyi.shuangchen.baodian.adapters.ListMunuAdapter;
import com.qi.zhongyi.shuangchen.baodian.utils.ActivityUtil;

public class ZYiMainActivity extends BaseActivity {
    private SlidingMenu mMenu=null;

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
        // 判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
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
    }
}

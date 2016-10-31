package com.qi.zhongyi.shuangchen.baodian;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;

import com.qi.zhongyi.shuangchen.baodian.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;


public class GuideViewActivity extends Activity {
    private int count;
    int currentPage=0;// 当前页数
    private ViewPager viewPager;
    private List<View> listViews;
    private MyPagerAdapter adapter;
    private Button bt_finish;
    private int InFlag;
    private int screen_Width;
    private int screen_Height;
    private int dis;
    private PackageInfo info;
    private SharedPreferences sp;
    private Editor edit;
    private Button bt_no;
    private WebView webview;
    private Boolean isNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 管理activity
        ActivityUtil.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_guideview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        try {
            info=this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        // 判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        init();
    }

    private void init() {
        // 获取当前手机屏幕宽高
        DisplayMetrics mDisplayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        screen_Width=mDisplayMetrics.widthPixels;
        screen_Height=mDisplayMetrics.heightPixels;
        dis=mDisplayMetrics.densityDpi;
        InFlag=getIntent().getIntExtra("InFlag", 0);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        listViews=initListViews(currentPage);
        adapter=new MyPagerAdapter(listViews, count);
        viewPager.setAdapter(adapter);
    }

    /**
     * listViews添加view对象
     */
    private List<View> initListViews(final int currentCount) {
        if (listViews == null) {
            listViews=new ArrayList<View>();
        }

        View view1=LayoutInflater.from(GuideViewActivity.this).inflate(R.layout.viewpage1, null);
        View view2=LayoutInflater.from(GuideViewActivity.this).inflate(R.layout.viewpage2, null);
        View view3=LayoutInflater.from(GuideViewActivity.this).inflate(R.layout.viewpage3, null);
        View view4=LayoutInflater.from(GuideViewActivity.this).inflate(R.layout.viewpage4, null);

        bt_finish=(Button) view4.findViewById(R.id.bt_finish);

        // 开始
        bt_finish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(GuideViewActivity.this, ZYiMainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
                String USER_INFO_SP="";
                try {
                    USER_INFO_SP=getPackageManager().getPackageInfo(getPackageName(), 0).packageName + "LOGIN_STATE_INFO";
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                }
                SharedPreferences sp=getSharedPreferences(USER_INFO_SP, Context.MODE_PRIVATE);
                Editor ed=sp.edit();
                ed.putBoolean("isFirst", false);
                ed.commit();
            }
        });
        listViews.add(view1);
        listViews.add(view2);
        listViews.add(view3);
        listViews.add(view4);

        return listViews;
    }

    /**
     * ViewPager适配器
     *
     * @author zhenglei
     */
    class MyPagerAdapter extends PagerAdapter {

        private List<View> listViews;// content

        private int size;// 页数

        public MyPagerAdapter(List<View> listViews, int count) {// 构造函数
            this.listViews=listViews;// 初始化viewpager的时候给的一个页面
            // this.size = count;
            size=listViews == null ? 0 : listViews.size();
            System.out.println("Count------------" + count);
        }

        public void setListViews(List<View> listViews) {// 更新ListView

            this.listViews=listViews;
            size=listViews == null ? 0 : listViews.size();
        }

        @Override
        public int getCount() {// 返回数量
            return size;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {// 销毁view对象
            if (getCount() > 1) {
                ((ViewPager) arg0).removeView(listViews.get(arg1 % size));
            }
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {// 返回view对象
            try {
                ((ViewPager) arg0).addView(listViews.get(arg1 % size), 0);
            } catch (Exception e) {
                Log.e("zhou", "exception：" + e.getMessage());
            }
            return listViews.get(arg1 % size);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }

}
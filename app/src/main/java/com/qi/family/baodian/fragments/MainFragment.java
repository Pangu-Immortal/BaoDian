package com.qi.family.baodian.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.NetworkImageView;
import com.qi.family.baodian.MyView.autoViewPagers.AutoScrollViewPager;
import com.qi.family.baodian.R;
import com.qi.family.baodian.adapters.AsvAdapter;
import com.qi.family.baodian.utils.InitComm;

/**
 * Ceate author: qihao on 2016/10/31 21:54
 * Class by MainFragment (TODO)
 * 邮箱：yugu88@163.com
 */
public class MainFragment extends Fragment {
    private int height;
    private int width;
    private View view;
    private AutoScrollViewPager pager;
    private NetworkImageView[] images;
    private ImageView[] dots;
    private AsvAdapter adAdapter;
    private Handler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DisplayMetrics displayMetrics=new DisplayMetrics();
        MainFragment.this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height=displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;
        view=inflater.inflate(R.layout.fragment_main, container, false);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 * 不延时会报错：Unable to add window -- token null is not valid; is your activity running?
                 * 由于PopupWindow要依附于一个activity，而activity的onCreate()还没执行完，哪来的popup让你弹出来嘛。
                 * 因此，你要做的就是让这个showAtLocation调用再晚一点
                 */
                InitComm.init().showPop(view, MainFragment.this.getActivity(), width, height);// 公告提示
            }
        }, 2000);
        iniViewPager();
        return view;
    }

    private void iniViewPager() {

        // 初始化中间部分
        //iniHorizontalScrollView();
        // 载入图片资源ID
        int pagernum=8;
        pager=(AutoScrollViewPager) view.findViewById(R.id.adv_pager);
        ViewGroup group=(ViewGroup) view.findViewById(R.id.viewGroup);
        // 这里存放的是广告背景图
        images=new NetworkImageView[pagernum];
        // 存放小点
        dots=new ImageView[pagernum];
        group.removeAllViews();
        if (MainFragment.this.getActivity() != null) {
            // 填充pager数据
            for (int i=0; i < pagernum; i++) {
                // 创建小点
                ImageView imageView=new ImageView(MainFragment.this.getActivity());
                LinearLayout.LayoutParams layout=new LinearLayout.LayoutParams(16, 16);
                layout.setMargins(5, 5, 5, 5);
                imageView.setLayoutParams(layout);
                if (i == 0) {
                    imageView.setBackgroundResource(R.mipmap.bbb);
                } else {
                    imageView.setBackgroundResource(R.mipmap.aaa);
                }
                dots[i]=imageView;
                group.addView(imageView);// 将点点加入到ViewGroup中
            }
            adAdapter=new AsvAdapter(MainFragment.this.getActivity(), images);
            adAdapter.notifyDataSetChanged();
            // 设置Adapter
            pager.setAdapter(adAdapter);
            if (images.length > 1) {
                pager.startAutoScroll();
                pager.setInterval(5000); // 设置自动滚动的间隔时间，单位为毫秒
                // pager.setCycle(true);
                pager.setSlideBorderMode(1);// 2滑动边界传递给父view,1轮播,0没有任何操作
            } else {
                pager.stopAutoScroll();
            }
            // 设置监听，主要是设置点点的背景
            pager.setOnPageChangeListener(new GuidePageChangeListener());
            pager.setCurrentItem(100);
            // 给父Activity中的SlidingMenu设置不可滑动区域
            // ((JZYHMainActivity) getActivity()).menu.addIgnoredView(pager);
        }

    }

    private int pager_position;// 当前pager的ID

    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做
            // Log.e("arg0=", arg0 + "");
            adAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            /**
             * 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法会一直得到调用 arg0 :当前页面，及你点击滑动的页面
             * arg1:当前页面偏移的百分比 arg2:当前页面偏移的像素位置
             */
            adAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPageSelected(int arg0) {
            // 此方法是页面跳转完后得到调用，arg0是你当前选中的页面的Position
            pager_position=arg0 % dots.length;
            // 切换选中的点
            // Log.e("a=", pager_position + "");
            pager.getAdapter().notifyDataSetChanged();
            for (int i=0; i < dots.length; i++) {
                if (pager_position == i) {
                    dots[i].setBackgroundResource(R.mipmap.bbb);
                } else {
                    dots[i].setBackgroundResource(R.mipmap.aaa);
                }
            }
            adAdapter.notifyDataSetChanged();
        }
    }

}

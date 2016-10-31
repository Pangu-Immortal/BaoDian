package com.qi.zhongyi.shuangchen.baodian.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qi.zhongyi.shuangchen.baodian.R;

/**
 * Ceate author: qihao on 2016/10/31 21:54
 * Class by MainFragment (TODO)
 * 邮箱：yugu88@163.com
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        ViewPager viewPager=(ViewPager) view.findViewById(R.id.main_viewpager);
//        viewPager.setAdapter(new );
        return view;
    }

}

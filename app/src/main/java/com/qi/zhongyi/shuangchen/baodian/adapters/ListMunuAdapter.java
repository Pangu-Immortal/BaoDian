package com.qi.zhongyi.shuangchen.baodian.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qi.zhongyi.shuangchen.baodian.R;

/**
 * Created by qihao on 2016/10/31.15:30
 * for: BaoDian.
 */

public class ListMunuAdapter extends BaseAdapter {
    private String[] menu_text={"登录密码修改", "交易限额设置", "预留信息设置", "手机银行冻结", "操作记录查询", "我的消息"};
    private int[] menu_icon_login={R.mipmap.menu_2, R.mipmap.menu_3, R.mipmap.menu_4, R.mipmap.menu_6, R.mipmap.menu_5, R.mipmap.menu_1_1};
    private Context mContext;
    private ImageView iv_menu;
    private TextView tv_menu;

    public ListMunuAdapter(Context mContext) {
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return menu_text.length;
    }

    @Override
    public Object getItem(int i) {
        return menu_text[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view=LayoutInflater.from(mContext).inflate(R.layout.adapter_listview_menu, null);
            iv_menu=(ImageView) view.findViewById(R.id.iv_menu);
            tv_menu=(TextView) view.findViewById(R.id.tv_menu);
        }
        iv_menu.setBackgroundResource(menu_icon_login[i]);
        tv_menu.setText(menu_text[i]);
        return view;
    }
}
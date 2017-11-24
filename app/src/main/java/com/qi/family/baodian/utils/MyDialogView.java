package com.qi.family.baodian.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.qi.family.baodian.R;

/**
 * Ceate author: qihao on 2016/10/27 19:28
 * Class by MyDialogView (TODO) 自定义Dialog
 * 邮箱：yugu88@163.com 
 */
public class MyDialogView extends Dialog implements View.OnClickListener {
	private Context mContext;
	private String mStr;
	private String mTitle;
	private String mContent;
	private String mButton1;
	private String mButton2;
	private MyDialogListener mListener;
	private boolean IsFlag = false;
	public interface MyDialogListener {
		public void onClick(int index);
	}
	public MyDialogView(Context context, String str, boolean isFlag, MyDialogListener listener){
		super(context);
		this.mContext = context;
		this.mStr = str;
		this.IsFlag = isFlag;
		this.mListener = listener;
	}
	public MyDialogView(Context context, String str, String title, String content, String button1, String button2, MyDialogListener listener){
		super(context);
		this.mContext = context;
		this.mStr = str;
		this.mTitle = title;
		this.mContent = content;
		this.mButton1 = button1;
		this.mButton2 = button2;
		this.mListener = listener;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		super.onCreate(savedInstanceState);
		if ("1".equals(mStr)) {
		   Title_Content();
		} else if ("2".equals(mStr)){
		   //YaoYiYao_Menu();
		}
	}
	private void Title_Content() {
		this.setContentView(R.layout.jzsjyh_title_content_dialog);
		TextView title = (TextView) findViewById(R.id.id_jzsjyh_dialog_title);
		TextView content = (TextView) findViewById(R.id.id_jzsjyh_dialog_content);
		Button button_yi = (Button) findViewById(R.id.id_jzsjyh_dialog_button_yi);
		Button button_er = (Button) findViewById(R.id.id_jzsjyh_dialog_button_er);
		title.setText(mTitle);
		content.setText(mContent);
		button_yi.setText(mButton1);
		button_er.setText(mButton2);
		button_yi.setOnClickListener(this);
		button_er.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_jzsjyh_dialog_button_yi:
			 mListener.onClick(0);
			 break;
		case R.id.id_jzsjyh_dialog_button_er:
			 mListener.onClick(1);
		     break;
		default:
			 break;
		}
	}
}
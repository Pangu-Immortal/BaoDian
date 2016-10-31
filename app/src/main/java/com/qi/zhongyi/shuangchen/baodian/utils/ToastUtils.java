package com.qi.zhongyi.shuangchen.baodian.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 提示管理
 * */
public class ToastUtils {
	private static Toast mToast;

	/* Toast优化 */
	public static void show(Context ctx, String text) {
		if (mToast == null) {
			mToast = Toast.makeText(ctx, text, Toast.LENGTH_SHORT);
			mToast.show();
		} else {
			cancelToast();
			mToast = Toast.makeText(ctx, text, Toast.LENGTH_SHORT);
			// mToast.setText(text);
			// mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	public static void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}
}

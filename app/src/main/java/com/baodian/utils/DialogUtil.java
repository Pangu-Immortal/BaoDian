package com.baodian.utils;


import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {
	public static int DIALOG = 100;
	public static int sDIALOG1= 101;
	public static void createDialog(final Context context, int flag,
			String message) {
		Builder builder = new Builder(context);
		builder.setTitle("提示");
		builder.setMessage(message);
		if(flag==DIALOG){
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {		
				@Override
				public void onClick(DialogInterface dialog, int arg1) {

					dialog.cancel();
					
				}
			});
			builder.setNegativeButton("取消", null);
		}else if(flag== sDIALOG1){
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {		
				@Override
				public void onClick(DialogInterface dialog, int arg1) {

					dialog.cancel();
				}
			});
		}else if(flag==99){//错误
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {		
				@Override
				public void onClick(DialogInterface dialog, int arg1) {

					dialog.cancel();
				}
			});
		}
		builder.setCancelable(false);
		builder.create().show();
	}
}

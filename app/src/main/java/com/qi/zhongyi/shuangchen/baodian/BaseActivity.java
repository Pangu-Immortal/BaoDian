package com.qi.zhongyi.shuangchen.baodian;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import com.iflytek.autoupdate.IFlytekUpdate;
import com.iflytek.autoupdate.IFlytekUpdateListener;
import com.iflytek.autoupdate.UpdateConstants;
import com.iflytek.autoupdate.UpdateErrorCode;
import com.iflytek.autoupdate.UpdateInfo;
import com.iflytek.autoupdate.UpdateType;
import com.qi.zhongyi.shuangchen.baodian.utils.ActivityUtil;
import com.qi.zhongyi.shuangchen.baodian.utils.MyDialogView;
import com.qi.zhongyi.shuangchen.baodian.utils.ToastUtils;

import java.io.File;

public class BaseActivity extends FragmentActivity {
    private IFlytekUpdate updManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化自动更新对象
        IFlytekUpdate updManager=IFlytekUpdate.getInstance(getApplicationContext());
        //开启调试模式，默认不开启
        updManager.setDebugMode(true);
        //开启wifi环境下检测更新，仅对自动更新有效，强制更新则生效
        updManager.setParameter(UpdateConstants.EXTRA_WIFIONLY, "true");
        //设置通知栏使用应用icon，设置通知栏icon，默认使用SDK默认
        updManager.setParameter(UpdateConstants.EXTRA_NOTI_ICON, "false");
        //设置更新提示类型，默认为通知栏提示
        updManager.setParameter(UpdateConstants.EXTRA_STYLE, UpdateConstants.UPDATE_UI_DIALOG);
        // 通知提醒
        // updManager.setParameter(UpdateConstants.EXTRA_STYLE, UpdateConstants.UPDATE_UI_NITIFICATION);
        // 启动自动更新
        updManager.autoUpdate(BaseActivity.this, updateListener);
        //updManager.forceUpdate(BaseActivity.this, updateListener);// 强制更新
        deleteapk(); // 删除已下载的更新文件
    }

    private void deleteapk() {
        // 删除已下载的更新文件
        String path = "";
        if (Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment.getExternalStorageState())) {
            path = Environment.getExternalStorageDirectory() + "/download/iFlyUpdate";
        }
        if (TextUtils.isEmpty(path)) {
            Log.e("删除日志：","文件路径不正确！");
            return;
        }
        File file = new File(path);
        delFile(file);
    }

    private IFlytekUpdateListener updateListener=new IFlytekUpdateListener() {

        @Override
        public void onResult(int errorcode, UpdateInfo result) {

            if (errorcode == UpdateErrorCode.OK && result != null) {
                if (result.getUpdateType() == UpdateType.NoNeed) {
                    //showTip("已经是最新版本！");
                    return;
                }
                updManager.showUpdateInfo(BaseActivity.this, result);
            } else {
                showTip("请求更新失败！\n更新错误码：" + errorcode);
            }
        }
    };

    private void delFile(File deleteFile) {
        if (!deleteFile.exists()) {
            return;
        }
        if (!deleteFile.isDirectory()) {
            deleteFile.delete();
        } else {
            File[] fileList=deleteFile.listFiles();
            if (null == fileList || fileList.length <= 0) {
                deleteFile.delete();
                return;
            }
            for (File file : fileList) {
                delFile(file);
            }
            deleteFile.delete();
        }
    }

    private void showTip(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show(BaseActivity.this,str);
            }
        });
    }

    MyDialogView finalDialog=null;

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
}

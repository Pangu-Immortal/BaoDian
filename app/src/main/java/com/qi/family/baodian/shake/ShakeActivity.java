package com.qi.family.baodian.shake;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.qi.family.baodian.R;
import com.qi.family.baodian.utils.ActivityUtil;

import java.util.Map;


public class ShakeActivity extends AppCompatActivity {

    ShakeListener mShakeListener=null;
    Vibrator mVibrator;
    private RelativeLayout mImgUp;
    private RelativeLayout mImgDn;
    private SoundPool sndPool;
    private Map<Integer, Integer> loadSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        // 管理activity
        ActivityUtil.getInstance().addActivity(this);
        // 初始化数据
        init();
        // 调用工具类方法把assets目录下的声音存放在map中，返回一个HashMap
        loadSound=ShakeUtils.loadSound(sndPool, this);
        // 创建加速度监听器的对象
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShakeListener=new ShakeListener(this);
        // 加速度传感器，达到速度阀值，播放动画
        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {
                ShakeUtils.startAnim(mImgUp, mImgDn); // 开始 摇一摇手掌动画
                mShakeListener.stop();// 停止加速度传感器
                sndPool.play(loadSound.get(0), (float) 1, (float) 1, 0, 0, (float) 1.2);// 摇一摇时播放map中存放的第一个声音
                startVibrato();// 震动

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        sndPool.play(loadSound.get(1), (float) 1, (float) 1, 0, 0, (float) 1.0);// 摇一摇结束后播放map中存放的第二个声音
                        Toast.makeText(getApplicationContext(), "抱歉，暂时没有找到\n在同一时刻摇一摇的人。\n再试一次吧！", Toast.LENGTH_SHORT).show();
                        mShakeListener.start();// 再次开始检测加速度传感器值
                        //mVibrator.cancel();// 震动关闭
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mShakeListener != null) {
            mShakeListener.stop();
        }
    }

    private void init() {
        // // 获取Vibrate震动对象
        mVibrator=(Vibrator) getApplication().getSystemService(VIBRATOR_SERVICE);
        mImgUp=(RelativeLayout) findViewById(R.id.shakeImgUp);
        mImgDn=(RelativeLayout) findViewById(R.id.shakeImgDown);
        sndPool=new SoundPool(2, AudioManager.STREAM_MUSIC, 5);
    }

    public void startVibrato() {
        // 设置Vibrate的震动周期  1参数用来指定振动时间的样本，2参数用来指定是否需要循环。
        /**
         * pattern:  an array of longs of times for which to turn the vibrator on or off.
         * repeat:  the index into pattern at which to repeat, or -1 if you don't want to repeat.
         * 在new long[]{num0,num1,num2,...}中第0、2、4...个数代表不震动的时间（ms），
         * 而奇数标号上的数代表震动的时间（ms），这样便组成了一组震动方式（pattern）
         * 当repeat 为 0 时， 持续震动。-1 时，只震动一轮。
         */
        mVibrator.vibrate(new long[]{100, 1000, 500, 800}, -1);
    }

    public void shake_activity_back(View v) { // 标题栏 返回按钮
        this.finish();
    }

    public void linshi(View v) { // 标题栏
        ShakeUtils.startAnim(mImgUp, mImgDn);
    }
}

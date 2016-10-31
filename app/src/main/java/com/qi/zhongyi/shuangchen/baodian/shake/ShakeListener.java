package com.qi.zhongyi.shuangchen.baodian.shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

/**
 * Created by qihao on 2016/10/27.15:34
 * for: HeiMa.   一个检测手机摇晃的监听器
 */
public class ShakeListener implements SensorEventListener {
    private static final int SPEED_SHRESHOLD=2000; // 速度阈值，当摇晃速度达到这值后产生作用
    private static final int UPTATE_INTERVAL_TIME=70; // 两次检测的时间间隔
    private SensorManager sensorManager; // 传感器管理器
    private Sensor sensor; // 传感器
    private OnShakeListener onShakeListener; // 加速度感应监听器
    private Context mContext; // 上下文
    private long lastUpdateTime; // 上次检测时间
    // 手机上一个位置时加速度感应坐标
    private float lastX;
    private float lastY;
    private float lastZ;

    public ShakeListener(Context c) {
        // 获得监听对象
        mContext=c;
        start();
    }

    public void start() {
        // 获得传感器管理器
        sensorManager=(SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            // 获得加速度传感器
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        // 注册
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        } else {
            Toast.makeText(mContext, "您的手机不支持该功能", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentUpdateTime=System.currentTimeMillis();    // 当前检测时间
        long timeInterval=currentUpdateTime - lastUpdateTime;        // 两次检测的时间间隔
        // 判断是否达到了检测时间间隔
        if (timeInterval < UPTATE_INTERVAL_TIME) return;
        // 现在的时间变成last时间
        lastUpdateTime=currentUpdateTime;

        // 获得x,y,z变化后坐标
        float x=event.values[0];
        float y=event.values[1];
        float z=event.values[2];

        // 获得x,y,z的变化值
        float deltaX=x - lastX;
        float deltaY=y - lastY;
        float deltaZ=z - lastZ;

        // 将现在的坐标变成last坐标
        lastX=x;
        lastY=y;
        lastZ=z;

        double speed=Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) / timeInterval * 10000;
        // 达到速度阀值，发出提示
        if (speed >= SPEED_SHRESHOLD) {
            onShakeListener.onShake();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // 摇晃监听接口
    public interface OnShakeListener {
        public void onShake();
    }

    // 停止检测
    public void stop() {
        sensorManager.unregisterListener(this);
    }

    // 设置重力感应监听器
    public void setOnShakeListener(OnShakeListener listener) {
        onShakeListener=listener;
    }
}

package com.baodian.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Stack;
/**
 * Ceate author: qihao on 2016/10/27 17:26
 * Class by ActivityUtil (TODO)
 * 邮箱：yugu88@163.com
 */
public class ActivityUtil {
	private static final String Tag = "ActivityUtil";
	//activity栈管理
	private Stack<Activity> activityStack;
	//实例化的工具类
	private static ActivityUtil activityUtil;
	
	//单例模式获取类的实例
	public static ActivityUtil getInstance() {
		if (activityUtil == null) {
			synchronized (ActivityUtil.class) {
				if(activityUtil == null){
					activityUtil = new ActivityUtil();
				}
			}
		}
		return activityUtil;
	}

	/**
	 * 往栈里面添加activity
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		if(activity!=null){
		activityStack.add(activity);
		}
	}

	/**
	 * 获得当前栈顶Activity
	 */
	public Activity getCurrentActivity() {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 关闭当前栈顶Activity
	 */
	public void finishCurrentActivity() {
		finishThisActivity(getCurrentActivity());
	} 

	/**
	 *  退出栈顶Activity
	 */
	public void finishThisActivity(Activity activity) {
		if (activity != null && activityStack != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}
	 /** 
     * 退出栈中其他所有Activity 
     *  
     * @param cls Class 类名 
     */  
    @SuppressWarnings("rawtypes")  
    public void popOtherActivity(Class cls)  
    {  
        if(null == cls)  
        {  
            Log.e(Tag, "cls is null");  
            return;  
        }  
          
        for(Activity activity : activityStack)  
        {  
            if (null == activity || activity.getClass().equals(cls))  
            {  
                continue;  
            }  
              
            activity.finish();  
        }  
        Log.d(Tag, "activity num is : " + activityStack.size());  
    } 

	/**
	 * 关闭指定名字activity
	 */
	public void finishActivity(Class<?> cls) {
		finishThisActivity(getActivity(cls));	
	}
	
	/**
	 * 返回指定名字的activity
	 */
	public Activity getActivity(Class<?>cls){
		Activity finishActivity=null;
		if(activityStack != null){
			for (Activity activity : activityStack) {
				if (activity.getClass().getName().equals(cls.getName())) {
					finishActivity = activity;
				}
			}
		}
		return finishActivity;
	}

	/**
	 * 关闭所有的activity然后清理栈
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 退出应用
	 */
	public void AppExit() {
		try {
			finishAllActivity();
		} catch (Exception e) {
		}
	}
	//判读程序是否在运行
	public static boolean stackResumed(Context context) {
		ActivityManager manager = (ActivityManager) context
				.getApplicationContext().getSystemService(
						Context.ACTIVITY_SERVICE);
		String packageName = context.getApplicationContext().getPackageName();
		List<RunningTaskInfo> recentTaskInfos = manager.getRunningTasks(1);
		if (recentTaskInfos != null && recentTaskInfos.size() > 0) {
			RunningTaskInfo taskInfo = recentTaskInfos.get(0);
			if (taskInfo.baseActivity.getPackageName().equals(packageName) && taskInfo.numActivities > 1) {
				return true;
			}
		}
		
		return false;
	}
}

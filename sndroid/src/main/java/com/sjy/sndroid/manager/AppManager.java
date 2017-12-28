package com.sjy.sndroid.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.Nullable;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by sjy on 2017/11/30.
 */

public class AppManager {

    private static AppManager appManager;
    private static CopyOnWriteArrayList<Activity> mActivityStack;

    public static AppManager getInstance(){
        if(appManager==null){
            appManager=new AppManager();
        }
        return appManager;
    }

    public  void addActivity(@Nullable Activity activity){
        if(mActivityStack==null){
            mActivityStack=new CopyOnWriteArrayList<>();
        }
        mActivityStack.add(activity);
    }

    public  void removeActivity(Activity activity){
        try {
            if (activity != null) {
                mActivityStack.remove(activity);
                activity.finish();
                activity = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void removeActivity(Class<?> cls){
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                removeActivity(activity);
            }
        }
    }

    public int getStackSize(){
        return mActivityStack==null?0:mActivityStack.size();
    }

    /**
     * 结束所有Activity
     */
    public void removeAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }


    /**
     * 退出应用程序
     */
    public void appExit(Context context) {
        try {
            removeAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

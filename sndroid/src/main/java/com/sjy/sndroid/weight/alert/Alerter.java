package com.sjy.sndroid.weight.alert;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.sjy.sndroid.R;
import com.sjy.sndroid.weight.alert.alerts.LoadingAlert;
import com.sjy.sndroid.weight.alert.alerts.PopAlert;
import com.sjy.sndroid.weight.alert.interfaces.IAlert;


/**
 * ui弹框操作类
 * 加载提交弹框，屏蔽用户点击返回事件
 * Created by sjy on 2017/9/22.
 */

public class Alerter {
    private static Alerter alerter;
    private static LoadingAlert loadingAlert;
    private static PopAlert popAlert;
    private static IAlert iAlert;
    public static Alerter LoadingAlert(Context mContext){
        setActivityListener((Activity) mContext);
        if(alerter==null){
            alerter=new Alerter();
        }
        if(loadingAlert==null) {
            loadingAlert = new LoadingAlert();
            loadingAlert.create(null, mContext);
        }
        iAlert=loadingAlert;
        return alerter;
    }

    public static Alerter PopAlert(Context mContext,View customView,View anchorView){
        setActivityListener((Activity) mContext);
        if(alerter==null){
            alerter=new Alerter();
        }
        if(popAlert==null) {
            popAlert = new PopAlert(anchorView);
            popAlert.create(customView, mContext);
        }
        iAlert=popAlert;
        return alerter;
    }

    public void show(){
        iAlert.show();
    }

    public static void dismiss(){
        iAlert.dismiss();
    }

    private static void destory(){
        popAlert=null;
        loadingAlert=null;
        alerter=null;
        iAlert.destory();
    }

    /**
     * 外部监听activity的生命周期
     * @param mactivity
     */
    private static void setActivityListener(final Activity mactivity){
        mactivity.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if(activity==mactivity){
                    destory();//回收资源
                }
            }
        });
    }




}

package com.sjy.sndroid.weight.alert;

import android.app.Activity;
import android.content.Context;
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
        if(alerter==null){
            alerter=new Alerter();
        }
        if(loadingAlert==null){
            loadingAlert=new LoadingAlert();
            loadingAlert.create(null,mContext);
            iAlert=loadingAlert;
        }
        return alerter;
    }

    public static Alerter PopAlert(Context mContext,View customView,View anchorView){
        if(alerter==null){
            alerter=new Alerter();
        }
        if(popAlert==null){
            popAlert=new PopAlert(anchorView);
            popAlert.create(customView,mContext);
            iAlert=popAlert;
        }
        return alerter;
    }

    public void show(){
        iAlert.show();
    }

    public static void dismiss(){
        iAlert.dismiss();
    }

    public static void destory(){
        popAlert=null;
        loadingAlert=null;
        iAlert.destory();
    }



}

package com.sjy.sndroid.weight.alert;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Window;

import com.sjy.sndroid.R;


/**
 * ui弹框操作类
 * 加载提交弹框，屏蔽用户点击返回事件
 * Created by sjy on 2017/9/22.
 */

public class Alerter {
    public static AlertDialog myloadingview;
    private static Activity activity;

    /**
     * 显示加载中弹框
     * @param mContext
     */
    public static void showLoading(Context mContext){
        if(myloadingview==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(mContext,R.style.LoadingDialogTheme);
            myloadingview=builder.setView(LayoutInflater.from(mContext).inflate(R.layout.progress_view,null)).create();
            myloadingview.setCanceledOnTouchOutside(false);
            myloadingview.setCancelable(false);
            Window dialogWindow = myloadingview.getWindow();
            dialogWindow.setDimAmount(0);//设置昏暗度为0
        }
        myloadingview.show();
    }


    /**
     * 关闭加载中弹框
     */
    public static void closeLoading(){
        if(myloadingview!=null){
            myloadingview.dismiss();
        }
    }



    /**
     * 清除加载中弹框
     */
    public static void destoryLoading(){
        if(myloadingview!=null){
            myloadingview=null;
        }
    }

}

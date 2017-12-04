package com.sjy.sndroid.weight.alert.alerts;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.sjy.sndroid.R;
import com.sjy.sndroid.weight.alert.interfaces.IAlert;

/**
 *
 * Created by sjy on 2017/12/4.
 */

public class LoadingAlert implements IAlert {

    private static AlertDialog myloadingview;//加载中进度弹框
    private static Context mContext;

    @Override
    public void create(View view, Context mContext) {
        this.mContext=mContext;
    }

    @Override
    public void show() {
        showLoading();
    }

    @Override
    public void dismiss() {
        closeLoading();
    }

    @Override
    public void destory() {
        destoryLoading();
    }

    /**
     * 显示加载中弹框
     */
    private static void showLoading(){
        if(myloadingview==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(mContext, R.style.LoadingDialogTheme);
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
    private static void closeLoading(){
        if(myloadingview!=null){
            myloadingview.dismiss();
        }
    }



    /**
     * 清除加载中弹框
     */
    private static void destoryLoading(){
        if(myloadingview!=null){
            myloadingview=null;
        }
    }
}

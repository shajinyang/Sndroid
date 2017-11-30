package com.sjy.sndroid.weight.snackbar;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sjy.sndroid.R;
import com.sjy.sndroid.Sndroid;

/**
 * Created by sjy on 2017/11/29.
 */

public class SnackBarHelper {

    private static Snackbar snackbar;
    private static Snackbar snackcustomviewbar;

    /**
     * 持续显示
     * @param view
     * @param msg
     */
    public static void showAlways(View view,String msg){
        if(snackbar==null){
            snackbar=Snackbar.make(view,msg,Snackbar.LENGTH_INDEFINITE);
        }else {
            snackbar.setText(msg);
            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        }
        snackbar.show();
    }

    /**
     * 长显示
     * @param view
     * @param msg
     */
    public static void showLong(View view,String msg){
        if(snackbar==null){
            snackbar=Snackbar.make(view,msg,Snackbar.LENGTH_LONG);
        }else {
            snackbar.setText(msg);
            snackbar.setDuration(Snackbar.LENGTH_LONG);
        }
        snackbar.show();
    }

    /**
     * 短显示
     * @param view
     * @param msg
     */
    public static void showShort(View view,String msg){
        if(snackbar==null){
            snackbar=Snackbar.make(view,msg,Snackbar.LENGTH_SHORT);
        }else {
            snackbar.setText(msg);
            snackbar.setDuration(Snackbar.LENGTH_SHORT);
        }
        snackbar.show();
    }

    /**
     * 显示自定义view的SnackBar 短时间显示
     * @param view
     * @param customView
     */
    public static void showCustomViewShort(View view,View customView){
        if(snackcustomviewbar==null){
            snackcustomviewbar=Snackbar.make(view,"",Snackbar.LENGTH_SHORT);
        }else {
            snackcustomviewbar.setDuration(Snackbar.LENGTH_SHORT);
        }
        Snackbar.SnackbarLayout snackbarLayout= (Snackbar.SnackbarLayout) snackcustomviewbar.getView();
        snackbarLayout.setBackgroundColor(Color.TRANSPARENT);
        snackbarLayout.setPadding(0,0,0,0);
        snackbarLayout.addView(customView);
        snackcustomviewbar.show();
    }
    /**
     * 显示自定义view的SnackBar 持续显示
     * @param view
     * @param customView
     */
    public static void showCustomViewAlways(View view,View customView){
        if(snackcustomviewbar==null){
            snackcustomviewbar=Snackbar.make(view,"",Snackbar.LENGTH_INDEFINITE);
        }else {
            snackcustomviewbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        }
        Snackbar.SnackbarLayout snackbarLayout= (Snackbar.SnackbarLayout) snackcustomviewbar.getView();
        snackbarLayout.setBackgroundColor(Color.TRANSPARENT);
        snackbarLayout.setPadding(0,0,0,0);
        snackbarLayout.addView(customView);
        snackcustomviewbar.show();
    }
    /**
     * 显示自定义view的SnackBar 长时间显示
     * @param view
     * @param customView
     */
    public static void showCustomViewLong(View view,View customView){
        if(snackcustomviewbar==null){
            snackcustomviewbar=Snackbar.make(view,"",Snackbar.LENGTH_LONG);
        }else {
            snackcustomviewbar.setDuration(Snackbar.LENGTH_LONG);
        }
        Snackbar.SnackbarLayout snackbarLayout= (Snackbar.SnackbarLayout) snackcustomviewbar.getView();
        snackbarLayout.setBackgroundColor(Color.TRANSPARENT);
        snackbarLayout.setPadding(0,0,0,0);
        snackbarLayout.addView(customView);
        snackcustomviewbar.show();
    }

    /**
     * 关闭snackbar
     */
    public static void closeCusBar(){
        if(snackcustomviewbar!=null){
            snackcustomviewbar.dismiss();
        }

    }
    /**
     * 关闭snackbar
     */
    public static void closeBar(){
        if(snackbar!=null){
            snackbar.dismiss();
        }

    }
}

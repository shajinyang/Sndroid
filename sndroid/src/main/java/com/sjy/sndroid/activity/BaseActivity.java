package com.sjy.sndroid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.sjy.sndroid.manager.AppManager;

import java.io.File;


/**
 *基本activity 基于databinding
 * Created by sjy on 2017/4/22.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity
    implements View.OnClickListener {
    public T binding;
    public Activity mActivity;
    public Context mContext;
    public boolean isHideStateBar=false;//是否隐藏状态栏，默认不隐藏
    public boolean isTransStateBar=false;//是否透明状态栏，默认否，配合fitsystemwindow使用（可改变某一个activity的状态栏颜色）
    protected BaseActivity() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        mActivity=this;
        mContext=this;
        binding=DataBindingUtil.setContentView(this,bindLayout());
        getBundle();
        initListener();
        bindData();
        setStateBar();
    }
    @Override
    public void onClick(View view) {
        onClickMethod(view.getId());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    /**
     * 设置状态栏
     */
    private void setStateBar() {
        if(isHideStateBar){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if(isTransStateBar){
            if(Build.VERSION.SDK_INT> Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }else {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }



    private void getBundle(){
        if(getIntent().getExtras()!=null){
            getIntentData(getIntent().getExtras());
        }else {
            getIntentData(new Bundle());
        }
    }


    /**
     * 获取intent传值
     * @param data
     */
    public abstract void getIntentData(Bundle data);

    /**
     * 绑定布局
     * @return
     */
    public abstract int bindLayout();

    /**
     * 初始化交互事件
     */
    public abstract void initListener();

    /**
     * 绑定数据xml与pojo
     */
    public abstract void bindData();


    /**
     * 点击事件处理
     * @param viewId
     */
    public abstract void onClickMethod(int viewId);



}

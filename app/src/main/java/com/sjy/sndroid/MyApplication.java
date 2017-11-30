package com.sjy.sndroid;

import android.app.Application;

import com.sjy.sndroid.weight.permission.PermissionHelper;


/**
 * Created by sjy on 2017/11/29.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Sndroid.init(this);
    }
}

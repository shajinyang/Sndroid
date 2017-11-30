package com.sjy.sndroid;

import android.app.Application;

import com.sjy.sndroid.cache.SharedPrefrencer;
import com.sjy.sndroid.weight.permission.PermissionHelper;

/**
 * Created by sjy on 2017/11/29.
 */

public class Sndroid {

    private static Application application;

    public static Application getConfig() {
        return application;
    }

    public static void init(Application application){
        Sndroid.application=application;
        SharedPrefrencer.init(application,application.getPackageName());//初始化sp
    }
}

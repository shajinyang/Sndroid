package com.sjy.sndroid.cache;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * SharedPreferences操作类
 * Created by sjy on 2016/11/11.
 */

public class SharedPrefrencer {
    private static SharedPreferences preferences;

    public static void init(Context context, String name) {
        preferences = context.getSharedPreferences(name, Activity.MODE_PRIVATE);
    }

    public static void setString(String key, String value) {
        preferences.edit().putString(key, value).commit();
    }

    public static String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public static void setInt(String key, int value) {
        preferences.edit().putInt(key, value).commit();
    }

    public static Integer getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).commit();
    }

    public static Boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public static void setFloat(String key, float value) {
        preferences.edit().putFloat(key, value).commit();
    }

    public static float getFloat(String key) {
        return preferences.getFloat(key, 0);
    }

    public static void setObject(String key,Object object){
        String value=new Gson().toJson(object);
        preferences.edit().putString(key, value).commit();
    }

    public static <T> T getObject(String key,Class<T> T){
        String value= preferences.getString(key, "");
        if(value.isEmpty()){
            return null;
        }else {
            return new Gson().fromJson(value,T);
        }

    }
    public static void delete(String key){
        preferences.edit().remove(key).commit();
    }
    public static void clearAll(){
        preferences.edit().clear().commit();
    }

}

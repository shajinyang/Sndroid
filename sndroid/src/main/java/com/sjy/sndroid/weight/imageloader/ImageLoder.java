package com.sjy.sndroid.weight.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;


/**
 * 图像加载类
 * Created by sjy on 2017/8/30.
 */
public class ImageLoder {

    public static void loadImgWithThumb(Object object, Object res, ImageView view, float val){
        if(object instanceof Context){
            Glide.with((Context) object).load(res).thumbnail(val).into(view);
        }else if(object instanceof Activity){
            Glide.with((Activity) object).load(res).thumbnail(val).into(view);
        }else if(object instanceof Fragment){
            Glide.with((Fragment) object).load(res).thumbnail(val).into(view);
        }else if(object instanceof FragmentActivity){
            Glide.with((FragmentActivity) object).load(res).thumbnail(val).into(view);
        }
    }

    public static void loadImg(Context mContext, Object url, ImageView view){
        Glide.with(mContext).load(url).into(view);
    }
    public static void loadImg(Activity activity, Object url, ImageView view){
        Glide.with(activity).load(url).into(view);
    }
    public static void loadImg(Fragment fragment, Object url, ImageView view){
        Glide.with(fragment).load(url).into(view);
    }
    public static void loadImg(FragmentActivity fragmentActivity, Object url, ImageView view){
        Glide.with(fragmentActivity).load(url).into(view);
    }
    public static void loadImg(Context mContext, Integer resid, ImageView view){
        Glide.with(mContext).load(resid).into(view);
    }
    public static void loadImg(Activity activity, Integer resid, ImageView view){
        Glide.with(activity).load(resid).into(view);
    }
    public static void loadImg(Fragment fragment, Integer resid, ImageView view){
        Glide.with(fragment).load(resid).into(view);
    }
    public static void loadImg(FragmentActivity fragmentActivity, Integer url, ImageView view){
        Glide.with(fragmentActivity).load(url).into(view);
    }
    public static void loadImg(Context mContext, Uri url, ImageView view){
        Glide.with(mContext).load(url).into(view);
    }
    public static void loadImg(Activity activity, Uri url, ImageView view){
        Glide.with(activity).load(url).into(view);
    }
    public static void loadImg(Fragment fragment, Uri url, ImageView view){
        Glide.with(fragment).load(url).into(view);
    }
    public static void loadImg(FragmentActivity fragmentActivity, Uri url, ImageView view){
        Glide.with(fragmentActivity).load(url).into(view);
    }
    public static void loadImg(Context mContext, File file, ImageView view){
        Glide.with(mContext).load(file).into(view);
    }
    public static void loadImg(Activity activity, File file, ImageView view){
        Glide.with(activity).load(file).into(view);
    }
    public static void loadImg(Fragment fragment, File file, ImageView view){
        Glide.with(fragment).load(file).into(view);
    }
    public static void loadImg(FragmentActivity fragmentActivity, File file, ImageView view){
        Glide.with(fragmentActivity).load(file).into(view);
    }


    public static Bitmap getBitmap(Context mContext, Object url){
        try {
            Bitmap bitmap = Glide.with(mContext)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            return bitmap;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Bitmap getBitmap(Activity activity, Object url){
        try {
            Bitmap bitmap = Glide.with(activity)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            return bitmap;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getBitmap(Fragment fragment, Object url){
        try {
            Bitmap bitmap = Glide.with(fragment)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            return bitmap;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getBitmap(FragmentActivity fragmentActivity, Object url){
        try {
            Bitmap bitmap = Glide.with(fragmentActivity)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            return bitmap;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

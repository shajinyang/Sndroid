package com.sjy.sndroid.weight.alert.alerts;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sjy.sndroid.R;
import com.sjy.sndroid.weight.alert.interfaces.IAlert;
import com.sjy.sndroid.weight.alert.wrapperview.ViewWrapper;

/**
 * popwindow  动画弹框
 * Created by sjy on 2017/12/4.
 */

public class PopAlert implements IAlert {
    private View AnchorView;//锚点view，用于显示pop的相对位置
    private RelativeLayout relativeLayout;
    private View bgView;
    View contentView;
    View customView;
    private int startHeight;
    private boolean isOpen=false;
    private boolean isAnim=false;
    private Context mContext;
    public PopAlert(View AnchorView) {
        this.AnchorView = AnchorView;
    }

    @Override
    public void create(View view, Context mContext) {
        this.mContext=mContext;
        customView=view;
    }

    @Override
    public void show() {
        if(contentView==null) {
            contentView = LayoutInflater.from(mContext)
                    .inflate(R.layout.alert_pop_view, null);
            relativeLayout = contentView.findViewById(R.id.content_view);
            bgView = contentView.findViewById(R.id.bg_view);
            bgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            if (customView != null) {
                relativeLayout.addView(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//添加自定义view
            }
        }
        if(isOpen||isAnim)return;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, AnchorView.getBottom(), 0, 0);
        ((Activity) mContext).addContentView(contentView, params);
        relativeLayout.post(getAction());
    }


    //弹出动画
    @NonNull
    private Runnable getAction() {
        return new Runnable() {
            @Override
            public void run() {
                ViewWrapper tw = new ViewWrapper(relativeLayout);
                if (startHeight == 0) {
                    startHeight = relativeLayout.getHeight();
                }
                ObjectAnimator oa = ObjectAnimator
                        .ofInt(tw, "Height", 0, startHeight)
                        .setDuration(300);
                ObjectAnimator ob = ObjectAnimator
                        .ofFloat(bgView, "alpha", 0, 1f)
                        .setDuration(300);
                oa.setInterpolator(new AccelerateDecelerateInterpolator());
                ob.setInterpolator(new AccelerateDecelerateInterpolator());
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(oa).with(ob);
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        relativeLayout.setVisibility(View.VISIBLE);
                        bgView.setVisibility(View.VISIBLE);
                        isAnim=true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isOpen=true;
                        isAnim=false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorSet.start();
            }
        };
    }

    @Override
    public void dismiss() {
        if(!isOpen||isAnim)return;
        relativeLayout.post(getActionClose());
    }

    //关闭动画
    @NonNull
    private Runnable getActionClose() {
        return new Runnable() {
            @Override
            public void run() {
                ViewWrapper tw = new ViewWrapper(relativeLayout);
                ObjectAnimator oa = ObjectAnimator
                        .ofInt(tw, "Height", startHeight, 0)
                        .setDuration(300);
                oa.setInterpolator(new AccelerateDecelerateInterpolator());
                ObjectAnimator ob = ObjectAnimator
                        .ofFloat(bgView, "alpha", 1, 0)
                        .setDuration(300);
                oa.setInterpolator(new AccelerateDecelerateInterpolator());
                ob.setInterpolator(new AccelerateDecelerateInterpolator());
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(oa).with(ob);
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        isAnim=true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isOpen=false;
                        isAnim=false;
                        ((ViewGroup)contentView.getParent()).removeView(contentView);//移除控件
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorSet.start();
            }
        };
    }

    @Override
    public void destory() {
        if(contentView!=null&&contentView.getParent()!=null){
            ((ViewGroup)contentView.getParent()).removeView(contentView);//移除控件
        }
    }




}

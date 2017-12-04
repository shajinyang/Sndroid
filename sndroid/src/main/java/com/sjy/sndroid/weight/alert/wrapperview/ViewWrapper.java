package com.sjy.sndroid.weight.alert.wrapperview;

import android.view.View;

/**
 * Created by sjy on 2017/12/2.
 */

public class ViewWrapper {
    private int Height;
    private View view;

    public int getHeight() {
        return view.getLayoutParams().height;
    }

    public void setHeight(int height) {
        view.getLayoutParams().height = height;
        view.requestLayout();
    }

    public ViewWrapper(View view) {
        this.view = view;
    }


}

package com.sjy.sndroid.weight.alert.interfaces;

import android.content.Context;
import android.view.View;

/**
 *
 * Created by sjy on 2017/12/4.
 */

public interface IAlert {
    void create(View view, Context mContext);
    void show();
    void dismiss();
    void destory();
}

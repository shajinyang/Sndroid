package com.sjy.sndroid;

import android.Manifest;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sjy.sndroid.weight.permission.PermissionHelper;
import com.sjy.sndroid.weight.permission.business.IpermissionCallBackListener;
import com.sjy.sndroid.weight.snackbar.SnackBarHelper;
import com.sjy.sndroid.weight.toast.Toaster;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    boolean isClick=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionHelper.getInstance()
                .requestPermissions(new IpermissionCallBackListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClick) {
                    Toaster.showCustomToast(LayoutInflater.from(MainActivity.this).inflate(R.layout.head_view, null));
                    SnackBarHelper.showShort(findViewById(R.id.btn),"短时间显示");
                    isClick=false;
                }else {

                    final View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.head_view, null);
                     ImageView iv= view.findViewById(R.id.logo);
                     iv.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Toaster.showToast("拜拜");
                             SnackBarHelper.closeCusBar();
                         }
                     });
                    SnackBarHelper.showCustomViewAlways(findViewById(R.id.btn),view);
                    isClick=true;
                }
            }
        });



    }
}

package com.sjy.sndroid.weight.permission;



import com.sjy.sndroid.Sndroid;
import com.sjy.sndroid.weight.permission.business.IpermissionCallBackListener;
import com.yanzhenjie.permission.AndPermission;

/**
 * 权限请求类 single instance
 * Created by sjy on 2017/5/8.
 */

public class PermissionHelper {

    private static final int REQUEST_CODE= (int) System.currentTimeMillis();
    private static PermissionHelper instance;
    public static PermissionHelper getInstance(){
        if(instance==null){
            synchronized (PermissionHelper.class){
                if(instance==null){
                    instance=new PermissionHelper();
                }
            }
        }
        return instance;
    }


    /**
     * 请求权限
     * @param permissions 权限集
     */
    public void requestPermissions(IpermissionCallBackListener ipermissionCallBackListener,String... permissions){
        AndPermission.with(Sndroid.getConfig())
                .requestCode(REQUEST_CODE)
                .permission(permissions)
                .callback(ipermissionCallBackListener)
                .start();
    }

}

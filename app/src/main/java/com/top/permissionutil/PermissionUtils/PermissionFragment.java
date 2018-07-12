package com.top.permissionutil.PermissionUtils;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihaitao on 2018/7/12.
 */
public class PermissionFragment extends android.support.v4.app.Fragment {
    private PermissionListener mPermissionListener;
    //申请权限code
    private static int PERMISSION_CODE = 200;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setListenerAndRequest(String[] permissions, PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
        requestPermissions(permissions);
    }

    private void requestPermissions(String[] permissions) {
        ArrayList<String> requestLists = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                //权限没有申请
                requestLists.add(permission);
            }
        }
        if (requestLists.isEmpty()) {
            //已经授权
            permissionAllGranted();
        } else {
            //申请授权
            requestPermissions(requestLists.toArray(new String[requestLists.size()]), PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
        grantResults) {
        ArrayList<String> list1 = new ArrayList<>();
        if (requestCode != PERMISSION_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if(grantResults.length>0){
            for (int i = 0; i < grantResults.length; i++) {
               if(grantResults[i]!= PackageManager.PERMISSION_GRANTED){
                   list1.add(permissions[i]);
               }
            }
            if(list1.isEmpty()){
                permissionAllGranted();
            }else{
                for (String s : list1) {
                    boolean b = shouldShowRequestPermissionRationale(s);
                    if(!b){
                        //点击不再询问 或者没有在manifest标识
                        permissionNotAsk(list1);
                        return;
                    }
                }
                //拒绝授权
                permissionDenied(list1);
            }
        }
    }

    /**
     * 权限全部通过
     */
    private void permissionAllGranted() {
        if (mPermissionListener != null) {
            mPermissionListener.onGranted();
        }
    }

    /**
     * 权限没有通过
     *
     * @param list
     */
    private void permissionDenied(List<String> list) {
        if (mPermissionListener != null) {
            mPermissionListener.onDenied(list);
        }
    }

    /**
     * 权限被拒绝并且勾选了不再询问或者没有在manifest标识
     */
    private void permissionNotAsk(List<String> list) {
        if (mPermissionListener != null) {
            mPermissionListener.onShouldShowRationale(list);
        }
    }

}

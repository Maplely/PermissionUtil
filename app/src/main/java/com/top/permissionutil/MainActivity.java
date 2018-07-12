package com.top.permissionutil;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.top.permissionutil.PermissionUtils.PermissionListener;
import com.top.permissionutil.PermissionUtils.PermissionUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.with(this).setListenerAndRequest(
            new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE},
            new PermissionListener() {
                @Override
                public void onGranted() {
                    Log.e("TTT","MainActivity->onGranted:"+"全部通过");
                }

                @Override
                public void onDenied(List<String> deniedPermission) {
                    Log.e("TTT","MainActivity->onDenied:"+"未通过"+deniedPermission.get(0));
                }

                @Override
                public void onShouldShowRationale(List<String> deniedPermission) {
                    Log.e("TTT","MainActivity->onShouldShowRationale:"+"不再询问或者没有在manifest标识"+deniedPermission.get(0));
                }
            }
        );
    }
}

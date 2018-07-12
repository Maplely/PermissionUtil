package com.top.permissionutil.PermissionUtils;

import java.util.List;

/**
 * Created by lihaitao on 2018/7/12.
 */
public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermission);

    void onShouldShowRationale(List<String> deniedPermission);
}

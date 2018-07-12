package com.top.permissionutil.PermissionUtils;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by lihaitao on 2018/7/12.
 */
public class PermissionUtil {
    private static final String TAG = "PermissionUtil";
   public static PermissionFragment with(FragmentActivity activity){
       PermissionFragment fragment = (PermissionFragment)activity.getSupportFragmentManager().findFragmentByTag(TAG);
       if(null==fragment){
           fragment=new PermissionFragment();
           FragmentManager manager = activity.getSupportFragmentManager();
           manager.beginTransaction()
               .add(fragment, TAG)
               .commit();
           manager.executePendingTransactions();
       }
       return fragment;
   }
}

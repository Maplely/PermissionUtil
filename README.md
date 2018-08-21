# PermissionUtil
[![](https://jitpack.io/v/top2015/PermissionUtil.svg)](https://jitpack.io/#top2015/PermissionUtil)
------------
6.0之后android版本需要权限动态申请 此工具类解决了申请与结果分离的问题 优点为使用者更为清晰
### 引用
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.top2015:PermissionUtil:1.1'
	}
```
### 用法
```
 PermissionUtil.with(this).setListenerAndRequest(
             new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE},
             new PermissionListener() {
                 @Override
                 public void onGranted() {
                  //全部通过
                 }
 
                 @Override
                 public void onDenied(List<String> deniedPermission) {
                 //未通过权限
                 }
 
                 @Override
                 public void onShouldShowRationale(List<String> deniedPermission) {
                 //不再询问或者没有在manifest标识
                 }
             }
         );
```
### 特点
1. 利用fragment 绑定activity的生命周期
2. 仿glide链式调用 不需要再onRequestPermissionsResult()中回调处理 逻辑更加清晰

### 联系方式及反馈 | Contract & FeedBack
Author: Li Haitao

Email: haitao.li_2016@163.com

QQ:986086927

GitHub: https://github.com/top2015

任何缺陷、建议，欢迎给我发邮件，或在GitHub上创建问题单。

Any bugs and recommendations, please send emails for me, or create issues on GitHub.

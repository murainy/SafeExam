package com.murainy.safeexam.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

public class DeviceUtil {

private static String android_id;

public String getSystemCode(){

          String handSetInfo=
          "手机型号:" + android.os.Build.MODEL + 
          ",SDK版本:" + Build.VERSION.RELEASE+
          ",系统版本:" + android.os.Build.VERSION.RELEASE/*+
          ",软件版本:"+getAppVersionName(MainActivity.this)*/; 
         Log.i("设备信息", handSetInfo);
    return null;
}

/**
 * 获取手机的唯一标志(机器码)
 * 
 * */
public static String getDeviceId(Context context){

    if(TextUtils.isEmpty(android_id)){

        android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    return android_id;
}
/**
 * 获取当前设备的MAC地址
 * 
 * */
public static String getMacAddress(Context context) {
    String macAddress;
       WifiManager wifi = (WifiManager) context .getSystemService(Context.WIFI_SERVICE);
       WifiInfo info = wifi.getConnectionInfo();
    macAddress = info.getMacAddress();
    if (null == macAddress) {
        return "";
        }
       macAddress = macAddress.replace(":", "");
    return macAddress;
}

/**
 * 获取应用版本号
 */
public static String getAppVersionName(Context mContext){
    String versionName = "";
    try {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo("com.murainy.safeexam", 0);
        versionName = packageInfo.versionName;
        if (TextUtils.isEmpty(versionName)) {
            return "";
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return versionName;
}

/** 
 * 获取应用程序名称 
 */  
public static String getAppName(Context context)  
{  
    try  
    {  
        PackageManager packageManager = context.getPackageManager();  
        PackageInfo packageInfo = packageManager.getPackageInfo(  
                context.getPackageName(), 0);  
        int labelRes = packageInfo.applicationInfo.labelRes;  
        return context.getResources().getString(labelRes);  
    } catch (PackageManager.NameNotFoundException e)
    {  
        e.printStackTrace();  
    }  
    return null;  
}
}
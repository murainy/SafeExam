package com.murainy.safeexam;

import android.app.Application;
import android.util.Log;

import com.murainy.safeexam.Utils.CrashHandler;
import com.murainy.safeexam.beans.Student;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by murainy on 2015/12/13.
 */
public class SafeExam extends Application {
    public static String APPID = "c313355d6f4419b5084f07b9ee758da4";
    private static Student student;

    public static Student getStudent() { return student;  }

    public static void setStudent(Student s) {
        student = s;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //第一:默认初始化
        //Bmob.initialize(this,APPID);
        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                 .setApplicationId(APPID)
                //请求超时时间（单位为秒）：默认15s
                 .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
               .setUploadBlockSize(1024 * 1024)

                //文件的过期时间(单位为秒)：默认1800s
              .setFileExpiration(5500)
                .build();
         Bmob.initialize(config);

        initData();
//
        if (LeakCanary.isInAnalyzerProcess(this)) {
            Log.d("App", "In LeakCanary Analyzer Process");
            return;
        }
        LeakCanary.install(this);
//异常报告
        CrashReport.initCrashReport(getApplicationContext(), "efd91e37-2d6a-42ef-9532-b1e7108190f9", false);
    }

    private void initData() {
        //当程序发生Uncaught异常的时候,由该类来接管程序,一定要在这里初始化
        CrashHandler.getInstance().init(this);
    }
}

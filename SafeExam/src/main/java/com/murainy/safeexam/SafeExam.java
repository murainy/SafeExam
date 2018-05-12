package com.murainy.safeexam;

import android.app.Application;
import android.util.Log;

import com.isseiaoki.simplecropview.util.Logger;
import com.murainy.safeexam.Utils.CrashHandler;
import com.murainy.safeexam.beans.Student;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by murainy on 2015/12/13.
 */
public class SafeExam extends Application {
	public static String APPID = "c313355d6f4419b5084f07b9ee758da4";
	private static Student student;

	public static Student getStudent() {
		return student;
	}

	public static void setStudent(Student s) {
		student = s;
	}


	@Override
	public void onCreate() {
		super.onCreate();
		//第一:默认初始化
		// 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
		Bmob.initialize(this, APPID);
		BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
			@Override
			public void done(BmobInstallation bmobInstallation, BmobException e) {
				if (e == null) {
					Logger.i(bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
				} else {
					Logger.e(e.getMessage());
				}
			}
		});
		// 启动推送服务
		BmobPush.startWork(this);
		initData();
		//
		LeakCanary.install(this);

		if (LeakCanary.isInAnalyzerProcess(this)) {
			Log.d("App", "In LeakCanary Analyzer Process");
			return;
		}

		//异常报告
		CrashReport.setIsDevelopmentDevice(getApplicationContext(), true);
		CrashReport.initCrashReport(getApplicationContext());
	}

	private void initData() {
		//当程序发生Uncaught异常的时候,由该类来接管程序,一定要在这里初始化
		CrashHandler.getInstance().init(this);

	}
}

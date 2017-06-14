package com.murainy.safeexam.activitis;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.murainy.safeexam.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends Activity {
	protected AgentWeb mAgentWeb;

	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.web_layout)
	LinearLayout mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		ButterKnife.bind(this);

		String mUrl;
		Bundle bundle = getIntent().getBundleExtra("bundle");
		mUrl = bundle.getString("url");
		WebSettings settings;
		String tvt = "关于 (SafeExam V" + getVersionName(this) + ")";
		tv_title.setText(tvt);

		mAgentWeb = AgentWeb.with(this)//传入Activity
				.setAgentWebParent(mLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
				.useDefaultIndicator()// 使用默认进度条
				.defaultProgressBarColor() // 使用默认进度条颜色
				.setReceivedTitleCallback(mCallback) //设置 Web 页面的 title 回调
				.createAgentWeb()//
				.ready()
				.go(mUrl);
	}

	private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
		@Override
		public void onReceivedTitle(WebView view, String title) {
			if (tv_title != null)
				tv_title.setText(title);
		}
	};


	// 获取当前应用的版本号
	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			String version = packInfo.versionName;
			if (!TextUtils.isEmpty(version)) {
				return version;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (mAgentWeb.handleKeyEvent(keyCode, event)) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		mAgentWeb.getWebLifeCycle().onPause();
		super.onPause();

	}

	@Override
	protected void onResume() {
		mAgentWeb.getWebLifeCycle().onResume();
		super.onResume();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		//mAgentWeb.destroy();
		mAgentWeb.getWebLifeCycle().onDestroy();
	}
}

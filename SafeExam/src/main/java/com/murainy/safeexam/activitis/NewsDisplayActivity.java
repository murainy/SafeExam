package com.murainy.safeexam.activitis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.murainy.safeexam.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsDisplayActivity extends Activity {
	private static String newsUrl;
	private static String webData;
	protected LinearLayout mLayout;
	private AgentWeb mAgentWeb;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_display);
		mLayout = findViewById(R.id.baseWeb);
		newsUrl = getIntent().getStringExtra("news_url");
		WebView webView = findViewById(R.id.web_view);
		webView.getSettings().setDefaultTextEncodingName("UTF-8");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setSupportZoom(true);
		//适应屏幕
		webView.getSettings().setUseWideViewPort(false);
		webView.getSettings().setDefaultFontSize(21);
		webView.getSettings().getStandardFontFamily();
		webView.setWebChromeClient(new WebChromeClient());
		try {
			ProgressAsyncTask asyncTask = new ProgressAsyncTask(webView);
			asyncTask.execute(6000);
			mAgentWeb = AgentWeb.with(this)//传入Activity
					.setAgentWebParent(mLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
					.useDefaultIndicator()// 使用默认进度条
					.createAgentWeb()//
					.ready()
					.go(webData);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	/**
	 * 跟随生命周期释放资源更省电
	 */
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
		mAgentWeb.getWebLifeCycle().onDestroy();
	}

	static class ProgressAsyncTask extends AsyncTask<Integer, Integer, String> {

		@SuppressLint("StaticFieldLeak")
		private WebView webView;

		ProgressAsyncTask(WebView webView) {
			super();
			this.webView = webView;

		}


		@Override
		protected String doInBackground(Integer... params) {
			String str = null;
			Document doc = null;
			try {
				// 获取文档
				doc = Jsoup
						.connect(newsUrl).timeout(5000).userAgent("Mozilla").get();
				//获取<div id="right">对应的内容
				Elements ListDiv = doc.getElementsByClass("artical-content");
				//查找图片
				for (Element element : ListDiv) {
					Elements Listimg = ListDiv.select("img");
					String strimg = "";
					for (Element e : Listimg) {
						e.attr("style", "width:100%,height:auto;");
						strimg = strimg + e.attr("src");
						webData = strimg;
					}
					str = element.html();
				}


			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
		}

		/**
		 * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
		 * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
		 */
		@Override
		protected void onPostExecute(String result) {
			webView.loadData(result, "text/html;charset=utf-8", null);

		}

	}
}
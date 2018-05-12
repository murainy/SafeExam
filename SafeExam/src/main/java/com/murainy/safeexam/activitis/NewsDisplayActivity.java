package com.murainy.safeexam.activitis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.murainy.safeexam.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDisplayActivity extends Activity {
	private static String newsUrl;
	private static String webData;
	@BindView(R.id.btn_large)
	Button btnLarge;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_display);
		ButterKnife.bind(this);
		newsUrl = getIntent().getStringExtra("news_url");
		WebView webView = findViewById(R.id.web_view);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.getSettings().setDefaultTextEncodingName("UTF-8");
		webView.getSettings().setJavaScriptEnabled(true);
		//适应屏幕
		webView.getSettings().setDefaultFontSize(21);
		webView.getSettings().getStandardFontFamily();

		try {
			ProgressAsyncTask asyncTask = new ProgressAsyncTask(webView);
			asyncTask.execute(8000);

		} catch (Exception e) {
			e.printStackTrace();
		}

		//java回调js代码，不要忘了@JavascriptInterface这个注解，不然点击事件不起作用
		webView.addJavascriptInterface(new JsCallJavaObj() {
			@JavascriptInterface
			@Override
			public void showBigImg(String url) {

				Intent intent = new Intent(NewsDisplayActivity.this, LargeImageViewActivity.class);
				intent.putExtra("fromMain", url);
				startActivity(intent);
			}
		}, "jsCallJavaObj");
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				setWebImageClick(view);
			}
		});

	}

	/**
	 * 設置網頁中圖片的點擊事件
	 *
	 * @param view
	 */
	private void setWebImageClick(WebView view) {
		String jsCode = "javascript:(function(){" +
				"var imgs=document.getElementsByTagName(\"img\");" +
				"for(var i=0;i<imgs.length;i++){" +
				"imgs[i].onclick=function(){" +
				"window.jsCallJavaObj.showBigImg(this.src);" +
				"}}})()";
		view.loadUrl(jsCode);
	}

	@OnClick(R.id.btn_large)
	public void onViewClicked() {
		Intent i7 = new Intent(NewsDisplayActivity.this, LargeImageViewActivity.class);
		i7.putExtra("fromMain", webData);
		startActivity(i7);
	}

	/**
	 * Js調用Java接口
	 */
	private interface JsCallJavaObj {
		void showBigImg(String url);
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

					for (Element e : Listimg) {
						e.attr("style", "MAX-WIDTH: 100%!important;HEIGHT: auto!important;width:expression(this.width > 600 ? \"600px\" : this.width)!important;");
						webData = e.attr("src");
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
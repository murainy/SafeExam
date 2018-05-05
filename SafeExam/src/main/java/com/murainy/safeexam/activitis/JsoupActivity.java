package com.murainy.safeexam.activitis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.murainy.safeexam.R;
import com.murainy.safeexam.adapter.NewsAdapter;
import com.murainy.safeexam.beans.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupActivity extends Activity {
	private ProgressBar pgb;
	private List<News> newsList;
	private NewsAdapter adapter;
	private Handler handler;
	private ListView lv;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jsoup);
		newsList = new ArrayList<>();
		lv = findViewById(R.id.news_lv);
		pgb = findViewById(R.id.loading_progress);

		getNews();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					adapter = new NewsAdapter(JsoupActivity.this, newsList);
					lv.setAdapter(adapter);
					lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							News news = newsList.get(position);
							Intent intent = new Intent(JsoupActivity.this, NewsDisplayActivity.class);
							intent.putExtra("news_url", news.getNewsUrl());
							startActivity(intent);
						}
					});
				}
			}
		};

	}


	private void getNews() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int p = 0;
					//获取虎扑新闻3页的数据，网址格式为：https://voice.hupu.com/nba/第几页
					for (int i = 1; i <= 5; i++) {
						Document doc = Jsoup.connect("https://voice.hupu.com/nba/" + Integer.toString(i)).get();
						Elements titleLinks = doc.select("div.list-hd");    //解析来获取每条新闻的标题与链接地址
						Elements descLinks = doc.select("div.otherInfo");//解析来获取每条新闻的简介
						Elements timeLinks = doc.select("div.otherInfo");   //解析来获取每条新闻的时间与来源
						Log.e("title", Integer.toString(titleLinks.size()));
						for (int j = 0; j < titleLinks.size(); j++) {
							String title = titleLinks.get(j).select("a").text();
							String uri = titleLinks.get(j).select("a").attr("href");
							String desc = "来自：" + descLinks.get(j).select(".other-left .comeFrom a").text();
							String time = timeLinks.get(j).select(".other-left a").attr("title");
							News news = new News(title, uri, desc, time);
							newsList.add(news);
						}
						p = pgb.getProgress();
						pgb.setProgress(p + 20);

					}

					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
